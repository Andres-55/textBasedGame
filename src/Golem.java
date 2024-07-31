class Golem extends Challenger {
    public Golem(String name, int health, int attackPower, int armor) {
        super(name, health, attackPower, armor);
    }

    @Override
    public void specialAttack(Character you) {
        int damage = this.attackPower + 10;
        if (you.armor > 0)
        {
            you.armor -= damage;
            System.out.println(this.name + " uses earth shatter on " + you.name + " for " + damage + " damage\n");
            if(you.armor <= 0)
            {
                you.armor = 0;
                System.out.println(you.name + " armor is destroyed");
            }
        }
        else
        {
            you.health -= damage;
            System.out.println(this.name + " uses earth shatter on " + you.name + " for " + damage + " damage\n");
        }
    }
}
