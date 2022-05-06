public class FastPopulation extends ThreadGroup{
    private int size;

    public FastPopulation(WomanPopulation parent, String name, int size) {
        super(parent, name);
        this.size = size;

        for(int i = 0; i < size; i++) {
            new FastPopulation.Fast(this, "S" + i);
        }
    }

    public class Fast extends Thread{
        public Fast(ThreadGroup group, String name) {
            super(group, name);
        }

        @Override
        public void run() {
        }
    }
}
