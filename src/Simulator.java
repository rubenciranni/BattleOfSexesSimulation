public class Simulator {
    // removed field sterility because it has been displaced in SubWomanPopulation.WomanSubType
    // removed fields a, b, c, noise because it has been displaced in Population
    // removed method newBorn because it has been displaced inside SubWomanPopulation.WomanSubType
    // (it is now called generateOffspringWith)
    // TODO remove this comments once understood
    private final Population population;

    public Simulator(int populationInitialSize, int a, int b, int c, boolean noise) {
        this.population = new Population("population", populationInitialSize, a, b, c, noise);
    }

    public Simulator(int coyPopulationInitialSize, int fastPopulationInitialSize,
                     int faithfulPopulationInitialSize, int philandererPopulationInitialSize,
                     int a, int b, int c, boolean noise) {
        this.population = new Population("population", coyPopulationInitialSize, faithfulPopulationInitialSize,
                faithfulPopulationInitialSize, philandererPopulationInitialSize, a, b, c, noise);
    }

    public void startSimulation() throws InterruptedException {
        //sorts initialPopulationList in alphabetic order
        population.initialPopulationList.sort((Thread o1, Thread o2) -> o1.getName().compareToIgnoreCase(o2.getName()));

        for (SubPopulation.SubType t : population.initialPopulationList) {
            t.start();
        }

        while (true) {
            boolean IntegerTrueFloatFalse = true;
            Thread.sleep(100);
            System.out.println(population.size);
            if (IntegerTrueFloatFalse)
                System.out.println(population.getPopulations());
            else
                System.out.println(population.getGlobalState());
            Thread[] list = new Thread[population.activeCount()];
            population.enumerate(list);
            System.out.println(list);
        }
    }
}
