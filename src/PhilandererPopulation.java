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
            this.setName(this.getName() + " (Philanderer)");
            System.out.println(this.getName() + " " + this.getId());
        }

        @Override
        public String getSubType() {
            return "Philanderer";
        }
        @Override
        public PhilandererPopulation getPopulation() {
            return PhilandererPopulation.this;
        }

        @Override
        public void updateCredit() {
            //TODO implement updateCredit
        }

        @Override
        public void leaveOrStay(SubWomanPopulation.WomanSubType woman) {
            this.currentWoman = null;
            this.isSingle = true;
            woman.currentMan = null;
            woman.isSingle = true;

        }
    }
}
