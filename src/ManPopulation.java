public class ManPopulation extends ThreadGroup {
    public FaithfulPopulation faithfulPopulation;
    public PhilandererPopulation philandererPopulation;
    public int size;

    public ManPopulation(Population parent, String name, int initialSize) {
        super(parent, name);
        this.faithfulPopulation = new FaithfulPopulation(this, "faithful population", initialSize / 2);
        this.philandererPopulation = new PhilandererPopulation(this, "philanderer population", initialSize / 2);
    }

    public ManPopulation(Population parent, String name, int numberOfFaithful, int numberOfPhilanderers) {
        super(parent, name);
        this.faithfulPopulation = new FaithfulPopulation(this, "faithful population", numberOfFaithful);
        this.philandererPopulation = new PhilandererPopulation(this, "philanderer population", numberOfPhilanderers);
    }

}
