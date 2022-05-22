public class FaithfulPopulation extends SubManPopulation {
    public FaithfulPopulation(ThreadGroup parent, String name, int size) {
        super(parent, name, size);
    }

    @Override
    public void addToInitialPopulation() {
        for (int i = 0; i < this.size; i++) {
            population.initialPopulationList.add(new FaithfulPopulation.Faithful(this));
        }
    }

    public class Faithful extends ManSubType {
        public Faithful(ThreadGroup group) {
            super(group);
        }

        @Override
        public FaithfulPopulation getPopulation() {
            return FaithfulPopulation.this;
        }

        @Override
        public void updateCredit() {

        }
    }
}
