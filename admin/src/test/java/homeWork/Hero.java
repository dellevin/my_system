package homeWork;

public class Hero {
    private int level;
    private String name;
    private int hp;
    private int money;

    public Hero(int level, String name, int hp, int money) {
        this.level = level;
        this.name = name;
        this.hp = hp;
        this.money = money;
    }

    public void show() {
        System.out.print(name+"的级别为: " + level + ", 血量: " + hp + ", 金币: " + money);
    }

    // Getters and setters
    public int getLevel() { return level; }
    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMoney() { return money; }

    public void setLevel(int level) { this.level = level; }
    public void setName(String name) { this.name = name; }
    public void setHp(int hp) { this.hp = hp; }
    public void setMoney(int money) { this.money = money; }
}

