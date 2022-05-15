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
                     return new FaithfulPopulation.((FaithfulPopulation.Faithful) man);
                 }
                return new Philanderers();
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
                    return new Coy();
                }
                return new Fast();
            }
        }
        return null;
    }
}
