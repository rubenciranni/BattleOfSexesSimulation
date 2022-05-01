public class Population {
    public int People;
    public Boolean Sterility;

    public static void main(String[] args) {

    }

    public void Newborn(){
        if (!Sterility){
            People++;
        }

    }

    public void death() {
        People--;
    }
}
