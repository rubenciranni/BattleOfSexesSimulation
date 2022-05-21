public class WomanPopulation extends ThreadGroup {
    public CoyPopulation coyPopulation;
    public FastPopulation fastPopulation;
    public int size;

    public WomanPopulation(Population parent, String name, int size) {
        super(parent, name);
        this.size = size;
        this.coyPopulation = new CoyPopulation(this, "coy population", size / 2);
        this.fastPopulation = new FastPopulation(this, "fast population", size / 2);
    }

    public WomanPopulation(Population parent, String name, int numberOfCoy, int numberOfFast) {
        super(parent, name);
        this.size = numberOfCoy + numberOfFast;
        this.coyPopulation = new CoyPopulation(this, "coy population", numberOfCoy);
        this.fastPopulation = new FastPopulation(this, "fast population", numberOfFast);
    }
}
