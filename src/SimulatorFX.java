import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
//Starter for the javafx interface
public class SimulatorFX extends Application{
    Button button;

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root=FXMLLoader.load(getClass().getResource("try.fxml"));
        primaryStage.setTitle("Title");
        primaryStage.setScene(new Scene(root, 640,412));
        primaryStage.show();
    }
}
