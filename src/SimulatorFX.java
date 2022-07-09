import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

//Starter for the javafx interface
public class SimulatorFX extends Application{
    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root=FXMLLoader.load(getClass().getResource("try.fxml"));
        primaryStage.setTitle("Simulator");
        primaryStage.setScene(new Scene(root, 800,600));
        primaryStage.show();
    }
}
