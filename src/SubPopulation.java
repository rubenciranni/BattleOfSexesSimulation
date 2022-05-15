public abstract class SubPopulation extends ThreadGroup {
    public int size;
    public final Population population = (Population) this.getParent().getParent();

    public SubPopulation(ThreadGroup parent, String name, int size) {
        super(parent, name);
        this.size = size;

        addToInitialPopulation();
    }
/*
    public <E extends ThreadGroup> void increaseSize() {
         size++;
         (E)(this.getParent()).size++;
         population.size++;
    }
*/
    public abstract void addToInitialPopulation();

    public abstract class SubType extends Thread {
        public final int id;
        public SubType(ThreadGroup group, String name) {
            super(group, name);
            this.id = ++population.ID;
        }

        public <E extends SubPopulation> E getPopulation() {
            return (E) SubPopulation.this;
        }

        @Override
        public long getId() {
            return this.id;
        }

        @Override
        public abstract void run();
    }
}
