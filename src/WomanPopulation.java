public class WomanPopulation extends ThreadGroup{
    private CoyPopulation coyPopulation;
    private FastPopulation fastPopulation;
    private int size;

    public WomanPopulation(Population parent, String name, int size) {
        super(parent, name);
        this.size = size;
        this.coyPopulation = new CoyPopulation(this, "coy population", size/2);
        this.fastPopulation = new FastPopulation(this, "fast population", size/2);
    }
}
