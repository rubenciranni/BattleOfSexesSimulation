public abstract class SubManPopulation extends SubPopulation {
    public SubManPopulation(ThreadGroup parent, String name, int size) {
        super(parent, name, size);
    }

    @Override
    public void increaseSize() {
        super.increaseSize();
        population.getManPopulation().size++;
    }

    public abstract class ManSubType extends SubType {
        SubWomanPopulation.WomanSubType currentWoman;

        public ManSubType(ThreadGroup group) {
            super(group, RandomNameGenerator.randomNameOfBoy());
        }

        @Override
        public void run() {
            while (credit > 0) {
                try {
                    if (currentWoman == null) {
                        currentWoman = population.queue.take();
                        currentWoman.proposal(this);
                        continue;
                    } else {
                        sleep(50);
                        currentWoman.heyBabe();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
