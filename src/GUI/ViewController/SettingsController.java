package GUI.ViewController;

import Model.Client;
import Utilities.Constances.Constance;
import Utilities.MediaControllers.MediaPlayerController;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Bhagya Rathnayake on 5/31/2017.
 */
public class SettingsController extends Application {

    Client client;
    @FXML
    Button btnHome;
    @FXML
    ImageView musicImage;

    @FXML
    Label lblSettingsMessage;

    private MediaPlayerController mediaPlayerController;
    public SettingsController()
    {
        mediaPlayerController = new MediaPlayerController();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/View/settings.fxml"));
        primaryStage.setTitle("EXIT-POKER");
        primaryStage.setScene(new Scene(root));
//        primaryStage.setResizable(false);
//        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    public void btnMusicPlayerClicked(ActionEvent actionEvent) {
        int n=Constance.mediaPlayerController.muteAll();
        if(n==1)
        {
            musicImage.setImage(new Image("Resources/Images/Icons/ButtonIcons/mute.png"));
            lblSettingsMessage.setText("MUSIC MUTED!");
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(3000),lblSettingsMessage);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
            fadeTransition.play();
        }

        else
        {
            musicImage.setImage(new Image("Resources/Images/Icons/ButtonIcons/music-player.png"));
            lblSettingsMessage.setText("MUSIC TURNED BACK ON!");
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(3000),lblSettingsMessage);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
            fadeTransition.play();
        }
    }

    public void btnHomeClicked(ActionEvent actionEvent) {
        Stage stage = (Stage)btnHome.getScene().getWindow();
        StartupController startupController = new StartupController();
        startupController.setClient(client);
        startupController.start(stage);
    }
}
