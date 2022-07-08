import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

//Controller for the javafx GUI
    public class FX_controller implements Initializable {

        @FXML
        public PieChart pieChart;

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
            Stage stage = (Stage) start_button.getScene().getWindow();
            int initialSize = Integer.valueOf(InitialSize.getText());
            int infantMortality = Integer.valueOf(InfantMortality.getText());
            int startCredit = Integer.valueOf(StartingCredit.getText());
            int lifePoints = Integer.valueOf(LifePoints.getText());
            int a = Integer.valueOf(apoints.getText());
            int b = Integer.valueOf(bpoints.getText());
            int c = Integer.valueOf(cpoints.getText());
            Simulator simulator = new Simulator(initialSize, infantMortality, startCredit, lifePoints, a, b, c,this);

            try {
                simulator.startSimulation();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }





        }


    private ObservableList<PieChart.Data> getChartData() {
        ObservableList<PieChart.Data> answer = FXCollections.observableArrayList();
        answer.addAll(
                new PieChart.Data("Coy", 0.25),
                new PieChart.Data("Fast", 0.25),
                new PieChart.Data("Faithful", 0.25),
                new PieChart.Data("Philanderers", 0.25)
        );
        return answer;
    }

    public void pieset(Population population){
            pieChart.setData(getChartTrueData(population));
    }

    public ObservableList<PieChart.Data> getChartTrueData(Population population) {
        ObservableList<PieChart.Data> answer = FXCollections.observableArrayList();
        answer.addAll(
                new PieChart.Data("Coy", 33),
                new PieChart.Data("Fast", 33),
                new PieChart.Data("Faithful", 33),
                new PieChart.Data("Philanderers", 0)
        );
        return answer;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<PieChart.Data> pieChartData = getChartData();

        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), ": ", data.pieValueProperty()
                        )
                )
        );

        pieChart.getData().addAll(pieChartData);
    }
}


