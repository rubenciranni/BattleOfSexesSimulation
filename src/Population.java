import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.SynchronousQueue;

public class Population extends ThreadGroup{
    private final ManPopulation manPopulation;
    private final WomanPopulation womanPopulation;
    public LinkedList<SubPopulation.SubType> initialPopulationList;
    public int ID = 0;
    public int size;
    public SynchronousQueue<SubWomanPopulation.WomanSubType> womenQueue;
    public final int a; // the evolutionary benefit for having a baby
    public final int b; // the cost of parenting a child
    public final int c; // the cost of courtship
    public final boolean noise;

    public Population(String name, int size, int a, int b, int c, boolean noise) {
        super(name);
        this.size = size;
        this.initialPopulationList = new LinkedList<>();
        this.womenQueue = new SynchronousQueue<>();
        this.womanPopulation = new WomanPopulation(this, "woman population", size/2);
        this.manPopulation = new ManPopulation(this, "man population", size/2);
        this.a = a;
        this.b = b;
        this.c = c;
        this.noise = noise;
    }

    public Population(String name, int numberOfCoy, int numberOfFast, int numberOfFaithful, int numberOfPhilanderers,
                      int a, int b, int c, boolean noise) {
        super(name);
        this.size = numberOfCoy+numberOfFaithful+numberOfFast+numberOfPhilanderers;
        this.initialPopulationList = new LinkedList<>();
        this.womenQueue = new SynchronousQueue<>();
        this.womanPopulation = new WomanPopulation(this, "woman population", numberOfCoy, numberOfFast);
        this.manPopulation = new ManPopulation(this, "man population", numberOfFaithful, numberOfPhilanderers);
        this.a = a;
        this.b = b;
        this.c = c;
        this.noise = noise;
    }

    public ManPopulation getManPopulation() {
        return manPopulation;
    }

    public WomanPopulation getWomanPopulation() {
        return womanPopulation;
    }

    public float getFaithfulState() {
        return ((float)this.manPopulation.faithfulPopulation.size/(float)this.size);
    }

    public float getPhilanderersState() {
        return ((float)this.manPopulation.philandererPopulation.size/(float)this.size);
    }

    public float getFastState() {
        return ((float)this.womanPopulation.fastPopulation.size/(float)this.size);
    }

    public float getCoyState() {
        return ((float)this.womanPopulation.coyPopulation.size/(float)this.size);
    }

    public HashMap getGlobalState() {
        HashMap<String, Float> globalState = new HashMap<>();
        globalState.put("Faithful", this.getFaithfulState());
        globalState.put("Philanderers", this.getPhilanderersState());
        globalState.put("Fast", this.getFastState());
        globalState.put("Coy", this.getCoyState());

        return globalState;
    }

    public HashMap getPopulations() {
        HashMap<String, Integer> globalState = new HashMap<>();
        globalState.put("Faithful", this.manPopulation.faithfulPopulation.size);
        globalState.put("Philanderers", this.manPopulation.philandererPopulation.size);
        globalState.put("Fast", this.womanPopulation.fastPopulation.size);
        globalState.put("Coy", this.womanPopulation.coyPopulation.size);

        return globalState;
    }

    public int totalSize() {
        return this.manPopulation.faithfulPopulation.size+this.manPopulation.philandererPopulation.size+this.womanPopulation.fastPopulation.size+this.womanPopulation.coyPopulation.size;
    }
}
