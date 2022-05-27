public class WomanPopulation extends ThreadGroup {
    public CoyPopulation coyPopulation;
    public FastPopulation fastPopulation;
    public int size;

    public WomanPopulation(Population parent, String name, int initialSize) {
        super(parent, name);
        this.coyPopulation = new CoyPopulation(this, "coy population", initialSize / 2);
        this.fastPopulation = new FastPopulation(this, "fast population", initialSize / 2);
    }

    public WomanPopulation(Population parent, String name, int numberOfCoy, int numberOfFast) {
        super(parent, name);
        this.coyPopulation = new CoyPopulation(this, "coy population", numberOfCoy);
        this.fastPopulation = new FastPopulation(this, "fast population", numberOfFast);
    }

    public CoyPopulation getCoyPopulation() {
        return coyPopulation;
    }

    public FastPopulation getFastPopulation() {
        return fastPopulation;
    }
}
