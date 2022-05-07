public class FastPopulation extends ThreadGroup{
    private int size;

    public FastPopulation(WomanPopulation parent, String name, int size) {
        super(parent, name);
        this.size = size;

        for(int i = 0; i < size; i++) {
            Population population = (Population) this.getParent().getParent();
            population.initialPopulationList.add(new FastPopulation.Fast(this, RandomNameGenerator.randomNameOfGirl()));
        }
    }

    public class Fast extends Thread{
        private final int id;
        public Fast(ThreadGroup group, String name) {
            super(group, name + " (Fast)");
            Population population = (Population) (FastPopulation.this).getParent().getParent();
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
