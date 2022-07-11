import javafx.application.Platform;

import java.util.HashMap;

import static java.lang.Math.abs;

public class Simulator {
    static boolean print = true;
    static int TIME = 10;
    private static final boolean IntegerTrueFloatFalse = true;
    public final Population population;
    public FXController myGui;
    public boolean EndOfEverything = false;

    public Simulator(int initialSize, int infantMortality, int startCredit, int life, int a, int b, int c, FXController gui) {
        this.myGui = gui;
        this.population = new Population("population", initialSize, infantMortality, startCredit, life, a, b, c);
    }

    public static String print(String s) {
        System.out.println(s);
        return s + "\n";
    }

    public void startSimulation() throws InterruptedException {
        //sorts initialPopulationList in alphabetic order
        population.initialPopulationList.sort((Thread o1, Thread o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        int countSmallDeltas = 0;
        int counter = 0;

        for (SubPopulation.SubType t : population.initialPopulationList) {
            population.world.execute(t);
        }

        HashMap<String, Float> previousState = population.getGlobalState();

        while (true) {
            counter++;
            Platform.runLater(new Thread(() -> {
                myGui.updatePie(population);
            }));
            Thread.sleep(TIME);
            HashMap<String, Float> activeState = population.getGlobalGenderState();
            if (counter % 100 == 0) {
                float[] difference = new float[4];
                int n = 0;
                float sum = 0;
                for (String i : activeState.keySet()) {
                    difference[n] = activeState.get(i) - previousState.get(i);
                    sum += abs(difference[n++]);
                }
                sum = sum / 4;
                System.out.println(sum);
                if (sum < 0.0005) {
                    countSmallDeltas++;
                }
                /*
                else {
                    countSmallDeltas = 0;
                }
                */

                if (print) {
                    System.out.println(population.getTotalSize() + "  " + population.size);

                    if (IntegerTrueFloatFalse)
                        System.out.println(population.getPopulations());
                    else
                        System.out.println(population.getGlobalState());
                }
                previousState = activeState;

                if (countSmallDeltas == 5 || EndOfEverything) {
                    population.sterility = true;
                    population.death = true;
                    HashMap<String, Float> perfectState = population.getPerfectValues();
                    float[] errorCounter = new float[4];
                    n = 0;
                    String toTextArea = "";
                    System.out.println("\n");
                    toTextArea += print("The sum of the differences is " + sum + ";\n\nEach difference is:");
                    toTextArea += print("Faithful:\t\t" + difference[0] + "\nCoy:\t\t\t" + difference[1] + "\nFast:\t\t\t" + difference[2] + "\nPhilanderers:\t" + difference[3]);
                    toTextArea += print("\n\nFinal vs Perfect Values:\n");
                    for (String i : activeState.keySet()) {
                        toTextArea += print(i + " (Simulation): " + activeState.get(i));
                        toTextArea += print(i + " (Prediction): " + perfectState.get(i) + "\n");
                        errorCounter[n++] = Math.abs(perfectState.get(i) - activeState.get(i));
                    }
                    toTextArea += print("\nMean Of Errors w.r.t Dawkins's prediction: " + (errorCounter[0] + errorCounter[1] + errorCounter[2] + errorCounter[3]) * 25 + "%");
                    myGui.setOutputText(toTextArea);
                    myGui.notSimulation = true;
                    break;
                }
            }
        }
    }
}


