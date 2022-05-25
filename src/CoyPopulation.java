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
            this.setName(this.getName() + " (Coy)");
            System.out.println(this.getName() + " " + this.getId());
        }

        @Override
        public String getSubType() {
            return "Coy";
        }

        @Override
        public CoyPopulation getPopulation() {
            return CoyPopulation.this;
        }

        @Override
        public void updateCredit(SubType partner) {

        }

        @Override
        public boolean accepted(SubManPopulation.ManSubType man) {
            return this.isSingle && (man.getSubType() == "Faithful");
        }

    }

}
