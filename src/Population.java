public class Population extends ThreadGroup{
    private ManPopulation manPopulation;
    private WomanPopulation womanPopulation;
    private int size;

    public Population(String name, int size) {
        super(name);
        this.size = size;
        this.womanPopulation = new WomanPopulation(this, "woman population", size/2);
        this.manPopulation = new ManPopulation(this, "man population", size/2);
    }
}
