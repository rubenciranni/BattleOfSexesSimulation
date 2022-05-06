import java.util.HashMap;
import java.util.LinkedList;

public class Population extends ThreadGroup{
    private ManPopulation manPopulation;
    private WomanPopulation womanPopulation;
    public LinkedList<Thread> initialPopulationList;
    private int size;

    public Population(String name, int size) {
        super(name);
        this.size = size;
        this.initialPopulationList = new LinkedList<>();

        this.womanPopulation = new WomanPopulation(this, "woman population", size/2);
        this.manPopulation = new ManPopulation(this, "man population", size/2);
    }

    public double getFaithfulState() {
    }

    public double getPhilanderersState() {
    }

    public double getFastState() {
    }

    public double getCoyState() {
    }

    public HashMap getGlobalState() {
        HashMap<String, Double> globalState = new HashMap<>();
        globalState.put("Faithful", this.getFaithfulState());
        globalState.put("Philanderers", this.getPhilanderersState());
        globalState.put("Fast", this.getFastState());
        globalState.put("Coy", this.getCoyState());

        return globalState;
    }
}
