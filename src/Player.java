import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Player {
    int money = 10;
    int lifes = 10;
    int wins = 0;
    Board board;
    Board opponent;
    Shop shop;

    public Player(GridPane gridpaneShop, Board opponent) throws FileNotFoundException {
        this.opponent = opponent;
        this.shop = new Shop(gridpaneShop);
        this.board = new Board(gridpaneShop);
    }

    public Player() throws FileNotFoundException {
        this.shop = new Shop();
        this.board = new Board();
        buyNoGrid(shop.shop.get(0));
        buyNoGrid(shop.shop.get(1));
        buyNoGrid(shop.shop.get(3));
    }

    public int getMoney(){
        return money;
    }

    public Board getBoard(){
        return board;
    }

    public void roll() throws FileNotFoundException {
        if (money > 0) {
            shop.roll();
            money--;
        } else {
            System.out.println("Need more money");
        }
    }

    public void buyNoGrid(Animal animal) throws FileNotFoundException {
        if (money >= 3 && board.isRoom()) {
            money = money - 3;
            board.addAnimalNoGrid(animal);
        } else if (money < 3) {
            System.out.println("Need more money");
        } else {
            System.out.println("No space on board");
        }
    }

    public void buy(Animal animal) throws FileNotFoundException {
        if (money >= 3 && board.isRoom()) {
            money = money - 3;
            shop.remove(animal);
            board.addAnimal(animal);
        } else if (money < 3) {
            System.out.println("Need more money");
        } else {
            System.out.println("No space on board");
        }
    }

    public String fightAgainstOtherPlayer(Board opponent, GridPane gridpaneFight) throws FileNotFoundException, InterruptedException {
        opponent.displayBoard(gridpaneFight,true);
        board.displayBoard(gridpaneFight, false);
        ArrayList<Animal> animalsInFightOpp = new ArrayList<>();
        for(Animal a : opponent.board){
            animalsInFightOpp.add(a);
        }
        ArrayList<Animal> animalsInFightPl1 = new ArrayList<>(board.board);
        for(Animal a : board.board){
            animalsInFightPl1.add(a);
        }
        while(!animalsInFightOpp.isEmpty() && !animalsInFightPl1.isEmpty()){

            TimeUnit.SECONDS.sleep(2);
            Animal oppAnimal = animalsInFightOpp.get(animalsInFightOpp.size()-1);
            Animal myAnimal = animalsInFightPl1.get(animalsInFightPl1.size()-1);
            System.out.println("-----------");
            System.out.println(oppAnimal.health);
            System.out.println(myAnimal.health);
            oppAnimal.attack(myAnimal);
            myAnimal.attack(oppAnimal);
            if(!oppAnimal.isAlive()){
                animalsInFightOpp.remove(oppAnimal);
                opponent.removeAnimalFight(oppAnimal, gridpaneFight);
            }if(!myAnimal.isAlive()){
                animalsInFightPl1.remove(myAnimal);
                board.removeAnimalFight(myAnimal, gridpaneFight);
            }
        }
        TimeUnit.SECONDS.sleep(2);
        if(animalsInFightOpp.isEmpty() && animalsInFightPl1.isEmpty()){
            return "draw";
        }else if(animalsInFightOpp.isEmpty()){
            return "win";
        }else{
            return "lost";
        }
    }

    public void sell(Animal animal) {
        money++;
        board.removeAnimal(animal);
    }

    public void newTurn() throws FileNotFoundException {
        money = 11;
        roll();
    }
}
