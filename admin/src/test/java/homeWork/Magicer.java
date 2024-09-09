package homeWork;

public class Magicer extends Hero implements Attackable {
    private int mp;

    public Magicer(int level, String name, int hp, int money, int mp) {
        super(level, name, hp, money);
        this.mp = mp;
    }

    @Override
    public void attack(Hero target) {
        System.out.println(getName() + "朝" + target.getName()+"发起进攻，对"+target.getName()+"造成"+100+"伤害");
        target.setHp(target.getHp()-100);
    }

    // Getter and setter for mp
    public int getMp() { return mp; }
    public void setMp(int mp) { this.mp = mp; }

    @Override
    public void show() {
        super.show();
        System.out.println("， 蓝量: " + mp);
    }
}
