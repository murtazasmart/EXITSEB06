package Client;

import Utilities.MediaControllers.MediaPlayerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by MA_Laptop on 6/21/2017.
 */
public class GUIThread extends Application {

    MediaPlayerController mediaPlayerController = new MediaPlayerController();
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/View/connecttoserver.fxml"));
        primaryStage.setTitle("EXIT-POKER");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        mediaPlayerController.playMusic();

    }

    public void getStage(){

    }

    public void startView() {
        launch();
    }

}
