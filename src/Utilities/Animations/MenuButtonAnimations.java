package Utilities.Animations;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.util.Duration;

/**
 * Created by Bhagya Rathnayake on 6/1/2017.
 */
public class MenuButtonAnimations {

    public void btnMouseEnteredFade(Button btn){
        btn.setCursor(Cursor.HAND);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(50),btn);
        fadeTransition.setInterpolator(Interpolator.EASE_IN);
        fadeTransition.setFromValue(0.5);
        fadeTransition.setToValue(1);
        fadeTransition.setCycleCount(1);
        fadeTransition.play();
    }

    public void btnMouseExitedFade(Button btn)
    {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(50),btn);
        fadeTransition.setInterpolator(Interpolator.EASE_IN);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0.5);
        fadeTransition.setCycleCount(1);
        fadeTransition.play();
    }

    public void btnClickedDispose(Button btn)
    {

    }

    public void btnClickedSlideOutRight(Button btn)
    {

    }

    public void btnClickedSlideOutLeft(Button btn)
    {

    }

}
