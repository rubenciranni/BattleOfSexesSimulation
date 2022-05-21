public abstract class SubManPopulation extends SubPopulation {
    public SubManPopulation(ThreadGroup parent, String name, int size) {
        super(parent, name, size);
    }

    public abstract class ManSubType extends SubType {
        public ManSubType(ThreadGroup group) {
            super(group, RandomNameGenerator.randomNameOfBoy());
        }

        @Override
        public void run() {

        }
    }
}
