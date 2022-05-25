public class Main {
    public static void main(String[] args) throws InterruptedException {
        // TODO implement user input to decide parameters for simulator's constructor,
        //  and what constructor to use between the two
        Simulator simulator = new Simulator( 4, 15, 20, 3, false);
        simulator.startSimulation();
    }
}
