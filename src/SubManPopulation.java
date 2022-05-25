public abstract class SubManPopulation extends SubPopulation {
    public SubManPopulation(ThreadGroup parent, String name, int size) {
        super(parent, name, size);
    }

    @Override
    public void increaseSize() {
        super.increaseSize();
        population.getManPopulation().size++;
    }

    @Override
    public void decreaseSize() {
        super.decreaseSize();
        population.getManPopulation().size--;
    }

    public abstract class ManSubType extends SubType {
        SubWomanPopulation.WomanSubType currentWoman;

        public ManSubType(ThreadGroup group) {
            super(group, RandomNameGenerator.randomNameOfBoy());
        }

        public abstract void leaveOrStay(SubWomanPopulation.WomanSubType woman);

        @Override
        public synchronized void run() {
            while (credit > 0 && lifePoints > 0) {
                try {
                    sleep(100);
                    if (isSingle) {
                        SubWomanPopulation.WomanSubType womanToPropose = population.womenQueue.take();
                        boolean accepted = womanToPropose.proposal(this);
                        if (!accepted) {
                             lifePoints--;
                             continue;
                        }
                        else {
                            currentWoman.hey();
                            wait();
                            SubWomanPopulation.WomanSubType woman = currentWoman;
                            leaveOrStay(woman);
                            woman.hey();
                        }
                    }
                    else {
                        sleep(100);
                        currentWoman.hey();
                        wait();
                        currentWoman.hey();
                    }
                    lifePoints--;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (ring) {
                if (!isSingle) {
                    currentWoman.currentMan = null;
                    currentWoman.isSingle = true;
                }
            }
            SubManPopulation.this.decreaseSize();
        }
    }
}
