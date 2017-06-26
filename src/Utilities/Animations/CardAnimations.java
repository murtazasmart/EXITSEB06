package Utilities.Animations;

import javafx.animation.*;
import javafx.scene.Cursor;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * Created by Bhagya Rathnayake on 6/2/2017.
 */
public class CardAnimations {

    public void cardMouseEnteredGrow(ImageView crdView)
    {
        crdView.setCursor(Cursor.HAND);
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(50),crdView);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(1.3);
        scaleTransition.setToY(1.3);
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();
    }

    public void  cardMouseExited(ImageView crdView)
    {

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(50),crdView);
        scaleTransition.setFromX(1.3);
        scaleTransition.setFromY(1.3);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();
    }

    public void cardMouseEnteredPaintBorder(ImageView crdView)
    {
        crdView.setEffect(new DropShadow(2d, 0d, +4d, Color.AQUA));
    }

    public void cardMouseExitedRemoveBorderPaint(ImageView crdView)
    {
        crdView.setEffect(null);
    }

    public void cardRotateOnClick(ImageView crdView)
    {
        System.out.println(crdView);
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(2000),crdView);
        rotateTransition.setAxis(Rotate.Y_AXIS);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setCycleCount(Animation.INDEFINITE);
        rotateTransition.play();
        
    }

    public void cardDistribute(ImageView image,Double toX,Double toY)
    {
        System.out.println(toX);
        Path path = new Path();

        //Creating 1st line
        LineTo line1 = new LineTo(image.getLayoutX(),image.getLayoutY());
        path.getElements().add(new MoveTo(toX,toY));
        path.getElements().addAll(line1);
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(1000));
        pathTransition.setNode(image);
        pathTransition.setPath(path);
        pathTransition.setCycleCount(1);
        pathTransition.play();
    }

    public void changeCardImage(ImageView crdView,String fileName)
    {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500),crdView);
        fadeTransition.setInterpolator(Interpolator.EASE_IN);
        fadeTransition.setFromValue(0.5);
        fadeTransition.setToValue(1);
        fadeTransition.setCycleCount(1);
        fadeTransition.play();
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(50),
                ae -> crdView.setImage(new Image("Resources/Images/Cards/Spades/" +fileName))));
        timeline.play();


    }

    public void changeMultipleCards(ImageView[] crdView,String fileName){
        for(ImageView cardView: crdView){
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(500),cardView);
            fadeTransition.setInterpolator(Interpolator.EASE_IN);
            fadeTransition.setFromValue(0.5);
            fadeTransition.setToValue(1);
            fadeTransition.setCycleCount(1);
            fadeTransition.play();
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(50),
                    ae -> cardView.setImage(new Image("Resources/Images/Cards/" +fileName))));
            timeline.play();
        }

    }

    public void hideSingleCard(ImageView cardView)
    {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500),cardView);
        fadeTransition.setInterpolator(Interpolator.EASE_IN);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setCycleCount(1);
        fadeTransition.play();
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(500),
                ae -> cardView.setVisible(false)));
        timeline.play();

    }

    public void hideMultipleCards(ImageView[] cardsView)
    {
        for(ImageView cardsViews:cardsView)
        {
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(500),cardsViews);
            fadeTransition.setInterpolator(Interpolator.EASE_IN);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
            fadeTransition.setCycleCount(1);
            fadeTransition.play();
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(500),
                    ae -> cardsViews.setVisible(false)));
            timeline.play();
        }
    }

}
