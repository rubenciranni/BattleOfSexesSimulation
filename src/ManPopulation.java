public class ManPopulation extends ThreadGroup{
    private FaithfulPopulation faithfulPopulation;
    private PhilandererPopulation philandererPopulation;
    private int size;

    public ManPopulation(Population parent, String name, int size) {
        super(parent, name);
        this.size = size;
        this.faithfulPopulation = new FaithfulPopulation(this, "faithful population", size/2);
        this.philandererPopulation= new PhilandererPopulation(this, "philanderer population", size/2);
    }
}
