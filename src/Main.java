import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));

        stage.setScene(new Scene(root, 792, 465));
        stage.initStyle(StageStyle.TRANSPARENT);
        ResizeHelper.addResizeListener(stage);

        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
