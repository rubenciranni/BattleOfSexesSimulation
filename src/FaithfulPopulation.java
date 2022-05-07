public class FaithfulPopulation extends ThreadGroup{
    private int size;
    private final Population population = (Population) this.getParent().getParent();

    public FaithfulPopulation(ManPopulation parent, String name, int size) {
        super(parent, name);
        this.size = size;

        for(int i = 0; i < size; i++) {
            population.initialPopulationList.add(new FaithfulPopulation.Faithful(this, RandomNameGenerator.randomNameOfBoy()));
        }
    }

    public class Faithful extends Thread{
        private final int id;
        public Faithful(ThreadGroup group, String name) {
            super(group, name + " (Faithful)");
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
