import java.util.Random;

public abstract class SubWomanPopulation extends SubPopulation {
    public SubWomanPopulation(ThreadGroup parent, String name, int size) {
        super(parent, name, size);
    }

    @Override
    public void increaseSize() {
        super.increaseSize();
        population.getWomanPopulation().size++;
    }

    @Override
    public void decreaseSize() {
        super.decreaseSize();
        population.getWomanPopulation().size--;
    }

    public abstract class WomanSubType extends SubType {
        SubManPopulation.ManSubType currentMan;
        private boolean sterility;

        public WomanSubType(ThreadGroup group) {
            super(group, RandomNameGenerator.randomNameOfGirl());
        }

        public abstract boolean accepted(SubManPopulation.ManSubType man);

        public synchronized boolean proposal(SubManPopulation.ManSubType man) {
            if (accepted(man)) {
                this.currentMan = man;
                man.currentWoman = this;
                this.isSingle = false;
                man.isSingle = false;
                this.ring = new Object();           //creating a shared object to synchronize them
                man.ring = this.ring;
                return true;
            }
            else {
                return false;
            }
        }

        public synchronized void generateOffspringWith(SubManPopulation.ManSubType man) {
            System.out.println(this.getName() + " coupled with " + man.getName());
            if (population.size > 100) {
                sterility = true;
            }
            if (sterility) {
                man.hey();
                return;
            }
            this.updateCredit(man);
            man.updateCredit(this);
            Random rand = new Random();
            boolean sex = rand.nextBoolean();

            if (sex) {
                boolean m = man.getSubType() == "Faithful";
                /*
                 if (noise && rand.nextInt(0, 50) == 49) {
                     m = !m;
                 }
                 */
                if (m) {
                    FaithfulPopulation fatherPopulation = man.getPopulation();
                    fatherPopulation.new Faithful(fatherPopulation).start();
                    fatherPopulation.increaseSize();
                }
                else {
                    PhilandererPopulation fatherPopulation = man.getPopulation();
                    fatherPopulation.new Philanderer(fatherPopulation).start();
                    fatherPopulation.increaseSize();
                }
            }
            else {
                boolean w = this.getSubType() == "Coy";
                /*
                if (noise && rand.nextInt(0, 50) == 49) {
                    w = !w;
                }
                 */
                if (w) {
                    CoyPopulation motherPopulation = this.getPopulation();
                    motherPopulation.new Coy(motherPopulation).start();
                    motherPopulation.increaseSize();
                }
                else {
                    FastPopulation motherPopulation = this.getPopulation();
                    motherPopulation.new Fast(motherPopulation).start();
                    motherPopulation.increaseSize();
                }
            }
            man.hey();
        }

        @Override
        public synchronized void run() {
            while (credit > 0 && lifePoints > 0) {
                try {
                    if (isSingle) {
                        sleep(100);
                        population.queue.put(this);
                    }
                    wait();
                    generateOffspringWith(currentMan);
                    wait();
                    lifePoints--;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (ring) {
                if (!isSingle) {
                    currentMan.currentWoman = null;
                    currentMan.isSingle = true;
                }
            }
            SubWomanPopulation.this.decreaseSize();
        }
    }
}
