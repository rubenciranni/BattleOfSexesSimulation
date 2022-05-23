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
            this.setName(this.getName() + " (Fast)");
            System.out.println(this.getName() + " " + this.getId());
        }

        @Override
        public String getSubType() {
            return "Fast";
        }

        @Override
        public FastPopulation getPopulation() {
            return FastPopulation.this;
        }

        @Override
        public void updateCredit(SubType partner) {
            //TODO implement updateCredit
        }

        @Override
        public boolean accepted(SubManPopulation.ManSubType man) {
            return this.isSingle;
        }

    }
}
