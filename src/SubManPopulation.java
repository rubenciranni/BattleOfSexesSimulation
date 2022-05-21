import java.util.Random;

public abstract class SubManPopulation extends SubPopulation {
    public SubManPopulation(ThreadGroup parent, String name, int size) {
        super(parent, name, size);
    }

    public abstract class ManSubType extends SubType {
        SubWomanPopulation.WomanSubType currentWoman;

        public ManSubType(ThreadGroup group) {
            super(group, RandomNameGenerator.randomNameOfBoy());
        }

        public abstract void doOtherStuff();

        @Override
        public void run() {
            while (credit > 0) {
                WomanPopulation womanPopulation = population.getWomanPopulation();
                SubWomanPopulation.WomanSubType[] women = new SubWomanPopulation.WomanSubType[womanPopulation.activeCount()];
                womanPopulation.enumerate(women, false);
                Random rand = new Random();
                currentWoman = women[rand.nextInt(women.length)];
                currentWoman.proposal(this);
                doOtherStuff();
            }
        }
    }
}
