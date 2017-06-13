package UIButtonActions;

import helpscene.HelpController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Bhagya Rathnayake on 6/2/2017.
 */
public class GeneralButtonActions{
    private Stage stage;
    private  HelpController helpController;
    public GeneralButtonActions()
    {
      helpController = new HelpController();
    }


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

    public void btnHelpClicked(Button btnHelp) throws IOException {
        try{
            stage = (Stage) btnHelp.getScene().getWindow();
            helpController.start(stage);
        }

        catch(Exception e)
        {
            System.out.println(e);
        }
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
