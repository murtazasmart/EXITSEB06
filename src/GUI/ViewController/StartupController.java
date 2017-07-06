package GUI.ViewController;

import Utilities.Animations.MenuButtonAnimations;
import Model.Client;
import Utilities.MediaControllers.MediaPlayerController;
import Utilities.UIButtonActions.GeneralButtonActions;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

import static javafx.scene.media.AudioClip.INDEFINITE;

/**
 * Created by Bhagya Rathnayake on 5/29/2017.
 */
public class StartupController extends Application{
    private MenuButtonAnimations menuButtonAnimations;
    private MediaPlayerController mediaPlayerController;
    private GeneralButtonActions generalButtonActions;
    private JoinController joinController;
    private HostController hostController;
    private SettingsController settingsController;
    private HelpController helpController;
    private AboutController aboutController;
    private Stage stage;
    private Client client;



    @FXML
    Button btnJoin,btnQuit,btnHost,btnLeaderBoard,btnSettings,btnHelp,btnAbout;

    public StartupController()
    {
        menuButtonAnimations= new MenuButtonAnimations();
        mediaPlayerController= new MediaPlayerController();
        generalButtonActions= new GeneralButtonActions();
        joinController= new JoinController();
        hostController= new HostController();
        settingsController = new SettingsController();
        helpController= new HelpController();
        aboutController = new AboutController();
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/startup.fxml"));
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

    public void btnSettingsClicked(ActionEvent actionEvent) throws Exception {
        stage = (Stage) btnSettings.getScene().getWindow();
        try{
            settingsController.start(stage);
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public void btnHelpClicked(ActionEvent actionEvent) throws Exception {
        System.out.println("HELPPPPPP");
        stage = (Stage) btnHelp.getScene().getWindow();
        helpController.start(stage);
    }

    public void btnInfoClicked(ActionEvent actionEvent) throws Exception{
        stage = (Stage) btnAbout.getScene().getWindow();
        aboutController.start(stage);
    }

    public void btnMouseEntered(MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        menuButtonAnimations.btnMouseEnteredFade(btn);
        mediaPlayerController.sfxMouseOver();

    }

    public void btnMouseExited(MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        menuButtonAnimations.btnMouseExitedFade(btn);
        mediaPlayerController.sfxMouseLeave();
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

    public void btnLeaderBoardClicked()
    {
        try{
            Stage stage = (Stage)btnLeaderBoard.getScene().getWindow();
            LeaderBoardController leaderBoardController = new LeaderBoardController();
            leaderBoardController.setClient(client);
            leaderBoardController.start(stage);
        }

        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }



}
