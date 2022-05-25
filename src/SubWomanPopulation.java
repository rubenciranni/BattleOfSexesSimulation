import java.util.Random;

public abstract class SubWomanPopulation extends SubPopulation {
    public SubWomanPopulation(ThreadGroup parent, String name, int size) {
        super(parent, name, size);
    }

    @Override
    public void increaseSize() {
        super.increaseSize();
        synchronized (population.getWomanPopulation()) {
            population.getWomanPopulation().size++;
        }
    }

    @Override
    public void decreaseSize() {
        super.decreaseSize();
        synchronized (population.getWomanPopulation()) {
            population.getWomanPopulation().size--;
        }
    }

    public abstract class WomanSubType extends SubType {
        SubManPopulation.ManSubType currentMan;
        private boolean sterility;
        public boolean alreadyInTheQueue = false;

        public WomanSubType(ThreadGroup group) {
            super(group, RandomNameGenerator.randomNameOfGirl());
        }

        public abstract boolean accepted(SubManPopulation.ManSubType man);

        public abstract void updateCredit(SubManPopulation.ManSubType partner);

        public synchronized boolean proposal(SubManPopulation.ManSubType man) {
            if (accepted(man)) {
                this.currentMan = man;
                man.currentWoman = this;
                this.isSingle = false;
                man.isSingle = false;
                this.ring = new Object();           //creating a shared object to synchronize them
                man.ring = this.ring;
                return true;
            } else {
                return false;
            }
        }

        public synchronized void generateOffspringWith(SubManPopulation.ManSubType man) {
            this.updateCredit(man);
            // temporary implementation in order to not destroy your PC
            // ----------------------
            if (population.size > 300) {
                sterility = true;
            }
            // ----------------------
            if (sterility) {
                man.hey();
                return;
            }
            Random rand = new Random();
            boolean sex = rand.nextBoolean();

            if (sex) {
                boolean m = man.getSubType() == "Faithful";
                // TODO bugfix, following error appears: class FaithfulPopulation cannot be cast to class
                //  PhilandererPopulation (FaithfulPopulation and PhilandererPopulation
                //  are in unnamed module of loader 'app') at line 68 when noise is true
                if (population.noise && (rand.nextInt(0, 50) == 49)) {
                    m = !m;
                }
                if (m) {
                    FaithfulPopulation fatherPopulation = man.getPopulation();
                    fatherPopulation.new Faithful(fatherPopulation).start();
                    fatherPopulation.increaseSize();
                } else {
                    PhilandererPopulation fatherPopulation = man.getPopulation();
                    fatherPopulation.new Philanderer(fatherPopulation).start();
                    fatherPopulation.increaseSize();
                }
            } else {
                boolean w = this.getSubType() == "Coy";

                if (population.noise && (rand.nextInt(0, 50) == 49)) {
                    w = !w;
                }

                if (w) {
                    CoyPopulation motherPopulation = this.getPopulation();
                    motherPopulation.new Coy(motherPopulation).start();
                    motherPopulation.increaseSize();
                } else {
                    FastPopulation motherPopulation = this.getPopulation();
                    motherPopulation.new Fast(motherPopulation).start();
                    motherPopulation.increaseSize();
                }
            }
            man.hey();
        }

        @Override
        public synchronized void run() {
            // TODO bugfix, after the population reaches 100 people it starts decreasing as expected
            //  but it never goes to zero, instead it reaches a stable situation which goes on forever.
            //  Here some examples of stalling situations:
            //  number of people: 22
            //  globalState: {Faithful=0.045454547, Coy=0.22727273, Fast=0.0, Philanderers=0.72727275} Why?
            //  ------
            //  number of people: 13
            //  globalState: {Faithful=0.6923077, Coy=0.23076923, Fast=0.0, Philanderers=0.07692308} Why?
            //  ------
            //  number of people: 15
            //  globalState: {Faithful=0.0, Coy=0.06666667, Fast=0.93333334, Philanderers=0.0} More comprehensible, since if
            //  only women are remaining they wait forever in the queue without any answer

            while (credit >= 0 && lifePoints > 0) {
                try {
                    lifePoints--;
                    if (!alreadyInTheQueue && isSingle) {
                        population.womenQueue.put(this);
                        alreadyInTheQueue = true;
                    }
                    wait(1000);
                    if (!isSingle) {
                        generateOffspringWith(currentMan);
                        wait();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
             synchronized (ring) {
                 if (!isSingle) {
                    currentMan.currentWoman = null;
                    currentMan.isSingle = true;
                }
            }
            SubWomanPopulation.this.decreaseSize();
        }
    }
}
