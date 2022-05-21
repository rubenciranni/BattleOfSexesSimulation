public class PhilandererPopulation extends SubManPopulation {
    public PhilandererPopulation(ThreadGroup parent, String name, int size) {
        super(parent, name, size);
    }

    @Override
    public void addToInitialPopulation() {
        for (int i = 0; i < this.size; i++) {
            population.initialPopulationList.add(new PhilandererPopulation.Philanderer(this));
        }
    }

    public class Philanderer extends ManSubType {
        public Philanderer(ThreadGroup group) {
            super(group);
        }

        @Override
        public PhilandererPopulation getPopulation() {
            return PhilandererPopulation.this;
        }
    }
}
