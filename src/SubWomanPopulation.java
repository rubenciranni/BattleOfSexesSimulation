public abstract class SubWomanPopulation extends SubPopulation {
    public SubWomanPopulation(ThreadGroup parent, String name, int size) {
        super(parent, name, size);
    }

    public abstract class WomanSubType extends SubType {
        SubManPopulation.ManSubType currentMan;
        public WomanSubType(ThreadGroup group) {
            super(group, RandomNameGenerator.randomNameOfGirl());
        }

        public abstract void generateOffspringWith(SubManPopulation.ManSubType man);

        public abstract boolean accepted(SubManPopulation.ManSubType man);

        public synchronized void proposal(SubManPopulation.ManSubType man) {
            if (accepted(man)) {
                currentMan = man;
                notify();
            }

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
