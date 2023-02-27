import java.io.FileNotFoundException;

public class Pterodactyl extends Animal {

    public Pterodactyl() throws FileNotFoundException {
        super(2, 2, 1, "C:\\Users\\louis\\GitHub\\superheroautodino\\src\\pte.png");
    }

    public void attack(Animal animalAttacks) {
        animalAttacks.decreaseHealth(attack * 2);
    }
}
