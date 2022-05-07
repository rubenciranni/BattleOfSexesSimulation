public class CoyPopulation extends ThreadGroup{
    private int size;
    private final Population population = (Population) this.getParent().getParent();

    public CoyPopulation(WomanPopulation parent, String name, int size) {
        super(parent, name);
        this.size = size;

        for(int i = 0; i < size; i++) {
            population.initialPopulationList.add(new Coy(this, RandomNameGenerator.randomNameOfGirl()));
        }
    }

    public class Coy extends Thread{
        private final int id;
        public Coy(ThreadGroup group, String name) {
            super(group, name  + " (Coy)");
            this.id = ++population.ID;
        }

        @Override
        public long getId() {
            return this.id;
        }

        @Override
        public void run() {
        }
    }

}
