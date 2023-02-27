import java.io.FileNotFoundException;

public class Stegosaurus extends Animal {

    public Stegosaurus() throws FileNotFoundException {
        super(3, 3, 0, "C:\\Users\\louis\\GitHub\\superheroautodino\\src\\stego.png");
    }

    public void decreaseHealth(int health) {
        this.health -= (health - 1);
    }
}
