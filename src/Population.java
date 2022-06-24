import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

public class Population extends ThreadGroup {
    public final int a; // the evolutionary benefit for having a baby
    public final int b; // the cost of parenting a child
    public final int c; // the cost of courtship
    public final boolean noise;
    private final ManPopulation manPopulation;
    private final WomanPopulation womanPopulation;
    public LinkedList<SubPopulation.SubType> initialPopulationList;
    public int ID = 0;
    public int size;
    public int infMor = 1;
    public int startCredit = 0;
    public int lifePoints = 5;
    public int noiseChance = 50;
    public SynchronousQueue<SubWomanPopulation.WomanSubType> womenQueue;
    public boolean sterility = false;
    public ExecutorService world = Executors.newFixedThreadPool(2000);

    public Population(String name, int size, int a, int b, int c, boolean noise) {
        super(name);
        this.size = size;
        this.initialPopulationList = new LinkedList<>();
        this.womenQueue = new SynchronousQueue<>();
        this.womanPopulation = new WomanPopulation(this, "woman population", size / 2);
        this.manPopulation = new ManPopulation(this, "man population", size / 2);
        this.a = a;
        this.b = b;
        this.c = c;
        this.noise = noise;
    }

    public Population(String name, int numberOfCoy, int numberOfFast, int numberOfFaithful, int numberOfPhilanderers, int a, int b, int c, boolean noise) {
        super(name);
        this.size = numberOfCoy + numberOfFaithful + numberOfFast + numberOfPhilanderers;
        this.initialPopulationList = new LinkedList<>();
        this.womenQueue = new SynchronousQueue<>();
        this.womanPopulation = new WomanPopulation(this, "woman population", numberOfCoy, numberOfFast);
        this.manPopulation = new ManPopulation(this, "man population", numberOfFaithful, numberOfPhilanderers);
        this.a = a;
        this.b = b;
        this.c = c;
        this.noise = noise;
    }

    public Population(String name, int numberOfCoy, int numberOfFast, int numberOfFaithful, int numberOfPhilanderers, int infantMortality, int startCredit, int lifePoints, int a, int b, int c, int noiseChance) {
        super(name);
        this.size = numberOfCoy + numberOfFaithful + numberOfFast + numberOfPhilanderers;
        this.initialPopulationList = new LinkedList<>();
        this.womenQueue = new SynchronousQueue<>();
        this.womanPopulation = new WomanPopulation(this, "woman population", numberOfCoy, numberOfFast);
        this.manPopulation = new ManPopulation(this, "man population", numberOfFaithful, numberOfPhilanderers);
        this.infMor = infantMortality;
        this.startCredit = startCredit;
        this.lifePoints = lifePoints;
        this.noiseChance = noiseChance;
        this.a = a;
        this.b = b;
        this.c = c;
        this.noise = true;
    }

    public Population(String name, int numberOfCoy, int numberOfFast, int numberOfFaithful, int numberOfPhilanderers, int infantMortality, int startCredit, int lifePoints, int a, int b, int c) {
        super(name);
        this.size = numberOfCoy + numberOfFaithful + numberOfFast + numberOfPhilanderers;
        this.initialPopulationList = new LinkedList<>();
        this.womenQueue = new SynchronousQueue<>();
        this.womanPopulation = new WomanPopulation(this, "woman population", numberOfCoy, numberOfFast);
        this.manPopulation = new ManPopulation(this, "man population", numberOfFaithful, numberOfPhilanderers);
        this.infMor = infantMortality;
        this.startCredit = startCredit;
        this.lifePoints = lifePoints;
        this.a = a;
        this.b = b;
        this.c = c;
        this.noise = false;
    }

    public synchronized int getNoiseChance() {
        return noiseChance;
    }

    public synchronized int getInfantMortality() {
        return infMor;
    }

    public ManPopulation getManPopulation() {
        return manPopulation;
    }

    public WomanPopulation getWomanPopulation() {
        return womanPopulation;
    }

    public float getFaithfulState() {
        return ((float) this.manPopulation.faithfulPopulation.size / (float) this.size);
    }

    public float getPhilanderersState() {
        return ((float) this.manPopulation.philandererPopulation.size / (float) this.size);
    }

    public float getFastState() {
        return ((float) this.womanPopulation.fastPopulation.size / (float) this.size);
    }

    public float getCoyState() {
        return ((float) this.womanPopulation.coyPopulation.size / (float) this.size);
    }

    public HashMap getGlobalState() {
        HashMap<String, Float> globalState = new HashMap<>();
        globalState.put("Faithful", this.getFaithfulState());
        globalState.put("Philanderers", this.getPhilanderersState());
        globalState.put("Fast", this.getFastState());
        globalState.put("Coy", this.getCoyState());

        return globalState;
    }

    public HashMap getGlobalStateToPrint() {
        HashMap<String, Float> globalState = new HashMap<>();
        globalState.put("Faithful:\t\t", this.getFaithfulState());
        globalState.put("Philanderers:\t", this.getPhilanderersState());
        globalState.put("Fast:\t\t\t", this.getFastState());
        globalState.put("Coy:\t\t\t", this.getCoyState());

        return globalState;
    }

    public HashMap getPopulationsToPrint() {
        HashMap<String, Integer> globalState = new HashMap<>();
        globalState.put("Faithful:\t\t", this.manPopulation.faithfulPopulation.size);
        globalState.put("Philanderers:\t", this.manPopulation.philandererPopulation.size);
        globalState.put("Fast:\t\t\t", this.womanPopulation.fastPopulation.size);
        globalState.put("Coy:\t\t\t", this.womanPopulation.coyPopulation.size);

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
        return this.manPopulation.faithfulPopulation.size + this.manPopulation.philandererPopulation.size + this.womanPopulation.fastPopulation.size + this.womanPopulation.coyPopulation.size;
    }
}
