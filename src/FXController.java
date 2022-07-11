import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

//Controller for the javafx GUI
public class FXController implements Initializable {
    public boolean notSimulation = true;
    public Simulator simulator;

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
    private TextArea textArea;

    @FXML
    private Button stopButton;

    private  AudioPlayer startSound = new AudioPlayer("Sounds/begin.wav");
    private  AudioPlayer processingSound = new AudioPlayer("Sounds/processing.wav");
    private  AudioPlayer endSound = new AudioPlayer("Sounds/end.wav");

    public FXController() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
    }

    @FXML
    private void init(ActionEvent event) {
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
            this.simulator = new Simulator(initialSize, infantMortality, startCredit, lifePoints, a, b, c, this);
            Thread simulation = new Thread(() -> {
                try {
                    simulator.startSimulation();
                    startSound.stop();
                    processingSound.stop();
                    endSound.play();
                    sleep(10000);
                    endSound.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            startSound.play();
            simulation.start();
            processingSound.play();
        }
    }

    @FXML
    private void terminate(ActionEvent event) {
        if (!notSimulation) this.simulator.EndOfEverything=true;
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


    public void setOutputText(String toTextArea){
        textArea.setText(toTextArea);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<PieChart.Data> pieChartData = getChartData();

        pieChartData.forEach(data -> data.nameProperty().bind(Bindings.concat(data.getName())));

        pieChart.getData().addAll(pieChartData);
    }
}