package UIButtonActions;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Created by Bhagya Rathnayake on 6/2/2017.
 */
public class GeneralButtonActions {

    public void btnQuitClicked(Button btnQuit)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning!");
        alert.setHeaderText("You are about to exit. Any Unsaved changes will be lost");
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage = (Stage) btnQuit.getScene().getWindow();
            stage.close();
        } else {

        }
    }

    public void btnHelpClicked()
    {

    }

    public void btnInfoClicked()
    {

    }

    public void btnSettingsClicked()
    {

    }

    public void btnHomeClicked()
    {

    }

}
