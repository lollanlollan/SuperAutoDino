import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Board {
    ArrayList<Animal> board = new ArrayList<>();
    GridPane gridpaneShop;

    public Board(GridPane gridpane) {
        this.gridpaneShop = gridpane;
    }

    public Board(){

    }

    public void addAnimal(Animal animalToAdd) throws FileNotFoundException {
        board.add(animalToAdd);
        int ind = board.indexOf(animalToAdd);
        gridpaneShop.add(animalToAdd.getImgView(), ind + 1, 3);
        gridpaneShop.add(animalToAdd.text, ind + 1, 3);
    }

    public void addAnimalNoGrid(Animal animalToAdd) throws FileNotFoundException {
        board.add(animalToAdd);
    }

    public boolean isRoom() {
        return board.size() < 4;
    }

    public void displayBoard(GridPane gridpaneFight, boolean side) throws FileNotFoundException {
        if(side){
            for (Animal animal : board) {
                int ind = board.indexOf(animal);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        gridpaneFight.add(animal.getImgView(), ind + 1, 2);
                        gridpaneFight.add(animal.text, ind + 1, 2);
                    }
                });

            }
        }else{
            for (Animal animal : board) {
                int ind = board.indexOf(animal);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        gridpaneFight.add(animal.getImgView(), 9 - (ind + 1), 2);
                        gridpaneFight.add(animal.text, 9 - (ind + 1), 2);
                    }
                });

            }
        }
    }

    public void displayBoardShop(){
        for (Animal animal : board) {
            int ind = board.indexOf(animal);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    gridpaneShop.add(animal.getImgView(), ind + 1, 2);
                    gridpaneShop.add(animal.text, ind + 1, 2);
                }
            });

        }
    }

    public void removeAnimal(Animal animal) {
        board.remove(animal);
        gridpaneShop.getChildren().remove(animal.getImgView());
        gridpaneShop.getChildren().remove(animal.text);
    }

    public void removeAnimalFight(Animal animal, GridPane gridpaneFight) {
        //board.remove(animal);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                gridpaneFight.getChildren().remove(animal.getImgView());
                gridpaneFight.getChildren().remove(animal.text);
            }
        });

    }

}
