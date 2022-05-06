public class PhilandererPopulation extends ThreadGroup{
    private int size;

    public PhilandererPopulation(ManPopulation parent, String name, int size) {
        super(parent, name);
        this.size = size;

        for(int i = 0; i < size; i++) {
            Population population = (Population) this.getParent().getParent();
            population.initialPopulationList.add(new PhilandererPopulation.Philanderer(this, RandomNameGenerator.randomNameOfBoy()));
        }
    }

    public class Philanderer extends Thread{
        private final int id;
        public Philanderer(ThreadGroup group, String name) {
            super(group, name +  " (Philanderer)");
            Population population =(Population) (PhilandererPopulation.this).getParent().getParent();
            this.id=population.ID++;
        }

        @Override
        public void run() {
        }
    }
}
