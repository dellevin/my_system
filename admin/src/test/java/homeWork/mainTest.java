package homeWork;

public class mainTest {
    public static void main(String[] args) {
        Magicer magicer = new Magicer(1, "貂蝉", 3000, 300, 100);
        Warrior warrior = new Warrior(1, "吕布", 3500, 300, 100);

        magicer.show();
        warrior.show();

        magicer.attack(warrior);
        warrior.counterattack(magicer);

        magicer.show();
        warrior.show();
    }
}
