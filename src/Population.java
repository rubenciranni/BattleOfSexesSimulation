import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Population {
    private boolean sterility = false;
    public LinkedList initialPopulation;
    private int numberOfIndividuals;
    private int numberOfFaithfull;
    private int numberOfPhilanderers;
    private int numberOfFast;
    private int numberOfCoy;

    public Population(LinkedList initialPopulation, int numberOfIndividuals,
                      int numberOfFaithfull, int numberOfPhilanderers, int numberOfFast, int numberOfCoy) {
        this.sterility = false;
        this.initialPopulation = initialPopulation;
        this.numberOfIndividuals = numberOfIndividuals;
        this.numberOfFaithfull = numberOfFaithfull;
        this.numberOfPhilanderers = numberOfPhilanderers;
        this.numberOfFast = numberOfFast;
        this.numberOfCoy = numberOfCoy;
    }

    public double getFaithfullState() {
        return this.numberOfFaithfull/this.numberOfIndividuals;
    }

    public double getPhilanderersState() {
        return this.numberOfPhilanderers/this.numberOfIndividuals;
    }

    public double getFastState() {
        return this.numberOfFast/this.numberOfIndividuals;
    }

    public double getCoyState() {
        return this.numberOfCoy/this.numberOfIndividuals;
    }

    public HashMap getGlobalState() {
        HashMap<String, Double> globalState = new HashMap<>();
        globalState.put("Faithfull", this.getFaithfullState());
        globalState.put("Philanderers", this.getPhilanderersState());
        globalState.put("Fast", this.getFastState());
        globalState.put("Coy", this.getCoyState());

        return globalState;
    }

    public void newborn(Man man, Woman woman){
        if (!sterility){
            numberOfIndividuals++;
            if (man.getClass().getName() == "Faithful")  {
                int m = 0;
            }
            else {
                int m = 1;
            }

            if (woman.getClass().getName() == "Coy")  {
                int w = 0;
            }
            else {
                int w = 1;
            }

            Random rand = new Random();
            boolean sex = rand.nextBoolean();
            int type = Math.round();
            if (sex) {

            }
            else {

            }
        }

    }

    public void death() {
        numberOfIndividuals--;
    }
}
