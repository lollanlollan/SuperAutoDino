import java.io.FileNotFoundException;

public class Tyrannosaurus extends Animal {

    public Tyrannosaurus() throws FileNotFoundException {
        super(1, 3, 2, "C:\\Users\\louis\\GitHub\\superheroautodino\\src\\trex.png");
    }

    public void attack(Animal animalAttacks) {
        increaseAttack(2);
        animalAttacks.decreaseHealth(attack);
    }
}
