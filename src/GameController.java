import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileNotFoundException;

public class GameController extends Application {
    public GridPane gridpaneShop = new GridPane();
    public GridPane gridpaneFight = new GridPane();
    public Scene shopScene = new Scene(gridpaneShop);
    public Scene fightScene = new Scene(gridpaneFight);
    public Stage stage;
    public Player player;
    public Text moneyText;
    public Board opponentBoard;

    public static void main(String[] args) throws FileNotFoundException {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        setUpGraphics(stage);
        setUpGame();
        startGame();
    }

    public void createBackground(String fileName, GridPane gridpane) {
        Image img = new Image(fileName);
        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background bGround = new Background(bImg);
        gridpane.setMinSize(1500, 750);
        gridpane.setVgap(1);
        gridpane.setHgap(1);
        gridpane.setBackground(bGround);
        gridpane.setGridLinesVisible(true);
        for (int i = 0; i < 1500 / 150; i++) {
            for (int j = 0; j < 750 / 150; j++) {
                Rectangle rec = new Rectangle(150, 150);
                rec.setStroke(Color.GREY);
                rec.setFill(Color.TRANSPARENT);
                GridPane.setColumnIndex(rec, i);
                GridPane.setRowIndex(rec, j);
                gridpane.getChildren().addAll(rec);
            }
        }
    }

    public void setUpGraphics(Stage stage){
        this.stage = stage;
        stage.setTitle("Super hero auto dino");
        createBackground("backgroundShop.png", gridpaneShop);
        createBackground("back.png", gridpaneFight);
        stage.setScene(shopScene);
        stage.show();
        moneyText = new Text(Integer.toString(10));
        moneyText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        gridpaneShop.add(moneyText, 7, 0);
    }

    public void setUpGame() throws FileNotFoundException {
        Player opponent = new Player();
        opponentBoard = opponent.getBoard();
        player = new Player(gridpaneShop, opponentBoard);
    }

    public void startGame(){
        makeAllClickable();
        clickableRoll();
        clickableEndTurn();
    }

    public void updateMoney() {
        moneyText.setText(Integer.toString(player.getMoney()));
    }

    public void makeAllClickable() {
        for (Animal animal : player.shop.shop) {
            clickableAnimal(animal);
        }
    }

    public void clickableEndTurn() {
        Rectangle rectangle = new Rectangle(151, 70);
        rectangle.setFill(Color.MEDIUMVIOLETRED);
        rectangle.setStroke(Color.DARKRED);
        Text nextText = new Text("   End Turn");
        nextText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        gridpaneShop.add(rectangle, 8, 0);
        gridpaneShop.add(nextText, 8, 0);

        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                stage.setScene(fightScene);
                Runnable task = new Runnable() {
                    public void run() {
                        String win = "";
                        try {
                            win = player.fightAgainstOtherPlayer(opponentBoard, gridpaneFight);
                        } catch (FileNotFoundException | InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        if(win.equals("win") && player.wins == 10){
                            quitGame(win);
                        }else if(win.equals("lost") && player.lifes <= 0){
                            quitGame(win);
                        }else{
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    stage.setScene(shopScene);
                                    try {
                                        player.newTurn();
                                    } catch (FileNotFoundException ex) {
                                        ex.printStackTrace();
                                    }
                                    //player.board.displayBoardShop();
                                    makeAllClickable();
                                    updateMoney();
                                }
                            });

                        }
                    }
                };
                Thread backgroundThread = new Thread(task);
                backgroundThread.setDaemon(true);
                backgroundThread.start();
            }
        };
        rectangle.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        nextText.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    public void quitGame(String win){
        if(win.equals("win")){
            System.out.println("you won");
        }else{
            System.out.println("you lost loser");
        }
    }

    public void clickableRoll() {
        Rectangle rectangle = new Rectangle(80, 50);
        rectangle.setFill(Color.BLUE);
        Text rerollText = new Text(" Reroll");
        rerollText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        gridpaneShop.getChildren().add(rectangle);
        gridpaneShop.add(rerollText, 0, 0);
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try {
                    player.roll();
                    updateMoney();
                    makeAllClickable();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        };
        rectangle.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        rerollText.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    public void clickableAnimal(Animal animal) {
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try {
                    System.out.println("trycker");
                    if (player.shop.shop.contains(animal)) {
                        player.buy(animal);
                    } else {
                        player.sell(animal);
                    }
                    updateMoney();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        };
        animal.imgView.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
    }
}
