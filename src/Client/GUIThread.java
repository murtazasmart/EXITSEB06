package Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by MA_Laptop on 6/21/2017.
 */
public class GUIThread extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/connecttoserver.fxml"));
        primaryStage.setTitle("EXIT-POKER");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
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
