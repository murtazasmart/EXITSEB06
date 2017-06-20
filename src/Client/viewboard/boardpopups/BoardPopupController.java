package Client.viewboard.boardpopups;

import Client.Animations.CardAnimations;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by Bhagya Rathnayake on 6/19/2017.
 */
public class BoardPopupController extends Application {
   private CardAnimations cardAnimations;
    private Timeline cardAnim = new Timeline();
    @FXML
    ImageView pc1,pc2,pc3,pc4;


    public BoardPopupController()
    {
        cardAnimations = new CardAnimations();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public void method1()
    {
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(500),pc1);
        rotateTransition.setAxis(Rotate.Y_AXIS);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setCycleCount(1);
        rotateTransition.play();
//        cardAnimations.cardRotateOnClick(pc1);

    }
}
