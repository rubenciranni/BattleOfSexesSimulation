public class CoyPopulation extends SubWomanPopulation {
    public CoyPopulation(ThreadGroup parent, String name, int size) {
        super(parent, name, size);
    }

    @Override
    public void addToInitialPopulation() {
        for (int i = 0; i < this.size; i++) {
            population.initialPopulationList.add(new Coy(this));
        }
    }

    public class Coy extends WomanSubType {
        public Coy(ThreadGroup group) {
            super(group);
        }

        @Override
        public void generateOffspringWith(SubManPopulation.ManSubType man) {

        }

        @Override
        public boolean accepted(SubManPopulation.ManSubType man) {
            return man.getClass().getName() == "Faithful" && this.isSingle;
        }

        @Override
        public CoyPopulation getPopulation() {
            return CoyPopulation.this;
        }

        @Override
        public void updateCredit() {

        }
    }

}
