package Client.viewstartup;

import Client.Animations.MenuButtonAnimations;
import Client.Controllers.MediaControllers.MediaPlayerController;
import Client.UIButtonActions.GeneralButtonActions;
import Client.viewhost.HostController;
import Client.viewjoin.JoinController;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by Bhagya Rathnayake on 5/29/2017.
 */
public class StartupController extends Application {
    private MenuButtonAnimations menuButtonAnimations;
    private MediaPlayerController mediaPlayerController;
    private GeneralButtonActions generalButtonActions;
    private JoinController joinController;
    private HostController hostController;
    private Stage stage;
    public StartupController()
    {
        menuButtonAnimations= new MenuButtonAnimations();
        mediaPlayerController= new MediaPlayerController();
        generalButtonActions= new GeneralButtonActions();
        joinController= new JoinController();
        hostController= new HostController();
//        ClientSide obj = new ClientSide();
//        obj.method1();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("startup.fxml"));
        primaryStage.setTitle("EXIT-POKER");
        primaryStage.setScene(new Scene(root, 1470, 1000));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }


    @FXML
    Button btnJoin,btnQuit,btnHost;
    @FXML
    ImageView btnVolume;

    public void btnJoinClicked(ActionEvent actionEvent) throws Exception {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500),btnJoin);
        fadeTransition.setCycleCount(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
        stage = (Stage) btnJoin.getScene().getWindow();
        joinController.start(stage);

    }

    public void btnQuitClicked(ActionEvent actionEvent){
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

    public void btnHostClicked(ActionEvent actionEvent) throws Exception {
        stage = (Stage) btnJoin.getScene().getWindow();
        hostController.start(stage);
    }
}
