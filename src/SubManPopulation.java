import java.util.concurrent.TimeUnit;

public abstract class SubManPopulation extends SubPopulation {
    public SubManPopulation(ThreadGroup parent, String name, int size) {
        super(parent, name, size);
    }

    @Override
    public void increaseSize() {
        super.increaseSize();
        synchronized (population.getManPopulation()) {
            population.getManPopulation().size++;
        }
    }

    @Override
    public void decreaseSize() {
        super.decreaseSize();
        synchronized (population.getManPopulation()) {
            population.getManPopulation().size--;
        }
    }

    public abstract class ManSubType extends SubType {
        SubWomanPopulation.WomanSubType currentWoman;

        public ManSubType(ThreadGroup group) {
            super(group, RandomNameGenerator.randomNameOfBoy());
        }

        public abstract void leaveOrStay(SubWomanPopulation.WomanSubType woman);

        @Override
        public synchronized void run() {
            while (credit >= 0 && lifePoints > 0) {
                try {
                    lifePoints--;
                    sleep(100);
                    if (isSingle) {
                        SubWomanPopulation.WomanSubType womanToPropose = population.womenQueue.poll(100, TimeUnit.MILLISECONDS);
                        if (womanToPropose != null) {
                            boolean accepted = womanToPropose.proposal(this);
                            if (!accepted) {
                                womanToPropose.hey();
                                continue;
                            } else {
                                currentWoman.hey();
                                wait();
                                SubWomanPopulation.WomanSubType woman = currentWoman;
                                leaveOrStay(woman);
                                woman.hey();
                            }
                        }
                    } else {
                        currentWoman.hey();
                        wait();
                        currentWoman.hey();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            SubManPopulation.this.decreaseSize();
            synchronized (ring) {
                if (!isSingle) {
                    currentWoman.currentMan = null;
                    currentWoman.isSingle = true;
                }
            }
        }
    }
}
