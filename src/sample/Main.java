package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../hostscene/host.fxml"));
        primaryStage.setTitle("EXIT-POKER");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setMaximized(true);
        primaryStage.show();
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
