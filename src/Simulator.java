public class Simulator {
    private final int a; // the evolutionary benefit for having a baby
    private final int b; // the cost of parenting a child
    private final int c; // the cost of courtship
    private final boolean sterility = false;
    private final boolean noise = false;
    private final Population population;

    public Simulator(Population population, int a, int b, int c) {
        this.population = population;
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
