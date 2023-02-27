import java.io.FileNotFoundException;

public class Velociraptor extends Animal {

    public Velociraptor() throws FileNotFoundException {
        super(2, 2, 3, "C:\\Users\\louis\\GitHub\\superheroautodino\\src\\velociraptor.png");
    }

    public void increaseAttack(int attack) {
        this.attack += attack*2;
    }
}

