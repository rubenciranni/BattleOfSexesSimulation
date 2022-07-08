import javafx.application.Platform;

import java.util.HashMap;

import static java.lang.Math.abs;

public class Simulator {
    private final Population population;
    private static boolean IntegerTrueFloatFalse = true;
    static boolean print = true;
    static int TIME = 10;
    public FXController myGui;

    public Simulator(int populationInitialSize, int a, int b, int c, boolean noise) {
        this.population = new Population("population", populationInitialSize, a, b, c, noise);
    }

    public Simulator(int coyPopulationInitialSize, int fastPopulationInitialSize,
                     int faithfulPopulationInitialSize, int philandererPopulationInitialSize,
                     int a, int b, int c, boolean noise) {
        this.population = new Population("population", coyPopulationInitialSize, fastPopulationInitialSize,
                faithfulPopulationInitialSize, philandererPopulationInitialSize, a, b, c, noise);
    }

    public Simulator(int coyPopulationInitialSize, int fastPopulationInitialSize, int faithfulPopulationInitialSize, int philandererPopulationInitialSize, int infantMortality, int startCredit, int life, int a, int b, int c, int noiseChance) {
        this.population = new Population("population", coyPopulationInitialSize, fastPopulationInitialSize, faithfulPopulationInitialSize, philandererPopulationInitialSize, infantMortality, startCredit, life, a, b, c, noiseChance);
    }

    public Simulator(int initialSize, int infantMortality, int startCredit, int life, int a, int b, int c, FXController gui) {
        this.myGui =gui;
        this.population = new Population("population", initialSize, infantMortality, startCredit, life, a, b, c);
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
            Platform.runLater(new Thread(() -> {myGui.updatePie(population);}));
            Thread.sleep(TIME);
            HashMap<String, Float> activeState = population.getGlobalState();
            if (counter % 100 == 0) {
                float difference[] = new float[4];
                int n = 0;
                float sum = 0;
                for (String i : activeState.keySet()) {
                    difference[n] = activeState.get(i) - previousState.get(i);
                    sum += abs(difference[n++]);
                }
                sum = sum / 4;
                System.out.println(sum);
                if (sum < 0.001) {
                    countSmallDeltas++;
                }

                if (print) {
                    System.out.println(population.getTotalSize() + "  " + population.size);

                    if (IntegerTrueFloatFalse)
                        System.out.println(population.getPopulations());
                    else
                        System.out.println(population.getGlobalState());
                }
                previousState = activeState;

                if (countSmallDeltas == 5) {
                    population.sterility = true;
                    population.death = true;
                    System.out.println("the sum of the differences is " + sum + ";\nEach difference is:");
                    System.out.println("Faithful:\t\t" + difference[0] + "\nCoy:\t\t\t" + difference[1] + "\nFast:\t\t\t" + difference[2] + "\nPhilanderers:\t" + difference[3]);
                    System.out.println("\n\nFinal Values:");
                    for (String i : activeState.keySet()) {
                        System.out.println(i + ": " + activeState.get(i));
                    }
                    System.out.println("\n");
                    Simulator.TIME = 1000;
                    Simulator.print = true;
                    FXController.notSimulation = true;
                    break;
                }
            }
        }
    }
}


