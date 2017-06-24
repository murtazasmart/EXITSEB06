package Client;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by MA_Laptop on 6/21/2017.
 */
public class TrialServiceClass extends Application
{

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Client/viewconnecttoserver/connecttoserver.fxml"));
        primaryStage.setTitle("EXIT-POKER");
        primaryStage.setScene(new Scene(root, 1470, 1000));
        primaryStage.setMaximized(true);
        primaryStage.show();

        List<String> choices = new ArrayList<>();
        choices.add("a");
        choices.add("b");
        choices.add("c");
        ChoiceDialog<String> dialog = new ChoiceDialog<>("b", choices);
        dialog.setTitle("Choice Dialog");
        dialog.setHeaderText("Look, a Choice Dialog");
        dialog.setContentText("Choose your letter:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(letter -> System.out.println("Your choice: " + letter));

    }

    public void getStage(){

    }

    public void startView() {
        launch();
    }
    /*
    public void changeScene(Scene scene){
        stage = (Stage) btnJoin.getScene().getWindow();
        joinController.start(stage);
    }

    public void startJoinController(Stage){

    }*/
}
