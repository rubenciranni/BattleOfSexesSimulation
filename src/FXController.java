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
    public class FXController implements Initializable {
        public static boolean notSimulation = true;

        @FXML
        public PieChart pieChart;

        @FXML
        private TextField startingCredit;

        @FXML
        private TextField infantMortality;

        @FXML
        private TextField initialSize;

        @FXML
        private TextField lifePoints;

        @FXML
        private Label aLabel;

        @FXML
        private TextField aPoints;

        @FXML
        private Label bLabel;

        @FXML
        private Text bigLabel;

        @FXML
        private TextField bPoints;

        @FXML
        private Label cLabel;

        @FXML
        private TextField cPoints;

        @FXML
        private Label icLabel;

        @FXML
        private Label imLabel;

        @FXML
        private Label isLabel;

        @FXML
        private Label lpLabel;

        @FXML
        private AnchorPane mainPane;

        @FXML
        private Button startButton;

        @FXML
        void init(ActionEvent event) {
            if (notSimulation) {
                notSimulation = false;
                Stage stage = (Stage) startButton.getScene().getWindow();
                int initialSize = Integer.valueOf(this.initialSize.getText());
                int infantMortality = Integer.valueOf(this.infantMortality.getText());
                int startCredit = Integer.valueOf(startingCredit.getText());
                int lifePoints = Integer.valueOf(this.lifePoints.getText());
                int a = Integer.valueOf(aPoints.getText());
                int b = Integer.valueOf(bPoints.getText());
                int c = Integer.valueOf(cPoints.getText());
                Simulator simulator = new Simulator(initialSize, infantMortality, startCredit, lifePoints, a, b, c, this);
                Thread simulation = new Thread(() -> {
                    try {
                        simulator.startSimulation();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                simulation.start();
            }
        }


    private ObservableList<PieChart.Data> getChartData() {
        ObservableList<PieChart.Data> answer = FXCollections.observableArrayList();
        answer.addAll(
                new PieChart.Data("Coy", 0.25),
                new PieChart.Data("Fast", 0.25),
                new PieChart.Data("Faithful", 0.25),
                new PieChart.Data("Philanderer", 0.25)
        );
        return answer;
    }

    public void updatePie(Population population){
        ObservableList<PieChart.Data> oldData = pieChart.getData();
        for (PieChart.Data d : oldData) {
            d.setPieValue(population.getStateFromName(d.getName()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<PieChart.Data> pieChartData = getChartData();

        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName()
                        )
                )
        );

        pieChart.getData().addAll(pieChartData);
    }
}


