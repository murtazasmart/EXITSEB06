package startupscreen;

import Animations.MenuButtonAnimations;
import Controllers.MediaControllers.MediaPlayerController;
import UIButtonActions.GeneralButtonActions;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 * Created by Bhagya Rathnayake on 5/29/2017.
 */
public class StartupController {
    private MenuButtonAnimations menuButtonAnimations;
    private MediaPlayerController mediaPlayerController;
    private GeneralButtonActions generalButtonActions;
    public StartupController()
    {
        menuButtonAnimations= new MenuButtonAnimations();
        mediaPlayerController= new MediaPlayerController();
        generalButtonActions= new GeneralButtonActions();
    }


    @FXML
    Button btnJoin,btnQuit;
    @FXML
    ImageView btnVolume;

    public void btnJoinClicked(ActionEvent actionEvent){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500),btnJoin);
        fadeTransition.setCycleCount(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        System.out.println("JOIN CLICKED HERE");
    }

    public void btnQuitClicked(ActionEvent actionEvent) {
        generalButtonActions.btnQuitClicked(btnQuit);
    }

    public void btnSettingsClicked(ActionEvent actionEvent) {

    }

    public void btnHelpClicked(ActionEvent actionEvent) {

    }

    public void btnInfoClicked(ActionEvent actionEvent) {

    }

    public void btnMouseEntered(MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        menuButtonAnimations.btnMouseEnteredFade(btn);

    }

    public void btnMouseExited(MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        menuButtonAnimations.btnMouseExitedFade(btn);
    }

    public void btnVolumeClicked(ActionEvent actionEvent) {
        mediaPlayerController.btnVolumeClicked(btnVolume);

    }
}
