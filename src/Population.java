public class Population {
    public int people;
    public boolean sterility = false;
    public boolean noise = true;

    public static void main(String[] args) {

    }

    public void newborn(){
        if (!sterility){
            people++;
        }

    }

    public void death() {
        people--;
    }
}
