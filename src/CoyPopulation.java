public class CoyPopulation extends SubPopulation{
    public CoyPopulation(ThreadGroup parent, String name, int size) {
        super(parent, name, size);
    }

    @Override
    public void addToInitialPopulation() {
        for(int i = 0; i < this.size; i++) {
            population.initialPopulationList.add(new Coy(this, RandomNameGenerator.randomNameOfGirl()));
        }
    }

    public class Coy extends SubPopulation.SubType {
        public Coy(ThreadGroup group, String name) {
            super(group, name + " (Coy)");
        }

        @Override
        public void run() {
        }
    }

}
