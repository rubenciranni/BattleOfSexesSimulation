public class FastPopulation extends SubWomanPopulation {
    public FastPopulation(ThreadGroup parent, String name, int size) {
        super(parent, name, size);
    }

    @Override
    public void addToInitialPopulation() {
        for (int i = 0; i < this.size; i++) {
            population.initialPopulationList.add(new FastPopulation.Fast(this));
        }
    }

    public class Fast extends WomanSubType {
        public Fast(ThreadGroup group) {
            super(group);
        }

        @Override
        public void generateOffspringWith(SubManPopulation.ManSubType man) {

        }

        @Override
        public boolean accepted(SubManPopulation.ManSubType man) {
            return true;
        }

        @Override
        public FastPopulation getPopulation() {
            return FastPopulation.this;
        }

        @Override
        public void updateCredit() {

        }
    }
}
