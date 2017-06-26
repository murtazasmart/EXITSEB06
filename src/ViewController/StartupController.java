package ViewController;

import Utilities.Animations.MenuButtonAnimations;
import Client.Client;
import Utilities.MediaControllers.MediaPlayerController;
import Client.UIButtonActions.GeneralButtonActions;
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

import java.io.IOException;

/**
 * Created by Bhagya Rathnayake on 5/29/2017.
 */
public class StartupController extends Application{
    private MenuButtonAnimations menuButtonAnimations;
    private MediaPlayerController mediaPlayerController;
    private GeneralButtonActions generalButtonActions;
    private JoinController joinController;
    private HostController hostController;
    private Stage stage;
    private MainController mainController;
    private Client client;



    @FXML
    Button btnJoin,btnQuit,btnHost;
    @FXML
    ImageView btnVolume;

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
    public void start(Stage primaryStage) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/startup.fxml"));
            loader.setController(this);
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("EXIT-POKER");
        primaryStage.setScene(new Scene(root, 1470, 1000));
        primaryStage.setMaximized(true);
        primaryStage.show();
        client = new Client(client);
        if(this.client==null)System.out.println("startup start client is null");else System.out.println("client not null in start start");
    }

    public void btnJoinClicked(ActionEvent actionEvent) throws Exception {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500),btnJoin);
        fadeTransition.setCycleCount(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
        stage = (Stage) btnJoin.getScene().getWindow();
        joinController.setClient(client);
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

    public void btnHostClicked(ActionEvent actionEvent){
        stage = (Stage) btnHost.getScene().getWindow();
        if(client==null) System.out.println("startupp controller client null");
        hostController.setClient(client);
        try {
            hostController.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
