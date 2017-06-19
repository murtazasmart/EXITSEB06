package Client.viewboard;

import Client.Animations.CardAnimations;
import Client.Animations.MenuButtonAnimations;
import Client.UIButtonActions.GeneralButtonActions;
import javafx.application.Application;
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
import Client.viewstartup.StartupController;
import javafx.stage.Stage;

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
    @FXML
    Label lblOpponent1,lblOpponent2,lblOpponent3,lblOpponent4,lblOpponent5;
    @FXML
    ImageView opo_1_cd_1,opo_1_cd_2,opo_c_cd_1,crd_pc_1,crd_pc_2,crd_pc_3;


    ImageView arr[] ={crd_pc_1,crd_pc_2,crd_pc_3};
    public BoardController()
    {
        cardAnimations= new CardAnimations();
        menuButtonAnimations = new MenuButtonAnimations();
        generalButtonActions= new GeneralButtonActions();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("board.fxml"));
        primaryStage.setTitle("EXIT-POKER");
        primaryStage.setScene(new Scene(root, 1470, 1000));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public void methid1()
    {
        Bounds boundsInScene = opo_c_cd_1.localToScene(opo_1_cd_1.getBoundsInLocal());
        System.out.println(boundsInScene.getMaxX()+" "+boundsInScene.getMaxY());
        opo_1_cd_1.setY(5);
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
}
