import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Population {
    private boolean sterility = false;
    private boolean rebellion = false;
    public LinkedList initialPopulation;
    private int numberOfIndividuals;
    private int numberOfFaithful;
    private int numberOfPhilanderers;
    private int numberOfFast;
    private int numberOfCoy;

    public Population(LinkedList initialPopulation,
                      int numberOfFaithful, int numberOfPhilanderers, int numberOfFast, int numberOfCoy) {
        this.sterility = false;
        this.initialPopulation = initialPopulation;
        this.numberOfIndividuals = numberOfCoy+numberOfFaithful+numberOfPhilanderers+numberOfFast;
        this.numberOfFaithful = numberOfFaithful;
        this.numberOfPhilanderers = numberOfPhilanderers;
        this.numberOfFast = numberOfFast;
        this.numberOfCoy = numberOfCoy;
    }

    public double getFaithfulState() {
        return this.numberOfFaithful/this.numberOfIndividuals;
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
        globalState.put("Faithfull", this.getFaithfulState());
        globalState.put("Philanderers", this.getPhilanderersState());
        globalState.put("Fast", this.getFastState());
        globalState.put("Coy", this.getCoyState());

        return globalState;
    }

    public Person newborn(Man man, Woman woman){
        if (!sterility){
            numberOfIndividuals++;
            Random rand = new Random();
            boolean sex = rand.nextBoolean();

            if (sex) {
                boolean m = false;
                if (man.getClass().getName() == "Faithful")  {
                    m = true;
                }
                 if (rebellion && rand.nextInt(0, 50) == 49) {
                     m = !m;
                 }
                 if (m) {
                    return new Faithful();
                 }
                return new Philanderers();
            }
            else {
                boolean w = false;
                if (man.getClass().getName() == "Coy") {
                    w = true;
                }
                if (rebellion && rand.nextInt(0, 50) == 49) {
                    w = !w;
                }
                if (w) {
                    return new Coy();
                }
                return new Fast();
            }
        }

        return null;
    }

    public void death() {
        numberOfIndividuals--;
    }
}
