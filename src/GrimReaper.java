import java.util.HashMap;

import static java.lang.Math.abs;
import static java.lang.Math.max;

public class GrimReaper extends Thread {
    Population population;
    int maxValue;
    static int callsToGrim;
    static HashMap PreviousState;
    static int wait;
    static boolean finished;

    public GrimReaper(Population population, int maxValue) {
        this.population = population;
        this.maxValue = maxValue;
        if (callsToGrim == 0) {
            wait = maxValue/10;
        }
        System.out.println("\n\n"+callsToGrim);
        callsToGrim+=1;
    }

    @Override
    public void run() {
        try {
            PreviousState = population.getGlobalState();
            sleep(50);
            if (callsToGrim > maxValue / 100) {
                float Difference[] = new float[4];
                int n = 0;
                HashMap ActiveState = population.getGlobalState();
                float sum = 0;
                for (Object i : ActiveState.keySet()) {
                    Difference[n] = (float) ActiveState.get((String) i) - (float) PreviousState.get((String) i);
                    sum += abs(Difference[n++]);
                }
                if (sum < 8 / (float) maxValue) {
                    population.modifySterility(true, true);
                    System.out.println("the sum of the differences is " + sum + ";\nEach difference is:");
                    n = 0;
                    System.out.println("Faithful:\t\t" +Difference[0]+ "\nCoy:\t\t\t"+Difference[1]+"\nFast:\t\t\t"+Difference[2]+"\nPhilanderers:\t"+Difference[3]);
                }
            }
            if (!finished) {
                while (population.size > maxValue) {
                    population.modifySterility(true, false);
                    sleep(wait);
                    population.modifySterility(false, false);
                }
                int size2 = population.size;
                if (size2>4*maxValue/5) {
                    wait += (maxValue - size2) / 10;
                }
                else {
                    wait += (size2 - maxValue) / 10;
                }
                System.out.println(wait);
                SubWomanPopulation.WomanSubType.grimmyIsOut = false;
            }
        }
        catch  (InterruptedException e) {}
    }
}
