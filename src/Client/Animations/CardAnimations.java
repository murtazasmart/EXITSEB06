package Client.Animations;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.Cursor;
import javafx.scene.effect.DropShadow;
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

        System.out.println("ROTATE ACCESSED");
        crdView = new ImageView();
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(500),crdView);
        rotateTransition.setAxis(Rotate.Y_AXIS);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setCycleCount(1);
        rotateTransition.play();
        
    }

    public void cardPackShuffle(ImageView[] arr)
    {

        for(int i=0;i<arr.length;i++)
        {
            RotateTransition rotateTransition = new RotateTransition(Duration.millis(500),arr[i]);
            rotateTransition.setAxis(Rotate.Y_AXIS);
            rotateTransition.setFromAngle(0);
            rotateTransition.setToAngle(360);
            rotateTransition.setInterpolator(Interpolator.LINEAR);
            rotateTransition.setCycleCount(1);
            rotateTransition.play();
        }
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


}
