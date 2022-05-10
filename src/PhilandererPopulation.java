public class PhilandererPopulation extends SubPopulation{
    public PhilandererPopulation(ThreadGroup parent, String name, int size) {
        super(parent, name, size);
    }

    @Override
    public void addToInitialPopulation() {
        for(int i = 0; i < this.size; i++) {
            population.initialPopulationList.add(new PhilandererPopulation.Philanderer(this, RandomNameGenerator.randomNameOfBoy()));
        }
    }

    public class Philanderer extends SubPopulation.SubType {
        public Philanderer(ThreadGroup group, String name) {
            super(group, name + " (Philanderer)");
        }

        @Override
        public void run() {
        }
    }
}
