import java.util.HashMap;

public class Simulator {
    private final Population population;
    private static boolean IntegerTrueFloatFalse = true;
    static boolean print = true;
    static int TIME = 1000;

    public Simulator(int populationInitialSize, int a, int b, int c, boolean noise) {
        this.population = new Population("population", populationInitialSize, a, b, c, noise);
    }

    public Simulator(int coyPopulationInitialSize, int fastPopulationInitialSize,
                     int faithfulPopulationInitialSize, int philandererPopulationInitialSize,
                     int a, int b, int c, boolean noise) {
        this.population = new Population("population", coyPopulationInitialSize, fastPopulationInitialSize,
                faithfulPopulationInitialSize, philandererPopulationInitialSize, a, b, c, noise);
    }

    public Simulator (int coy, int fast, int faith, int phil, int infMor, int startCredit, int life, int a,int b, int c, int noiseChance) {
        this.population = new Population("population", coy, fast, faith, phil, infMor, startCredit, life, a, b, c, noiseChance);
    }

    public Simulator (int coy, int fast, int faith, int phil, int infMor, int startCredit, int life, int a,int b, int c) {
        this.population = new Population("population", coy, fast, faith, phil, infMor, startCredit, life, a, b, c);
    }

    public void startSimulation() throws InterruptedException {
        //sorts initialPopulationList in alphabetic order
        population.initialPopulationList.sort((Thread o1, Thread o2) -> o1.getName().compareToIgnoreCase(o2.getName()));

        for (SubPopulation.SubType t : population.initialPopulationList) {
            t.start();
        }
        while (population.size > 0) {
            Thread.sleep(50);
            if (print) {
                Thread.sleep(TIME);
                System.out.println(population.totalSize() + "  " + population.size);

                if (IntegerTrueFloatFalse) {
                    HashMap t = population.getPopulationss();
                    System.out.println(population.getManPopulation().size + population.getWomanPopulation().size);
                    if ((int)t.get("Faithful")+(int)t.get("Philanderers") == population.getManPopulation().size){
                        System.out.println(t.get("Faithful")+" "+ (int)t.get("Philanderers")+" "+population.getManPopulation().size);
                    }
                    if ((int)t.get("Faithful")+(int)t.get("Philanderers") != (int)t.get("Men")){
                        System.out.println(t.get("Faithful")+" "+ (int)t.get("Philanderers")+" "+(int)t.get("Men")+ "****");
                    }
                    if ((int)t.get("Coy")+(int)t.get("Fast") != (int)t.get("Women")){
                        System.out.println(t.get("Coy")+" "+ (int)t.get("Fast")+" "+(int)t.get("Women") +"****");
                    }
                }
                else
                    System.out.println(population.getGlobalState());
            }
        }
    }
}
