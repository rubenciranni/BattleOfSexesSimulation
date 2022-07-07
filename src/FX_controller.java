import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

    //Controller for the javafx GUI
    public class FX_controller {

        @FXML
        private TextField StartingCredit;

        @FXML
        private TextField InfantMortality;

        @FXML
        private TextField InitialSize;

        @FXML
        private TextField LifePoints;

        @FXML
        private Label a_label;

        @FXML
        private TextField apoints;

        @FXML
        private Label b_label;

        @FXML
        private Text biglabel;

        @FXML
        private TextField bpoints;

        @FXML
        private Label c_label;

        @FXML
        private TextField cpoints;

        @FXML
        private Label ic_label;

        @FXML
        private Label im_label;

        @FXML
        private Label is_label;

        @FXML
        private Label lp_label;

        @FXML
        private AnchorPane mainpane;

        @FXML
        private Button start_button;

        @FXML
        void init(ActionEvent event) {
            int initialSize = Integer.valueOf(InitialSize.getText());
            int infantMortality = Integer.valueOf(InfantMortality.getText());
            int startCredit = Integer.valueOf(StartingCredit.getText());
            int lifePoints = Integer.valueOf(LifePoints.getText());
            int a = Integer.valueOf(apoints.getText());
            int b = Integer.valueOf(bpoints.getText());
            int c = Integer.valueOf(cpoints.getText());
            Simulator simulator = new Simulator(initialSize, infantMortality, startCredit, lifePoints, a, b, c);
            try {
                simulator.startSimulation();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }


        }

    }


