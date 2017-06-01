package startupscreen;

import Animations.MenuButtonAnimations;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by Bhagya Rathnayake on 5/29/2017.
 */
public class StartupController {
    private MenuButtonAnimations menuButtonAnimations;

    public StartupController()
    {
        menuButtonAnimations= new MenuButtonAnimations();
    }


    @FXML
    Button btnJoin,btnQuit;

    public void btnJoinClicked(ActionEvent actionEvent){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500),btnJoin);
        fadeTransition.setCycleCount(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        System.out.println("JOIN CLICKED HERE");
    }

    public void btnQuitClicked(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning!");
        alert.setHeaderText("You are about to exit. Any Unsaved changes will be lost");
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage = (Stage) btnQuit.getScene().getWindow();
            stage.close();
        } else {

        }
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
}
