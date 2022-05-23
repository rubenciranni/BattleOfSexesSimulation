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

    public abstract class WomanSubType extends SubType {
        SubManPopulation.ManSubType currentMan;

        public WomanSubType(ThreadGroup group) {
            super(group, RandomNameGenerator.randomNameOfGirl());
        }

        public abstract boolean accepted(SubManPopulation.ManSubType man);

        public synchronized void proposal(SubManPopulation.ManSubType man) {
            if (accepted(man)) {
                this.updateCredit(man);
                man.updateCredit(this);
                System.out.println(this.getName() + " coupled with " + man.getName());
                this.currentMan = man;
                man.currentWoman = this;
                this.isSingle = false;
                man.isSingle = false;
                hey();
            }
        }

        public synchronized void generateOffspringWith(SubManPopulation.ManSubType man) {
            Random rand = new Random();
            boolean sex = rand.nextBoolean();

            if (population.size > 1000) {
                return;
            }

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
            man.leaveOrStay(this);
        }

        @Override
        public synchronized void run() {
            while (credit > 0) {
                try {
                    if (isSingle) {
                        sleep(100);
                        population.queue.put(this);
                        wait();
                        generateOffspringWith(currentMan);
                    }
                    else {
                        wait();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
