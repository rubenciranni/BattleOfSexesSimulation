public class FaithfulPopulation extends SubPopulation{
    public FaithfulPopulation(ThreadGroup parent, String name, int size) {
        super(parent, name, size);
    }

    @Override
    public void addToInitialPopulation() {
        for(int i = 0; i < this.size; i++) {
            population.initialPopulationList.add(new FaithfulPopulation.Faithful(this, RandomNameGenerator.randomNameOfBoy()));
        }
    }

    public class Faithful extends SubPopulation.SubType {
        public Faithful(ThreadGroup group, String name) {
            super(group, name + " (Faithful)");
        }

        @Override
        public void run() {
        }
    }
}
