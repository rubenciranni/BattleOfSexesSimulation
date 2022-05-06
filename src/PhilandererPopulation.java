public class PhilandererPopulation extends ThreadGroup{
    private int size;

    public PhilandererPopulation(ManPopulation parent, String name, int size) {
        super(parent, name);
        this.size = size;

        for(int i = 0; i < size; i++) {
            new PhilandererPopulation.Philanderer(this, "P" + i);
        }
    }

    public class Philanderer extends Thread{
        public Philanderer(ThreadGroup group, String name) {
            super(group, name);
        }

        @Override
        public void run() {
        }
    }
}
