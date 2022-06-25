import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.SynchronousQueue;

public class Population extends ThreadGroup{
    private final ManPopulation manPopulation;
    private final WomanPopulation womanPopulation;
    public LinkedList<SubPopulation.SubType> initialPopulationList;
    public int ID = 0;
    public int size;
    public int infMor = 1;
    public int startCredit = 0;
    public int life = 5;
    public int noiseChance = 50;
    public SynchronousQueue<SubWomanPopulation.WomanSubType> womenQueue;
    public final int a; // the evolutionary benefit for having a baby
    public final int b; // the cost of parenting a child
    public final int c; // the cost of courtship
    public final boolean noise;
    public boolean sterility = false;

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

    public Population (String name, int coy, int fast, int faith, int phil, int infMor, int startCredit, int life, int a,int b, int c, int noiseChance) {
        super(name);
        this.size = coy+faith+fast+phil;
        this.initialPopulationList = new LinkedList<>();
        this.womenQueue = new SynchronousQueue<>();
        this.womanPopulation = new WomanPopulation(this, "woman population", coy, fast);
        this.manPopulation = new ManPopulation(this, "man population", faith, phil);
        this.infMor = infMor;
        this.startCredit = startCredit;
        this.life = life;
        this.noiseChance = noiseChance;
        this.a = a;
        this.b = b;
        this.c = c;
        this.noise = true;
    }

    public Population (String name, int coy, int fast, int faith, int phil, int infMor, int startCredit, int life, int a,int b, int c) {
        super(name);
        this.size = coy+faith+fast+phil;
        this.initialPopulationList = new LinkedList<>();
        this.womenQueue = new SynchronousQueue<>();
        this.womanPopulation = new WomanPopulation(this, "woman population", coy, fast);
        this.manPopulation = new ManPopulation(this, "man population", faith, phil);
        this.infMor = infMor;
        this.startCredit = startCredit;
        this.life = life;
        this.a = a;
        this.b = b;
        this.c = c;
        this.noise = false;
    }

    public synchronized int getNoiseChance() {
        return noiseChance;
    }

    public synchronized int getInfMor() {
        return infMor;
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
        return ((float)this.manPopulation.philandererPopulation.size/(float)manPopulation.size);
    }

    public float getFastState() {
        return ((float)this.womanPopulation.fastPopulation.size/(float)this.size);
    }

    public float getCoyState() {
        return ((float)this.womanPopulation.coyPopulation.size/(float)this.size);
    }



    public float getFaithfulGen() {
        return ((float)this.manPopulation.faithfulPopulation.size/(float)manPopulation.size);
    }

    public float getPhilanderersGen() {
        return ((float)this.manPopulation.philandererPopulation.size/(float)manPopulation.size);
    }

    public float getFastGen() {
        return ((float)this.womanPopulation.fastPopulation.size/(float)womanPopulation.size);
    }

    public float getCoyGen() {
        return ((float)this.womanPopulation.coyPopulation.size/(float)womanPopulation.size);
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

    public HashMap getPopulations() {
        HashMap<String, Integer> globalState = new HashMap<>();
        globalState.put("Faithful", this.manPopulation.faithfulPopulation.size);
        globalState.put("Philanderers", this.manPopulation.philandererPopulation.size);
        globalState.put("Fast", this.womanPopulation.fastPopulation.size);
        globalState.put("Coy", this.womanPopulation.coyPopulation.size);

        return globalState;
    }

    public HashMap getPopulationss() {
        HashMap<String, Integer> globalState = new HashMap<>();
        globalState.put("Faithful", this.manPopulation.faithfulPopulation.size);
        globalState.put("Philanderers", this.manPopulation.philandererPopulation.size);
        globalState.put("Fast", this.womanPopulation.fastPopulation.size);
        globalState.put("Coy", this.womanPopulation.coyPopulation.size);
        globalState.put("Men", this.manPopulation.size);
        globalState.put("Women", this.womanPopulation.size);
        return globalState;
    }

    public HashMap getGenderState() {
        HashMap<String, Float> globalState = new HashMap<>();
        globalState.put("Faithful", this.getFaithfulGen());
        globalState.put("Philanderers", this.getPhilanderersGen());
        globalState.put("Fast", this.getFastGen());
        globalState.put("Coy", this.getCoyGen());

        return globalState;
    }

    public int totalSize() {
        return this.manPopulation.faithfulPopulation.size+this.manPopulation.philandererPopulation.size+this.womanPopulation.fastPopulation.size+this.womanPopulation.coyPopulation.size;
    }

    public synchronized void modifySterility(boolean newSterilityValue, boolean finishedNewValue) {
        if (!GrimReaper.finished) {
            sterility = newSterilityValue;
            GrimReaper.finished = finishedNewValue;
        }
    }
}
