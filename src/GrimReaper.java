public class GrimReaper extends Thread {
    Population population;
    int maxValue;

    public GrimReaper(Population population, int maxValue) {
        this.population = population;
        this.maxValue = maxValue;
    }

    @Override
    public void run() {
        try {
            int wait = maxValue / 10;
            while (population.size > maxValue) {
                int size = population.size;
                population.sterility = true;
                sleep(wait);
                population.sterility= false;
                wait+= (maxValue- population.size)/10;
            }
        }
        catch (InterruptedException e) {

        }
    }
}
