public class Challenger extends Character {
    protected int maxHealth;

    public Challenger(String name, int health, int attackPower, int armor) {
        super(name, health, attackPower, armor);
        this.maxHealth = this.health;
    }

    @Override
    public void attack(Character you) {
        if (you.armor > 0)
        {
            you.armor -= this.attackPower;
            System.out.println(this.name + " attacks " + you.name + " for " + this.attackPower + " damage\n");
            if(you.armor <= 0)
            {
                you.armor = 0;
                System.out.println(you.name + " armor is destroyed");
            }
        }
        else
        {
            you.health -= this.attackPower;
            System.out.println(this.name + " attacks " + you.name + " for " + this.attackPower + " damage\n");
        }
    }

    @Override
    public void heal(){
        this.health += 18;
        if(this.health > this.maxHealth)
        {
            this.health = this.maxHealth;
        }
        System.out.println(this.name + " heals for 15 health");
    }


    public void specialAttack(Character you) {
        System.out.println(this.name + " uses a special attack");
    }
}
