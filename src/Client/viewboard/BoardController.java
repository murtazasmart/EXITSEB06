package Client.viewboard;

import Client.Animations.CardAnimations;
import Client.Animations.MenuButtonAnimations;
import Client.Client;
import Client.UIButtonActions.GeneralButtonActions;
import Client.viewboard.boardpopups.BoardPopupController;
import Client.viewstartup.StartupController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;

/**
 * Created by Bhagya Rathnayake on 6/2/2017.
 */
public class BoardController extends Application{

    private CardAnimations cardAnimations;
    private MenuButtonAnimations menuButtonAnimations;
    private GeneralButtonActions generalButtonActions;
    private List<ImageView> cardPackList;
    private Stage stage;
    private StartupController startupController;
    private BoardPopupController boardPopupController;
    private Client client;

    @FXML
    Label lblOpponent1,lblOpponent2,lblOpponent3,lblOpponent4,lblOpponent5;
    @FXML
    ImageView opo_1_cd_1,opo_1_cd_2,opo_c_cd_1,crd_pc_1,crd_pc_2,crd_pc_3;
    @FXML
    Button btnHome,btnShuffle;
    @FXML ImageView crd_p1,crd_p2,crd_p3;

    public BoardController()
    {
        cardAnimations= new CardAnimations();
        menuButtonAnimations = new MenuButtonAnimations();
        generalButtonActions= new GeneralButtonActions();
        startupController= new StartupController();
        boardPopupController = new BoardPopupController();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("board.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        primaryStage.setTitle("EXIT-POKER");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public void cardMouseEntered(MouseEvent mouseEvent) {
        ImageView crdView= (ImageView) mouseEvent.getSource();
        cardAnimations.cardMouseEnteredGrow(crdView);
    }

    public void cardMouseExited(MouseEvent mouseEvent) {
        ImageView crdView= (ImageView) mouseEvent.getSource();
        cardAnimations.cardMouseExited(crdView);
    }

    public void cardMouseEnteredPaintBorder(MouseEvent mouseEvent) {
        ImageView crdView= (ImageView) mouseEvent.getSource();
        cardAnimations.cardMouseEnteredPaintBorder(crdView);
    }

    public void cardMouseExitedRemovePaintBorder(MouseEvent mouseEvent) {
        ImageView crdView= (ImageView) mouseEvent.getSource();
        cardAnimations.cardMouseExitedRemoveBorderPaint(crdView);
    }

    public void generalButtonMouseEntered(MouseEvent mouseEvent) {
        Button generatButton=(Button)mouseEvent.getSource();
        menuButtonAnimations.btnMouseEnteredFade(generatButton);
    }

    public void generalButtonMouseExited(MouseEvent mouseEvent) {
        Button generatButton=(Button)mouseEvent.getSource();
        menuButtonAnimations.btnMouseExitedFade(generatButton);
    }

    public void btnQuitClicked(MouseEvent mouseEvent) {
        Button btnQuit=(Button) mouseEvent.getSource();
        generalButtonActions.btnQuitClicked(btnQuit);
    }

    public void cardRotateWhenClicked(MouseEvent mouseEvent) {
        ImageView crdView= (ImageView) mouseEvent.getSource();
        cardAnimations.cardRotateOnClick(crdView);
        crdView.setImage(new Image("Client/Images/Cards/Spades/joker.png"));
    }

    public void btnHelpClicked(MouseEvent mouseEvent) throws IOException {
        Button btnHelp = (Button)mouseEvent.getSource();

        StartupController obj = new StartupController();
        try{
            generalButtonActions.btnHelpClicked(btnHelp);
            cardAnimations.cardDistribute(opo_c_cd_1,764.0,197.02438354492188);

            //cardAnimations.cardPackShuffle();

//            this.methid1();
        }

        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void btnHomeClicked(ActionEvent actionEvent) throws Exception {
//        stage = (Stage) btnHome.getScene().getWindow();
//        startupController.start(stage);
    }


    public void cardRevealWhenClicked(MouseEvent mouseEvent) {
        ImageView crdView = (ImageView)mouseEvent.getSource();
        cardAnimations.changeCardImage(crdView,"joker.png");
    }

    public void btnShuffleClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("boardpopups/popup.fxml"));
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.setResizable(false);
        dialog.show();

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(6000),
                ae ->dialog.close()));
        timeline.play();
    }


    public void crdHideSingle(MouseEvent mouseEvent) {
        ImageView crdView = (ImageView)mouseEvent.getSource();
        cardAnimations.hideSingleCard(crdView);
    }

    public void crdHideMultiple(MouseEvent mouseEvent) {

        ImageView [] arr = {crd_p1,crd_p2,crd_p3};
        cardAnimations.hideMultipleCards(arr);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
