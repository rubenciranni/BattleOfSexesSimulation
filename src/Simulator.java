import java.util.Random;

public class Simulator {
    private final int a; // the evolutionary benefit for having a baby
    private final int b; // the cost of parenting a child
    private final int c; // the cost of courtship
    private final boolean sterility = false;
    private final boolean noise = false;
    private final Population population;

    public Simulator(Population population, int a, int b, int c) {
        this.population = population;
        this.a = a;
        this.b = b;
        this.c = c;
    }


    public synchronized SubPopulation.SubType newborn(SubPopulation.SubType man, SubPopulation.SubType woman) {
        if (!sterility) {
            Random rand = new Random();
            boolean sex = rand.nextBoolean();
            if (sex) {
                boolean m = man.getClass().getName() == "Faithful";
                /*
                 if (noise && rand.nextInt(0, 50) == 49) {
                     m = !m;
                 }
                 */
                ((ManPopulation) man.getPopulation().getParent()).size++;
                if (m) {
                    FaithfulPopulation father = man.getPopulation();
                    father.increaseSize();
                    return father.new Faithful(father);
                }
                PhilandererPopulation father = man.getPopulation();
                father.increaseSize();
                return father.new Philanderer(father);
            } else {
                boolean w = woman.getClass().getName() == "Coy";
                /*
                if (noise && rand.nextInt(0, 50) == 49) {
                    w = !w;
                }
                 */
                ((WomanPopulation) woman.getPopulation().getParent()).size++;
                if (w) {
                    CoyPopulation mother = man.getPopulation();
                    mother.increaseSize();
                    return mother.new Coy(mother);
                }
                FastPopulation mother = man.getPopulation();
                mother.increaseSize();
                return mother.new Fast(mother);
            }
        }
        return null;
    }

}
