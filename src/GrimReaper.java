import java.util.HashMap;

public class GrimReaper extends Thread {
    Population population;
    int maxValue;
    static int callsToGrim;
    static HashMap PreviousState;
    static float wait;

    public GrimReaper(Population population, int maxValue) {
        this.population = population;
        this.maxValue = maxValue;
        if (callsToGrim == 0) {
            wait = maxValue/10;
        }
        callsToGrim+=1;
    }

    @Override
    public void run() {
        PreviousState = population.getGlobalState();
        try {
            int wait = maxValue / 10;
            while (population.size > maxValue) {
                int size = population.size;
                population.sterility = true;
                sleep(wait);
                population.sterility= false;
                wait += (maxValue - population.size)/10;
            }
        }
        catch (InterruptedException e) {}
        if (callsToGrim > maxValue/50) {
            float Difference[] = new float[4];
            int n=0;
            HashMap ActiveState = population.getGlobalState();
            float sum=0;
            for (Object i : ActiveState.keySet()) {
                Difference[n] = (float) ActiveState.get((String) i)-(float) PreviousState.get((String) i);
                sum += Difference[n++];
            }
            if (sum < 1/maxValue) {
                System.out.println("the sum of the differences is" +sum+ "; while each different difference is" +Difference);
                population.sterility=true;
            }
        }
    }
}
