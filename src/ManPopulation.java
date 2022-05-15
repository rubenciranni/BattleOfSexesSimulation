public class ManPopulation extends ThreadGroup{
    public FaithfulPopulation faithfulPopulation;
    public PhilandererPopulation philandererPopulation;
    public int size;

    public ManPopulation(Population parent, String name, int size) {
        super(parent, name);
        this.size = size;
        this.faithfulPopulation = new FaithfulPopulation(this, "faithful population", size/2);
        this.philandererPopulation= new PhilandererPopulation(this, "philanderer population", size/2);
    }

    public ManPopulation(Population parent, String name, int numberOfFaithful, int numberOfPhilanderers) {
        super(parent, name);
        this.size = numberOfFaithful+numberOfPhilanderers;
        this.faithfulPopulation = new FaithfulPopulation(this, "faithful population", numberOfFaithful);
        this.philandererPopulation= new PhilandererPopulation(this, "philanderer population", numberOfPhilanderers);
    }

}
