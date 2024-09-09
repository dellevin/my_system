package homeWork;

public class Warrior extends Hero implements Counterattackable {
    private int energy;

    public Warrior(int level, String name, int hp, int money, int energy) {
        super(level, name, hp, money);
        this.energy = energy;
    }

    @Override
    public void counterattack(Hero attacker) {
        System.out.println(getName() + "反击了" + attacker.getName()+"的进攻，并对"+ attacker.getName()+"造成了"+50+"点伤害");
        attacker.setHp(attacker.getHp()-50);
    }

    // Getter and setter for energy
    public int getEnergy() { return energy; }
    public void setEnergy(int energy) { this.energy = energy; }

    @Override
    public void show() {
        super.show();
        System.out.println("， 能量: " + energy);
    }
}
