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

        public void generateOffspringWith(SubManPopulation.ManSubType man) {
            Random rand = new Random();
            boolean sex = rand.nextBoolean();
            this.updateCredit();
            man.updateCredit();

            if (sex) {
                boolean m = man.getClass().getName() == "Faithful";
                /*
                 if (noise && rand.nextInt(0, 50) == 49) {
                     m = !m;
                 }
                 */
                if (m) {
                    FaithfulPopulation fatherPopulation = man.getPopulation();
                    fatherPopulation.increaseSize();
                    fatherPopulation.new Faithful(fatherPopulation);
                }
                else {
                    PhilandererPopulation father = man.getPopulation();
                    father.increaseSize();
                    father.new Philanderer(father);
                }
            }
            else {
                boolean w = this.getClass().getName() == "Coy";
                /*
                if (noise && rand.nextInt(0, 50) == 49) {
                    w = !w;
                }
                 */
                if (w) {
                    CoyPopulation mother = man.getPopulation();
                    mother.increaseSize();
                    mother.new Coy(mother);
                }
                FastPopulation mother = man.getPopulation();
                mother.increaseSize();
                mother.new Fast(mother);
            }
        }

        public abstract boolean accepted(SubManPopulation.ManSubType man);

        public synchronized void proposal(SubManPopulation.ManSubType man) {
            if (accepted(man)) {
                currentMan = man;
                notify();
            }
        }
        public synchronized void heyBabe() {
            notify();
        }

        public synchronized void manWants() {
            notify();
        }

        @Override
        public void run() {
            while (credit > 0) {
                try {
                    wait();
                    generateOffspringWith(currentMan);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
