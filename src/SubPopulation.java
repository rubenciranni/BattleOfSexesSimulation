public abstract class SubPopulation extends ThreadGroup {
    public int size;
    public final Population population = (Population) this.getParent().getParent();

    public SubPopulation(ThreadGroup parent, String name, int size) {
        super(parent, name);
        this.size = size;

        addToInitialPopulation();
    }

    public void increaseSize() {
         size++;
         population.size++;
    }

    public abstract void addToInitialPopulation();

    public abstract class SubType extends Thread {
        public final int id;
        int credit = 100;
        public boolean isSingle = true;

        public SubType(ThreadGroup group, String name) {
            super(group, name);
            this.id = ++population.ID;
            System.out.println("new" + group.getName());
        }

        public <E extends SubPopulation> E getPopulation() {
            return (E) SubPopulation.this;
        }

        public abstract void updateCredit();

        @Override
        public long getId() {
            return this.id;
        }

        @Override
        public abstract void run();

    }
}
