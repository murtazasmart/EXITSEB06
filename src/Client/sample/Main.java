package Client.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../../View/startup.fxml"));
        primaryStage.setTitle("EXIT-POKER");
        primaryStage.setScene(new Scene(root, 1470, 1000));
        primaryStage.setMaximized(true);
        //primaryStage.setFullScreen(true);
        primaryStage.show();
//        Client obj = new Client();
//        obj.joinGame();
//        ClientSide obj = new ClientSide();
//        obj.method1();
try{


//    Media sound = new Media();
//    MediaPlayer player = new MediaPlayer(sound);
//    player.setCycleCount(MediaPlayer.INDEFINITE);
//    player.play();
}

catch(Exception e){
    System.out.println(e);
}
    }


    public static void main(String[] args) {
        launch(args);
    }
}
