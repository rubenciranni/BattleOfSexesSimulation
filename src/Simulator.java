public class Simulator {
    private final Population population;
    static private boolean IntegerTrueFloatFalse = true;
    static boolean print = false;
    static int Time = 1000;

    public Simulator(int populationInitialSize, int a, int b, int c, boolean noise) {
        this.population = new Population("population", populationInitialSize, a, b, c, noise);
    }

    public Simulator(int coyPopulationInitialSize, int fastPopulationInitialSize,
                     int faithfulPopulationInitialSize, int philandererPopulationInitialSize,
                     int a, int b, int c, boolean noise) {
        this.population = new Population("population", coyPopulationInitialSize, fastPopulationInitialSize,
                faithfulPopulationInitialSize, philandererPopulationInitialSize, a, b, c, noise);
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
                System.out.println(population.totalSize() + "  " + population.size);
                Thread.sleep(Time);
                if (IntegerTrueFloatFalse)
                    System.out.println(population.getPopulations());
                else
                    System.out.println(population.getGlobalState());
            }
        }
    }
}
