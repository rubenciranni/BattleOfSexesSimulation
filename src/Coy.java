public class Coy extends Thread implements Woman {
    public int credit;
    public boolean single;

    @Override
    public boolean acceptPropose(Man m) {
        return false;
    }

    @Override
    public void generateChildWith(Man m) {

    }
}
