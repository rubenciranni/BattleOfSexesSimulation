import java.util.HashMap;
import java.util.LinkedList;

public class Population extends ThreadGroup{
    private ManPopulation manPopulation;
    private WomanPopulation womanPopulation;
    public LinkedList<Thread> initialPopulationList;
    public int ID=0;
    private int size;

    public Population(String name, int size) {
        super(name);
        this.size = size;
        this.initialPopulationList = new LinkedList<>();

        this.womanPopulation = new WomanPopulation(this, "woman population", size/2);
        this.manPopulation = new ManPopulation(this, "man population", size/2);
    }

    public float getFaithfulState() {
        return ((float)this.manPopulation.faithfulPopulation.activeCount()/(float)this.activeCount());
    }

    public float getPhilanderersState() {
        return ((float)this.manPopulation.philandererPopulation.activeCount()/(float)this.activeCount());
    }

    public float getFastState() {
        return ((float)this.womanPopulation.fastPopulation.activeCount()/(float)this.activeCount());
    }

    public float getCoyState() {
        return ((float)this.womanPopulation.coyPopulation.activeCount()/(float)this.activeCount());
    }

    public HashMap getGlobalState() {
        HashMap<String, Float> globalState = new HashMap<>();
        globalState.put("Faithful", this.getFaithfulState());
        globalState.put("Philanderers", this.getPhilanderersState());
        globalState.put("Fast", this.getFastState());
        globalState.put("Coy", this.getCoyState());

        return globalState;
    }
}
