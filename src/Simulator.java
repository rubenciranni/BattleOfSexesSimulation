import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Simulator {
    private boolean sterility = false;
    private boolean noise = false;


    private Population population;
    private final int a; // the evolutionary benefit for having a baby
    private final int b; // the cost of parenting a child
    private final int c; // the cost of courtship

    public Simulator(Population population, int a, int b, int c) {
        this.population = population;
        this.a = a;
        this.b = b;
        this.c = c;
    }


    public synchronized SubPopulation.SubType newborn(SubPopulation.SubType man, SubPopulation.SubType woman){
        if (!sterility){
            population.IncreaseSize();
            Random rand = new Random();
            boolean sex = rand.nextBoolean();

            if (sex) {
                boolean m = false;
                if (man.getClass().getName() == "Faithful")  {
                    m = true;
                }
                /*
                 if (noise && rand.nextInt(0, 50) == 49) {
                     m = !m;
                 }
                 */
                 if (m) {
                     FaithfulPopulation father = man.<FaithfulPopulation>getPopulation();
                     return  father.new Faithful(father, RandomNameGenerator.randomNameOfBoy());
                 }
                PhilandererPopulation father = man.<PhilandererPopulation>getPopulation();
                return  father.new Philanderer(father, RandomNameGenerator.randomNameOfBoy());
            }
            else {
                boolean w = false;
                if (woman.getClass().getName() == "Coy") {
                    w = true;
                }
                /*
                if (noise && rand.nextInt(0, 50) == 49) {
                    w = !w;
                }
                 */
                if (w) {
                    CoyPopulation mother = man.<CoyPopulation>getPopulation();
                    return  mother.new Coy(mother, RandomNameGenerator.randomNameOfBoy());
                }
                FastPopulation mother = man.<FastPopulation>getPopulation();
                return  mother.new Fast(mother, RandomNameGenerator.randomNameOfBoy());
            }
        }
        return null;
    }

}
