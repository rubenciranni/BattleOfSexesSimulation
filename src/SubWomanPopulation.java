import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class SubWomanPopulation extends SubPopulation {
    public SubWomanPopulation(ThreadGroup parent, String name, int size) {
        super(parent, name, size);
    }

    @Override
    public void increaseSize() {
        super.increaseSize();
        synchronized (population.getWomanPopulation()) {
            population.getWomanPopulation().size++;
        }
    }

    @Override
    public void decreaseSize() {
        super.decreaseSize();
        synchronized (population.getWomanPopulation()) {
            population.getWomanPopulation().size--;
        }
    }

    public abstract class WomanSubType extends SubType {

        SubManPopulation.ManSubType currentMan;
        static boolean grimmyIsOut;




        public WomanSubType(ThreadGroup group) {
            super(group, RandomNameGenerator.randomNameOfGirl());
        }

        public abstract boolean accepted(SubManPopulation.ManSubType man);

        public abstract void updateCredit(SubManPopulation.ManSubType partner);

        public synchronized static boolean isNotGrimmyOut() {
            if (grimmyIsOut)
                return false;
            grimmyIsOut = true;
            return true;
        }

        public synchronized boolean proposal(SubManPopulation.ManSubType man) {
            if (accepted(man)) {
                this.currentMan = man;
                man.currentWoman = this;
                this.isSingle = false;
                man.isSingle = false;
                this.ring = man.ring;
                return true;
            } else {
                return false;
            }
        }

        public synchronized void generateOffspringWith(SubManPopulation.ManSubType man) {
            this.updateCredit(man);
            // temporary implementation in order to not destroy your PC
            // ----------------------
            // TODO it happened sometimes that execution didn't end. Try and see if this happens also to you.
            if (population.size > 1200 && isNotGrimmyOut()) {
                new GrimReaper(population, 1100).start();
            }
            // ----------------------
            if (population.sterility) {
                man.hey();
                return;
            }
            Random rand = new Random();
            boolean sex = rand.nextBoolean();

            if (sex) {
                boolean m = man.getSubType() == "Faithful";
                if (population.noise && (rand.nextInt(0, 50) == 49)) {
                    m = !m;
                }
                if (m) {
                    FaithfulPopulation fatherPopulation = population.getManPopulation().faithfulPopulation;
                    fatherPopulation.new Faithful(fatherPopulation).start();
                    fatherPopulation.increaseSize();
                } else {
                    PhilandererPopulation fatherPopulation = population.getManPopulation().philandererPopulation;
                    fatherPopulation.new Philanderer(fatherPopulation).start();
                    fatherPopulation.increaseSize();
                }
            } else {
                boolean w = this.getSubType() == "Coy";

                if (population.noise && (rand.nextInt(0, 50) == 49)) {
                    w = !w;
                }

                if (w) {
                    CoyPopulation motherPopulation = population.getWomanPopulation().coyPopulation;
                    motherPopulation.new Coy(motherPopulation).start();
                    motherPopulation.increaseSize();
                } else {
                    FastPopulation motherPopulation = population.getWomanPopulation().fastPopulation;
                    motherPopulation.new Fast(motherPopulation).start();
                    motherPopulation.increaseSize();
                }
            }
            man.hey();
        }

        @Override
        public synchronized void run() {
            while (credit >= 0 && lifePoints > 0) {
                try {
                    lifePoints--;
                    //System.out.println(this.getSubType() +"  "+ this.credit);
                    sleep(100);
                    if (isSingle) {
                        population.womenQueue.offer(this, 100, TimeUnit.MILLISECONDS);
                    }
                    wait(400);
                    if (!isSingle) {
                        generateOffspringWith(currentMan);
                        wait();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            SubWomanPopulation.this.decreaseSize();
            synchronized (ring) {
                if (!isSingle) {
                    currentMan.currentWoman = null;
                    currentMan.isSingle = true;
                }
            }
        }
    }
}
