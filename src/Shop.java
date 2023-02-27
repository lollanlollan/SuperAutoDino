import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class Shop {
    ArrayList<Animal> shop = new ArrayList<>();
    ArrayList<Animal> possibleAnimals = new ArrayList<>();
    int shopSize = 5;
    GridPane gridpane;

    public Shop(GridPane gridpane) throws FileNotFoundException {
        this.gridpane = gridpane;
        Stegosaurus stegosaurus = new Stegosaurus();
        Pterodactyl pterodactyl = new Pterodactyl();
        Tyrannosaurus tyrannosaurus = new Tyrannosaurus();
        Triceratops triceratops = new Triceratops();
        Velociraptor velociraptor = new Velociraptor();
        possibleAnimals.add(stegosaurus);
        possibleAnimals.add(pterodactyl);
        possibleAnimals.add(tyrannosaurus);
        possibleAnimals.add(triceratops);
        possibleAnimals.add(velociraptor);
        populateShop();
    }

    public Shop() throws FileNotFoundException {
        Stegosaurus stegosaurus = new Stegosaurus();
        Pterodactyl pterodactyl = new Pterodactyl();
        Tyrannosaurus tyrannosaurus = new Tyrannosaurus();
        Triceratops triceratops = new Triceratops();
        Velociraptor velociraptor = new Velociraptor();
        possibleAnimals.add(stegosaurus);
        possibleAnimals.add(pterodactyl);
        possibleAnimals.add(tyrannosaurus);
        possibleAnimals.add(triceratops);
        possibleAnimals.add(velociraptor);
        populateShopNoGrid();
    }

    public void populateShopNoGrid() throws FileNotFoundException {
        Random rnd = new Random();
        for (int i = 0; i < shopSize; i++) {
            int rndAnimal = rnd.nextInt(possibleAnimals.size());
            Animal a;
            if (rndAnimal == 0) {
                a = new Stegosaurus();
            } else if (rndAnimal == 1) {
                a = new Pterodactyl();
            } else if (rndAnimal == 2) {
                a = new Tyrannosaurus();
            } else if (rndAnimal == 3) {
                a = new Triceratops();
            } else {
                a = new Velociraptor();
            }
            shop.add(a);
        }
    }

    public void populateShop() throws FileNotFoundException {
        Random rnd = new Random();
        for (int i = 0; i < shopSize; i++) {
            int rndAnimal = rnd.nextInt(possibleAnimals.size());
            Animal a;
            if (rndAnimal == 0) {
                a = new Stegosaurus();
            } else if (rndAnimal == 1) {
                a = new Pterodactyl();
            } else if (rndAnimal == 2) {
                a = new Tyrannosaurus();
            } else if (rndAnimal == 3) {
                a = new Triceratops();
            } else {
                a = new Velociraptor();
            }
            shop.add(a);
            int ind = shop.indexOf(a);

            gridpane.add(a.getImgView(), ind + 1, 0);
            gridpane.add(a.text, ind + 1, 0);

        }
    }

    public void displayShop() throws FileNotFoundException {
        for (Animal animal : shop) {
            int ind = shop.indexOf(animal);
            gridpane.add(animal.getImgView(), 3 + ind, 3);

        }
    }

    public void remove(Animal animal) {
        shop.remove(animal);
        gridpane.getChildren().remove(animal.getImgView());
        gridpane.getChildren().remove(animal.text);
    }

    public void roll() throws FileNotFoundException {
        for (Animal animal : shop) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    gridpane.getChildren().remove(animal.getImgView());
                    gridpane.getChildren().remove(animal.text);
                }
            });
        }
        shop.clear();
        populateShop();
    }
}
