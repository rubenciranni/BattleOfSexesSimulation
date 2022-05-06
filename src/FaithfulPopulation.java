public class FaithfulPopulation extends ThreadGroup{
    private int size;

    public FaithfulPopulation(ManPopulation parent, String name, int size) {
        super(parent, name);
        this.size = size;

        for(int i = 0; i < size; i++) {
            new FaithfulPopulation.Faithful(this, NameGenerator.generateFaithfulName(i));
        }
    }

    public class Faithful extends Thread{
        public Faithful(ThreadGroup group, String name) {
            super(group, name);
        }

        @Override
        public void run() {
        }
    }
}
