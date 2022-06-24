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

    public Simulator (int coyPopulationInitialSize, int fastPopulationInitialSize, int faithfulPopulationInitialSize, int philandererPopulationInitialSize, int infantMortality, int startCredit, int life, int a,int b, int c, int noiseChance) {
        this.population = new Population("population", coyPopulationInitialSize, fastPopulationInitialSize, faithfulPopulationInitialSize, philandererPopulationInitialSize, infantMortality, startCredit, life, a, b, c, noiseChance);
    }

    public Simulator (int coyPopulationInitialSize, int fastPopulationInitialSize, int faithfulPopulationInitialSize, int philandererPopulationInitialSize, int infantMortality, int startCredit, int life, int a,int b, int c) {
        this.population = new Population("population", coyPopulationInitialSize, fastPopulationInitialSize, faithfulPopulationInitialSize, philandererPopulationInitialSize, infantMortality, startCredit, life, a, b, c);
    }

    public void startSimulation() throws InterruptedException {
        //sorts initialPopulationList in alphabetic order
        population.initialPopulationList.sort((Thread o1, Thread o2) -> o1.getName().compareToIgnoreCase(o2.getName()));

        for (SubPopulation.SubType t : population.initialPopulationList) {
            population.world.execute(t);
        }
        while (population.size > 0) {
            Thread.sleep(50);
            if (print) {
                Thread.sleep(TIME);
                System.out.println(population.totalSize() + "  " + population.size);

                if (IntegerTrueFloatFalse)
                    System.out.println(population.getPopulations());
                else
                    System.out.println(population.getGlobalState());
            }
        }
    }
}
