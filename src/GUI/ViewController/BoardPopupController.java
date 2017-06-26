package GUI.ViewController;

import Utilities.Animations.CardAnimations;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Bhagya Rathnayake on 6/19/2017.
 */
public class BoardPopupController extends Application implements Initializable{
   private CardAnimations cardAnimations;
    private Timeline cardAnim = new Timeline();
    @FXML
    ImageView pc1,pc2,pc3,pc4,pc5,pc6,pc7,pc8,pc9,pc10,pc11;


    public BoardPopupController()
    {
        cardAnimations = new CardAnimations();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cardAnimations.cardRotateOnClick(pc1);
        cardAnimations.cardRotateOnClick(pc2);
        cardAnimations.cardRotateOnClick(pc3);
        cardAnimations.cardRotateOnClick(pc4);
        cardAnimations.cardRotateOnClick(pc5);
        cardAnimations.cardRotateOnClick(pc6);
        cardAnimations.cardRotateOnClick(pc7);
        cardAnimations.cardRotateOnClick(pc8);
        cardAnimations.cardRotateOnClick(pc9);
        cardAnimations.cardRotateOnClick(pc10);
        cardAnimations.cardRotateOnClick(pc11);

    }
}
