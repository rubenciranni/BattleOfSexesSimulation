public class CoyPopulation extends ThreadGroup{
    private int size;

    public CoyPopulation(WomanPopulation parent, String name, int size) {
        super(parent, name);
        this.size = size;

        for(int i = 0; i < size; i++) {
            new Coy(this, NameGenerator.generateCoyName(i));
        }
    }

    public class Coy extends Thread{
        public Coy(ThreadGroup group, String name) {
            super(group, name);
        }

        @Override
        public void run() {
        }
    }

}
