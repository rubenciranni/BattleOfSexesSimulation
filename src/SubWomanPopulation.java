public abstract class SubWomanPopulation extends SubPopulation {
    public SubWomanPopulation(ThreadGroup parent, String name, int size) {
        super(parent, name, size);
    }

    public abstract class WomanSubType extends SubType {
        public WomanSubType(ThreadGroup group) {
            super(group, RandomNameGenerator.randomNameOfGirl());
        }

        public void generateOffspringWith(SubManPopulation.ManSubType man) {
        }

        @Override
        public void run() {

        }
    }
}
