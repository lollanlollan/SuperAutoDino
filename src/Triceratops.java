import java.io.FileNotFoundException;

public class Triceratops extends Animal {

    public Triceratops() throws FileNotFoundException {
        super(2, 2, 3, "C:\\Users\\louis\\GitHub\\superheroautodino\\src\\triceratops.png");
    }

    public void attack(Animal animalAttacks) {
        animalAttacks.decreaseAttack(1);
        animalAttacks.decreaseHealth(attack);
    }
}

