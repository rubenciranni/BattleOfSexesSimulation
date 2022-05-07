public class FastPopulation extends ThreadGroup{
    private int size;
    private final Population population = (Population) this.getParent().getParent();

    public FastPopulation(WomanPopulation parent, String name, int size) {
        super(parent, name);
        this.size = size;

        for(int i = 0; i < size; i++) {
            population.initialPopulationList.add(new FastPopulation.Fast(this, RandomNameGenerator.randomNameOfGirl()));
        }
    }

    public class Fast extends Thread{
        private final int id;
        public Fast(ThreadGroup group, String name) {
            super(group, name + " (Fast)");
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
