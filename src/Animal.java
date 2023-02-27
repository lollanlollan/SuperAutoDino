import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Animal {
    int health;
    int attack;
    int animalNr;
    ImageView imgView;
    Text text;

    public Animal(int health, int attack, int animalNr, String fileName) throws FileNotFoundException {
        this.health = health;
        this.attack = attack;
        this.animalNr = animalNr;
        Image image = new Image(new FileInputStream(fileName));
        ImageView imgView = new ImageView(image);
        imgView.setFitHeight(150);
        imgView.setFitWidth(150);
        imgView.setPickOnBounds(true);
        this.imgView = imgView;
        text = new Text(" " + this.health + "\n " + this.attack);
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
    }

    public ImageView getImgView() {
        return imgView;
    }

    public void increaseHealth(int health) {
        this.health += health;
    }

    public void decreaseHealth(int damage) {
        health -= damage;
    }

    public boolean isAlive(){
        return health > 1;
    }

    public void increaseAttack(int attack) {
        this.attack += attack;
    }

    public void decreaseAttack(int attack) {
        this.attack -= attack;
    }

    public void attack(Animal animalAttacks) {
        animalAttacks.decreaseHealth(attack);
    }
}
