import java.util.Random;

public class Population {
    public int people;
    public boolean sterility = false;
    public boolean noise = true;


    public void newborn(Man man, Woman woman){
        if (!sterility){
            people++;
            if (man.getClass().getName() == "Faithful")  {
                int m = 0;
            }
            else {
                int m = 1;
            }

            if (woman.getClass().getName() == "Coy")  {
                int w = 0;
            }
            else {
                int w = 1;
            }

            Random rand = new Random();
            boolean sex = rand.nextBoolean();
            int type = Math.round();
            if (sex) {

            }
            else {

            }
        }

    }

    public void death() {
        people--;
    }
}
