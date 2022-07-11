import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Starter for the javafx interface
public class SimulatorFX extends Application {
    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("try.fxml"));
        primaryStage.setTitle("Simulator");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
}
