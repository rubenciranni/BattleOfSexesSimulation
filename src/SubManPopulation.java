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

        public abstract void leaveOrStay(SubWomanPopulation.WomanSubType woman);

        @Override
        public synchronized void run() {
            while (credit > 0) {
                try {
                    if (isSingle) {
                        sleep(100);
                        SubWomanPopulation.WomanSubType womanToPropose = population.queue.take();
                        womanToPropose.proposal(this);
                    }
                    /*
                    else {
                        sleep(100);
                        currentWoman.heyBabe();
                    }
                    */
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
