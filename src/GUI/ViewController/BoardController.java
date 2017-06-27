package GUI.ViewController;

import Utilities.Animations.CardAnimations;
import Utilities.Animations.MenuButtonAnimations;
import Model.Client;
import Utilities.UIButtonActions.GeneralButtonActions;
import GUI.Services.BoardService;
import Model.Message;
import Model.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Bhagya Rathnayake on 6/2/2017.
 */
public class BoardController extends Application{

    private CardAnimations cardAnimations;
    private MenuButtonAnimations menuButtonAnimations;
    private GeneralButtonActions generalButtonActions;
    private List<ImageView> cardPackList;
    private Stage stage;
    private StartupController startupController;
    private BoardPopupController boardPopupController;
    BoardService boardService;
    private Client client;
    Player player;
    int myPlayerId;
    ImageView[] crd_pArr; //All oponents cards stored here
    Label[] lblPlayers;
    String[][] cardHand;
    boolean gameEnd;
    boolean btnShuffleClicked;
    private boolean[] arrSwapCards;

    @FXML
    Label lblPlayer1,lblPlayer2,lblPlayer3,lblPlayer4,lblPlayer5,lblPlayer6, lblScore, lblUsername, lblHints, lblWaitingForPlayers;
    @FXML
    ImageView opo_1_cd_1,opo_1_cd_2,opo_c_cd_1,crd_pc_1,crd_pc_2,crd_pc_3;
    @FXML
    Button btnHome,btnShuffle;
    @FXML ImageView crd_p1,crd_p11,crd_p2,crd_p21,crd_p3,crd_31,crd_p4,crd_p41,crd_p5,crd_p51,crd_p6,crd_p61,crdMain1,crdMain2,crdMain3,crdMain4,crdMain5;
    @FXML
    private List<ImageView> crdPackAllPlayers,crdPackPlayer1,crdPackPlayer2,crdPackPlayer3,crdPackPlayer4,crdPackPlayer5,crdPackPlayer6 ;

    public BoardController()
    {
        cardAnimations= new CardAnimations();
        menuButtonAnimations = new MenuButtonAnimations();
        generalButtonActions= new GeneralButtonActions();
        startupController= new StartupController();
        boardPopupController = new BoardPopupController();
        arrSwapCards= new boolean[5];
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/board.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        primaryStage.setTitle("EXIT-POKER");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        primaryStage.show();
        //crdMArr = new ImageView[]{crdMain1,crdMain2,crdMain3,crdMain4,crdMain5};
        boardService = new BoardService();
        boardService.setClient(client);
        lblPlayers = new Label[]{lblPlayer1,lblPlayer2,lblPlayer3,lblPlayer4,lblPlayer5,lblPlayer6};
        ImageView[] crd_pArr2 = {crd_p1,crd_p11,crd_p2,crd_p21,crd_p3,crd_31,crd_p4,crd_p41,crd_p5,crd_p51,crd_p6,crd_p61};
        crd_pArr = crd_pArr2;
        crdMain1.setEffect(new DropShadow(6d, 6d, 6d, Color.GOLD));
        crdMain2.setEffect(new DropShadow(4d, 4d, 4d, Color.GOLD));
        lblWaitingForPlayers.setText("");
        btnShuffleClicked = false;
        roundPartA();
        //INSERT TIMER FOR ROUND A AND ALSO FREEZE BEFORE SUBMITTING
    }

    public void cardMouseEntered(MouseEvent mouseEvent) {
        ImageView crdView= (ImageView) mouseEvent.getSource();
        cardAnimations.cardMouseEnteredGrow(crdView);
    }

    public void cardMouseExited(MouseEvent mouseEvent) {
        ImageView crdView= (ImageView) mouseEvent.getSource();
        cardAnimations.cardMouseExited(crdView);
    }

    public void cardMouseEnteredPaintBorder(MouseEvent mouseEvent) {
        ImageView crdView= (ImageView) mouseEvent.getSource();
        cardAnimations.cardMouseEnteredPaintBorder(crdView);
    }

    public void cardMouseExitedRemovePaintBorder(MouseEvent mouseEvent) {
        ImageView crdView= (ImageView) mouseEvent.getSource();
        cardAnimations.cardMouseExitedRemoveBorderPaint(crdView);
    }

    public void generalButtonMouseEntered(MouseEvent mouseEvent) {
        Button generatButton=(Button)mouseEvent.getSource();
        menuButtonAnimations.btnMouseEnteredFade(generatButton);
    }

    public void generalButtonMouseExited(MouseEvent mouseEvent) {
        Button generatButton=(Button)mouseEvent.getSource();
        menuButtonAnimations.btnMouseExitedFade(generatButton);
    }

    public void btnQuitClicked(MouseEvent mouseEvent) {
        Button btnQuit=(Button) mouseEvent.getSource();
        generalButtonActions.btnQuitClicked(btnQuit);
    }

    public void cardRotateWhenClicked(MouseEvent mouseEvent) {
        ImageView crdView= (ImageView) mouseEvent.getSource();
        cardAnimations.cardRotateOnClick(crdView);
        crdView.setImage(new Image("Resources/Images/Cards/Spades/joker.png"));
    }

    public void btnHelpClicked(MouseEvent mouseEvent) throws IOException {
        Button btnHelp = (Button)mouseEvent.getSource();

        StartupController obj = new StartupController();
        try{
            generalButtonActions.btnHelpClicked(btnHelp);
            cardAnimations.cardDistribute(opo_c_cd_1,764.0,197.02438354492188);
        }

        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void btnHomeClicked(ActionEvent actionEvent) throws Exception {
//        stage = (Stage) btnHome.getScene().getWindow();
//        startupController.start(stage);
    }


    public void cardRevealWhenClicked(MouseEvent mouseEvent) {
        ImageView crdView = (ImageView)mouseEvent.getSource();
        cardAnimations.changeCardImage(crdView,"joker.png");
    }

    public void btnShuffleClicked(ActionEvent actionEvent) throws IOException {
        if(!btnShuffleClicked)
            roundPartB();
        btnShuffleClicked = true;
    }

    public String generateFileLocation(String cardHand){
        String folderName, fileName, cardFileLocation;
        folderName = getCardFolderName(cardHand);
        fileName = getCardFileName(cardHand);
        cardFileLocation = "/Resources/Images/Cards/" + folderName + "/" + fileName + ".png";
        System.out.println(cardFileLocation);
        return cardFileLocation;
    }

    public String getCardFileName(String cardHand) {
        String fileName, res;
        res = getCardType(cardHand);
        if(res.equalsIgnoreCase("H"))
            fileName = "H";
        else if(res.equalsIgnoreCase("D"))
            fileName = "D";
        else if(res.equalsIgnoreCase("S"))
            fileName = "S";
        else
            fileName = "C";
        res = getCardValue(cardHand);
        fileName = fileName+res;
        return fileName;
    }

    private String getCardType(String str){
        String type=str.substring(0,str.indexOf("-"));
        return type;
    }

    private String getCardValue(String str){
        String value = str.substring(str.indexOf("-")+1);
        return value;
    }

    private String getCardFolderName(String cardHand) {
        String folderName,res;
        res = getCardType(cardHand);
        if(res.equalsIgnoreCase("H"))
            folderName = "Hearts";
        else if(res.equalsIgnoreCase("D"))
            folderName = "Diamonds";
        else if(res.equalsIgnoreCase("S"))
            folderName = "Spades";
        else
            folderName = "Clubs";

        return folderName;
    }

    public boolean roundPartA(){
        player = boardService.getPlayerFromServer();
        lblUsername.setText(player.getUsername());
        for(int i = 0;i<player.getNumberofplayers();i++){
            if(i == player.getPlayerId()){
                continue;
            }
            else{
                lblPlayers[i].setText(player.getAllUsernames()[i]);
            }
            System.out.println(player.getAllUsernames()[i]);
        }
        cardHand = player.getCardHand();
        updateMainCardPack(cardHand);
        int i = 0, j = 0;
        if(i != player.getPlayerId() && i < player.getNumberofplayers()) {
            for (ImageView imageView : crdPackPlayer1) {
                imageView.setImage(new Image(generateFileLocation(cardHand[i][j])));
                j++;
            }
        }
        i++; j = 0;
        if(i != player.getPlayerId() && i < player.getNumberofplayers()) {
            for (ImageView imageView : crdPackPlayer2) {
                imageView.setImage(new Image(generateFileLocation(cardHand[i][j])));
                j++;
            }
        }
        i++; j = 0;
        if(i != player.getPlayerId() && i < player.getNumberofplayers()) {
            for (ImageView imageView : crdPackPlayer3) {
                imageView.setImage(new Image(generateFileLocation(cardHand[i][j])));
                j++;
            }
        }
        i++; j = 0;
        if(i != player.getPlayerId() && i < player.getNumberofplayers()) {
            for (ImageView imageView : crdPackPlayer4) {
                imageView.setImage(new Image(generateFileLocation(cardHand[i][j])));
                j++;
            }
        }
        i++; j = 0;
        if(i != player.getPlayerId() && i < player.getNumberofplayers()) {
            for (ImageView imageView : crdPackPlayer5) {
                imageView.setImage(new Image(generateFileLocation(cardHand[i][j])));
                j++;
            }
        }
        i++; j = 0;
        if(i != player.getPlayerId() && i < player.getNumberofplayers()) {
            for (ImageView imageView : crdPackPlayer6) {
                imageView.setImage(new Image(generateFileLocation(cardHand[i][j])));
                j++;
            }
        }
        lblHints.setText("Observe your opponents cards before they disappear!");
        lblScore.setText("You have only 20 seconds.");

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(10000),
                a-> {
                    hideOtherCardPacks();
                    lblHints.setText("You can now swap a maximum of 3 cards, click to swap!!");
                }
                ));
        timeline.play();

        Timeline timelineRoundA = new Timeline(new KeyFrame(
                Duration.millis(20000),
                a-> {
                    roundPartB();
                    btnShuffleClicked = true;
                }
        ));
        timelineRoundA.play();

        return true;
    }

    public boolean roundPartB(){
//        //UPDATE SWAP OPTION CODE HAS TO BE INSERTED
        lblWaitingForPlayers.setText("Waiting other players...");
        // Lambda Runnable
        Runnable t1 = () -> {
            //UPDATE SWAP OPTION CODE HAS TO BE INSERTED
            player.setSwapCards(arrSwapCards);
            boardService.sendPlayerToServer(player);
            player = boardService.getPlayerFromServer();

            cardHand = player.getCardHand();
            updateMainCardPack(cardHand);

            Platform.runLater( ()->{
                lblHints.setText("Check out your swapped cards.");

                lblScore.setText(String.valueOf(player.getScore()));
                lblHints.setText("Your final score.");
                //hideOtherCardPacks();
                //UPDATE BOARD CODE HAS TO BE INSERTED

                if(player.isKicked() == true){
                    System.out.println("You've been kicked");
                    if(player.getAllUsernames().length >1){
                        for(int i =0;i<player.getAllUsernames().length;i++){
                            System.out.print(player.getAllUsernames()[i]+" ");
                        }
                        //HANDLE IF WRONG PLAYER NAME INCLUDED
                        if(player.getNumberofplayers() > 2){
                            List<String> choices = new ArrayList<>();
                            for(int i =0;i<player.getNumberofplayers();i++){
                                if(i!=player.getPlayerId())
                                    choices.add(player.getAllUsernames()[i]);
                            }
                            stage = (Stage) btnShuffle.getScene().getWindow();
                            ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
                            dialog.setTitle("You LOSE!!");
                            dialog.setHeaderText("Donate your points");
                            dialog.setContentText("Choose a person to donate quarter of your points::");
                            Optional<String> result = dialog.showAndWait();
                            System.out.println(result.get());
                            for(int i =0;i<player.getAllUsernames().length;i++){
                                if(player.getAllUsernames()[i].equalsIgnoreCase(result.get()))
                                    player.setPlayerIdToDonatePoints(i);
                            }
                        }
                    }
                }

                // Lambda Runnable
                Runnable t2 = () -> {

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("about to send");

                    boardService.sendPlayerToServer(player);

                    System.out.println("about to receive");

                    boardService.getPlayerFromServer();

                    final Message msg = boardService.getMessageFromServer();

                    Platform.runLater( ()-> {

                        lblWaitingForPlayers.setText("");

                        if(player.getNumberofplayers()>2){
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Points Update");
                            alert.setHeaderText("Points Update");
                            alert.setContentText(msg.getText());
                            lblWaitingForPlayers.setText(msg.getText());
                            //alert.showAndWait();
                        }

                        if(player.isKicked() != true && player.getNumberofplayers() <= 2){
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("You Won");
                            alert.setHeaderText("Your score is "+player.getScore());
                            alert.setContentText("Congratulations you WON IN FOKER");

                            alert.showAndWait();
                        }

                        if(player.isKicked()){
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("You LOSE");
                            alert.setHeaderText("Score is "+player.getScore());
                            alert.setContentText("You had the lowest score. You've been kicked from the game. You lose! Better luck next time. ");

                            alert.showAndWait();
                        }

                        if(player.getNumberofplayers() == 2){
                            gameEnd = true;
                        }

                        if(player.isKicked()){
                            stage = (Stage)btnShuffle.getScene().getWindow();
                            StartupController startupController = new StartupController();
                            startupController.setClient(client);
                            startupController.start(stage);
                        }

                        if(!gameEnd && !player.isKicked()){
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Next round is starting");
                            alert.setHeaderText("Your score is "+player.getScore());
                            alert.setContentText("Concentrate. You are moving to the next round ");
                            alert.show();

                            Stage stage = (Stage)btnShuffle.getScene().getWindow();
                            BoardController boardController = new BoardController();
                            boardController.setClient(client);
                            try {
                                boardController.start(stage);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                };

                new Thread(t2).start();
            });
        };
        new Thread(t1).start();

        //DONATE POINTS CODE

        //SEND PLAYER OBJECT TO SERVER CODE

        //RECEIVE PLAYER FROM SERVER AND SHOW FINAL POINTS

        //RECEIVE DONATION DETAILS MESSAGE OBJECT FROM SERVER

        //CHECK IF KICKED AND OR END, IF SO EXIT CODE OR LOOP CODE
        return true;
    }

    public void updateMainCardPack(String cardHand[][]){
        for(int i = 0 ; i<cardHand.length;i++){
            if(i == player.getPlayerId()){ //ALtered have to check if working same manner as client file
                crdMain1.setImage(new Image(generateFileLocation(cardHand[i][0])));
                crdMain2.setImage(new Image(generateFileLocation(cardHand[i][1])));
                crdMain3.setImage(new Image(generateFileLocation(cardHand[i][2])));
                crdMain4.setImage(new Image(generateFileLocation(cardHand[i][3])));
                crdMain5.setImage(new Image(generateFileLocation(cardHand[i][4])));
                System.out.println("player "+player.getUsername()+" score is "+player.getScore());
            }else{
                System.out.println("Player "+player.getAllUsernames()[i]+" 2 inital cards are "+player.getIndividualCardHand(i,0)+" "+player.getIndividualCardHand(i,1));
            }

        }
    }

    public void hideOtherCardPacks(){
        ImageView [] arr = {crd_p1,crd_p11,crd_p2,crd_p21,crd_p3,crd_31,crd_p4,crd_p41,crd_p5,crd_p51,crd_p6,crd_p61};
//        cardAnimations.hideMultipleCards(arr);
        //Platform.runLater(() -> cardAnimations.changeMultipleCards(arr,"Spades/back.png"));
        for(ImageView img : arr){
            if(img!=null)
            img.setImage(new Image("Resources/Images/Cards/Spades/back.png"));
        }
    }

    public void selectedCards(MouseEvent mouseEvent) {
        ImageView crdView = (ImageView) mouseEvent.getSource();
        String cardID = crdView.getId();
        switch (cardID) {
            case "crdMain5":
                arrSwapCards[4] = (arrSwapCards[4])?false:true;
                if(arrSwapCards[4]) cardAnimations.cardMouseEnteredGrow(crdMain5); else cardAnimations.cardMouseExited(crdMain5);
                System.out.println("card swapped id 4 " +arrSwapCards[4]);
                break;
            case "crdMain4":
                arrSwapCards[3] = (arrSwapCards[3])?false:true;
                if(arrSwapCards[3]) cardAnimations.cardMouseEnteredGrow(crdMain4); else cardAnimations.cardMouseExited(crdMain4);
                System.out.println("card swapped id 3 " +arrSwapCards[3]);
                break;
            case "crdMain3":
                arrSwapCards[2] = (arrSwapCards[2])?false:true;
                if(arrSwapCards[2]) cardAnimations.cardMouseEnteredGrow(crdMain3); else cardAnimations.cardMouseExited(crdMain3);
                System.out.println("card swapped id 2 " +arrSwapCards[2]);
                break;
            case "crdMain2":
                arrSwapCards[1] = (arrSwapCards[1])?false:true;
                if(arrSwapCards[1]) cardAnimations.cardMouseEnteredGrow(crdMain2); else cardAnimations.cardMouseExited(crdMain2);
                System.out.println("card swapped id 1" +arrSwapCards[1]);
                break;
            case "crdMain1":
                arrSwapCards[0] = (arrSwapCards[0])?false:true;
                if(arrSwapCards[0]) cardAnimations.cardMouseEnteredGrow(crdMain1); else cardAnimations.cardMouseExited(crdMain1);
                System.out.println("card swapped id 0 " +arrSwapCards[0]);
                break;
        }
    }

    public void swapCards(ActionEvent actionEvent) {
        player.setSwapCards(arrSwapCards);
    }


    public void crdHideSingle(MouseEvent mouseEvent) {
        ImageView crdView = (ImageView)mouseEvent.getSource();
        cardAnimations.hideSingleCard(crdView);
    }

    public void crdHideMultiple(MouseEvent mouseEvent) {

        ImageView [] arr = {crd_p1,crd_p2,crd_p3};
        cardAnimations.hideMultipleCards(arr);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
