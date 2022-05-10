public class FastPopulation extends SubPopulation{
    public FastPopulation(ThreadGroup parent, String name, int size) {
        super(parent, name, size);
    }

    @Override
    public void addToInitialPopulation() {
        for(int i = 0; i < this.size; i++) {
            population.initialPopulationList.add(new FastPopulation.Fast(this, RandomNameGenerator.randomNameOfGirl()));
        }
    }

    public class Fast extends SubPopulation.SubType {
        public Fast(ThreadGroup group, String name) {
            super(group, name + " (Fast)");
        }

        @Override
        public void run() {
        }
    }
}
