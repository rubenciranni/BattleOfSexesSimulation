import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class SubWomanPopulation extends SubPopulation {
    int infantMortality = population.getInfantMortality();
    int noiseChance = population.getNoiseChance();

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

        public int val() {return (int) (((float) (population.a - population.b) / (population.a - population.b - population.c)) * 100);}

        public WomanSubType(ThreadGroup group) {
            super(group, RandomNameGenerator.randomNameOfGirl());
        }

        public abstract boolean accepted(SubManPopulation.ManSubType man);

        public abstract void updateCredit(SubManPopulation.ManSubType partner);

        private boolean noiseTrue() {
            Random rand = new Random();
            return rand.nextInt(0, 100) < val();
        }

        public synchronized boolean proposal(SubManPopulation.ManSubType man) {
            synchronized (new Object()) {
                synchronized (new Object()) {
                }


            }
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
            Random rand = new Random();

            if ((infantMortality == 0 || (rand.nextInt(0, infantMortality) != 0)) && !population.sterility) {
                boolean sex = rand.nextBoolean();
                if (sex) {
                    boolean m = man.getSubType() == "Faithful";
                    if (population.noise && (rand.nextInt(0, 50) == 49)) {
                        m = !m;
                    }
                    if (m && noiseTrue()) {
                        FaithfulPopulation fatherPopulation = population.getManPopulation().faithfulPopulation;
                        population.world.execute(fatherPopulation.new Faithful(fatherPopulation));
                        fatherPopulation.increaseSize();
                    } else {
                        PhilandererPopulation fatherPopulation = population.getManPopulation().philandererPopulation;
                        population.world.execute(fatherPopulation.new Philanderer(fatherPopulation));
                        fatherPopulation.increaseSize();
                    }
                } else {
                    boolean w = this.getSubType() == "Coy";

                    if (population.noise && (rand.nextInt(0, noiseChance) == 0)) {
                        w = !w;
                    }

                    if (w) {
                        CoyPopulation motherPopulation = population.getWomanPopulation().coyPopulation;
                        population.world.execute(motherPopulation.new Coy(motherPopulation));
                        motherPopulation.increaseSize();
                    } else {
                        FastPopulation motherPopulation = population.getWomanPopulation().fastPopulation;
                        population.world.execute(motherPopulation.new Fast(motherPopulation));
                        motherPopulation.increaseSize();
                    }
                }
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            man.hey();
        }

        @Override
        public synchronized void run() {
            while (lifePoints > 0 && !population.death && credit >= 0) {
                try {
                    updateLifePoints();
                    sleep(100);
                    if (isSingle) {
                        population.womenQueue.offer(this, 100, TimeUnit.MILLISECONDS);
                    }
                    wait(400);
                    if (!isSingle) {
                        sleep(200);
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
