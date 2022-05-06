public class CoyPopulation extends ThreadGroup{
    private int size;

    public CoyPopulation(WomanPopulation parent, String name, int size) {
        super(parent, name);
        this.size = size;

        for(int i = 0; i < size; i++) {
            Population population = (Population) this.getParent().getParent();
            population.initialPopulationList.add(new Coy(this, RandomNameGenerator.randomNameOfGirl()));
        }
    }

    public class Coy extends Thread{
        private final int id;
        public Coy(ThreadGroup group, String name) {
            super(group, name  + " (Coy)");
            Population population =(Population) (CoyPopulation.this).getParent().getParent();
            this.id=population.ID++;
        }

        @Override
        public void run() {
        }
    }

}
