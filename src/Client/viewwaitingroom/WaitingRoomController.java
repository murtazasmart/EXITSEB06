package Client.viewwaitingroom;

import Client.Client;
import Client.viewboard.BoardController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by MA_Laptop on 6/21/2017.
 */
public class WaitingRoomController extends Application{

    Client client;
    WaitingRoomService waitingRoomService;

    @FXML
    ImageView imageView;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("waitingroom.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        primaryStage.setTitle("EXIT-POKER");
        primaryStage.setScene(new Scene(root, 1470, 1000));
        primaryStage.show();
        waitingRoomService = new WaitingRoomService();
        waitingRoomService.setClient(client);

        // Lambda Runnable
        Runnable task2 = () -> {
            waitingRoomService.waitngForAllPlayers();
            Platform.runLater(() -> {
                Stage stage = (Stage) imageView.getScene().getWindow();
                BoardController boardController = new BoardController();
                boardController.setClient(client);
                try {
                    boardController.start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        };

        // start the thread
        new Thread(task2).start();

    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
