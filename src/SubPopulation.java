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
        private final int id;
        int credit = 100;
        int lifePoints = 5;
        public boolean isSingle = true;

        public SubType(ThreadGroup group, String name) {
            super(group, name);
            this.id = ++population.ID;
        }

        public abstract String getSubType();

        public <E extends SubPopulation> E getPopulation() {
            return (E) SubPopulation.this;
        }

        public abstract void updateCredit(SubType partner);

        public synchronized void hey() {
            notify();
        }

        @Override
        public long getId() {
            return this.id;
        }

        @Override
        public abstract void run();

    }
}
