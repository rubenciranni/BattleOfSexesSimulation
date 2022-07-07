import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
//Starter for the javafx interface
public class pie_FX extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root=FXMLLoader.load(getClass().getResource("pie.fxml"));
        Stage secondStage = new Stage();
        secondStage.setScene(new Scene(root, 600,400));
        secondStage.show();
    }
}
