import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        /*
        Scanner in=new Scanner(System.in);
        System.out.println("a: the evolutionary benefit for having a baby");
        System.out.println("b: the cost of parenting a child");
        System.out.println("c: the cost of courtship");
        System.out.println("noise: to add randomness to the type of the child");

        System.out.print("Insert the size of the population [int]: ");
        int pop_size = in.nextInt();
        System.out.print("Insert a [int]: ");
        int a = in.nextInt();
        System.out.print("Insert b [int]: ");
        int b = in.nextInt();
        System.out.print("Insert c [int]: ");
        int c = in.nextInt();
        System.out.print("Insert noise [true/false]: ");
        boolean noise = in.nextBoolean();
        Simulator simulator = new Simulator(pop_size, a, b, c, noise);

         */
        Simulator simulator = new Simulator(0, 0, 1, 0, 15, 20, 3, false);
        simulator.startSimulation();
    }
}
