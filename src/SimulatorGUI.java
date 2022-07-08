import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import javax.swing.*;

public class SimulatorGUI extends JPanel {
    private JTextField initialSizeField;
    private JLabel initialSizeLabel;
    private JLabel infantMortalityLabel;
    private JLabel startCreditLabel;
    private JLabel lifePointsLabel;
    private JLabel aLabel;
    private JLabel bLabel;
    private JLabel cLabel;
    private JButton startButtonField;
    private JTextField infantMortalityField;
    private JTextField startCreditField;
    private JTextField lifePointsField;
    private JTextField aField;
    private JTextField bField;
    private JTextField cField;

    public SimulatorGUI() {
        //construct components
        initialSizeField = new JTextField (5);
        initialSizeLabel = new JLabel ("Initial size:");
        infantMortalityLabel = new JLabel ("Mortality:");
        startCreditLabel = new JLabel ("Starting credit:");
        lifePointsLabel = new JLabel ("Life points:");
        aLabel = new JLabel ("a:");
        bLabel = new JLabel ("b:");
        cLabel = new JLabel ("c:");
        startButtonField = new JButton ("Start");
        infantMortalityField = new JTextField (5);
        startCreditField = new JTextField (5);
        lifePointsField = new JTextField (5);
        aField = new JTextField (5);
        bField = new JTextField (5);
        cField = new JTextField (5);
        startButtonField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int initialSize = Integer.valueOf(initialSizeField.getText());
                int infantMortality = Integer.valueOf(infantMortalityField.getText());
                int startCredit = Integer.valueOf(startCreditField.getText());
                int lifePoints = Integer.valueOf(lifePointsField.getText());
                int a = Integer.valueOf(aField.getText());
                int b = Integer.valueOf(bField.getText());
                int c = Integer.valueOf(cField.getText());
                Simulator simulator = new Simulator(initialSize, infantMortality, startCredit, lifePoints, a, b, c,null);
                try {
                    simulator.startSimulation();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });


        //adjust size and set layout
        setPreferredSize (new Dimension (771, 462));
        setLayout (null);

        //add components
        add (initialSizeField);
        add (initialSizeLabel);
        add (infantMortalityLabel);
        add (startCreditLabel);
        add (lifePointsLabel);
        add (aLabel);
        add (bLabel);
        add (cLabel);
        add (startButtonField);
        add (infantMortalityField);
        add (startCreditField);
        add (lifePointsField);
        add (aField);
        add (bField);
        add (cField);

        //set component bounds (only needed by Absolute Positioning)
        initialSizeField.setBounds (130, 10, 100, 25);
        initialSizeLabel.setBounds (10, 10, 100, 25);
        infantMortalityLabel.setBounds (10, 40, 100, 25);
        startCreditLabel.setBounds (10, 70, 100, 25);
        lifePointsLabel.setBounds (10, 100, 100, 25);
        aLabel.setBounds (10, 130, 20, 25);
        bLabel.setBounds (10, 160, 20, 25);
        cLabel.setBounds (10, 190, 20, 25);
        startButtonField.setBounds (30, 230, 100, 50);
        infantMortalityField.setBounds (130, 40, 100, 25);
        startCreditField.setBounds (130, 70, 100, 25);
        lifePointsField.setBounds (130, 100, 100, 25);
        aField.setBounds (30, 130, 100, 25);
        bField.setBounds (30, 160, 100, 25);
        cField.setBounds (30, 190, 100, 25);
    }

    public static void main (String[] args) {
        JFrame frame = new JFrame ("MyPanel");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        SimulatorGUI panel = new SimulatorGUI();
        frame.getContentPane().add (panel);
        frame.pack();
        frame.setVisible (true);


    }
}