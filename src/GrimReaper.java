import java.util.HashMap;

import static java.lang.Math.abs;

public class GrimReaper extends Thread {
    Population population;
    int maxValue;
    static int callsToGrim;
    static int wait;
    static boolean finished;
    int sleepingTime = 100;

    public GrimReaper(Population population, int maxValue) {
        this.population = population;
        this.maxValue = maxValue;
        if (callsToGrim == 0) {
            wait = maxValue/30;
        }
        callsToGrim+=1;
    }

    @Override
    public void run() {
        HashMap PreviousState = population.getPopulationsToPrint();
        try {
            sleep(sleepingTime);
            HashMap ActiveState = population.getPopulationsToPrint();
            HashMap Ratios = population.getGlobalStateToPrint();
            if (callsToGrim > 10+maxValue/50) {
                float Differences[] = new float[4];
                int n = 0;
                int sum = 0;
                for (Object i : ActiveState.keySet()) {
                    Differences[n] = (int) ActiveState.get((String) i) - (int) PreviousState.get((String) i);
                    sum += abs(Differences[n++]);
                }
                n=0;
                if (sum < maxValue/15) {
                    System.out.println("the sum of the differences is " + sum + ";\nwhile each difference is:\n");
                    for (Object i : ActiveState.keySet()) {
                        System.out.println(i +""+ ActiveState.get(i) + "\t\t" + PreviousState.get(i)+"\t\t"+Differences[n++]+"\t\t\t"+Ratios.get(i));
                    }
                    population.modifySterility(true, true);
                    Simulator.print = true;
                }
            }
            while (population.size > maxValue) {
                population.modifySterility(true, false);
                sleep(wait);
                population.modifySterility(false, false);
            }
            System.out.println(callsToGrim+"\n"+wait+"\n\n");
            SubWomanPopulation.WomanSubType.grimmyIsOut = false;
        }
        catch (InterruptedException e) {}
    }
}

