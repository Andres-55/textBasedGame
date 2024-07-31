abstract class Character {
    protected String name;
    protected int health;
    protected int attackPower;
    protected int armor;

    public Character(String name, int health, int attackPower, int armor) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.armor = armor;
    }

    public abstract void attack(Character opponent);

    public abstract void heal();

    public boolean isAlive() {
        return this.health > 0;
    }

    public String getName() {
        return name;
    }
}
