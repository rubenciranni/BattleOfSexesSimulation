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

        public synchronized void generateOffspringWith(SubManPopulation.ManSubType man) {
            Random rand = new Random();
            boolean sex = rand.nextBoolean();
            this.updateCredit();
            man.updateCredit();

            if (sex) {
                boolean m = man.getSubType() == "Faithful";
                /*
                 if (noise && rand.nextInt(0, 50) == 49) {
                     m = !m;
                 }
                 */
                if (m) {
                    FaithfulPopulation fatherPopulation = man.getPopulation();
                    fatherPopulation.increaseSize();
                    fatherPopulation.new Faithful(fatherPopulation).start();
                }
                else {
                    PhilandererPopulation father = man.getPopulation();
                    father.increaseSize();
                    father.new Philanderer(father).start();
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
                    CoyPopulation mother = this.getPopulation();
                    mother.increaseSize();
                    mother.new Coy(mother).start();
                }
                else {
                    FastPopulation mother = this.getPopulation();
                    mother.increaseSize();
                    mother.new Fast(mother).start();
                }
            }
            man.leaveOrStay(this);
        }

        public abstract boolean accepted(SubManPopulation.ManSubType man);

        public void proposal(SubManPopulation.ManSubType man) {
            if (accepted(man)) {
                System.out.println(this.getName() + " coupled with " + man.getName());
                this.currentMan = man;
                man.currentWoman = this;
                this.isSingle = false;
                man.isSingle = false;
                heyBabe();
            }
        }
        public synchronized void heyBabe() {
            notify();
        }

        @Override
        public synchronized void run() {
            while (credit > 0) {
                try {
                    if (isSingle) {
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
