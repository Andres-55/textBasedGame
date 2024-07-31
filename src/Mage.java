class Mage extends Challenger {
    public Mage(String name, int health, int attackPower, int armor)
    {
        super(name, health, attackPower, armor);
    }

    @Override
    public void specialAttack(Character you) {
        int damage = this.attackPower - 5;
        you.armor -= damage;
        you.health -= damage;
        System.out.println(this.name + " uses magic blade and deals " + damage + " damage to your armor and health");
        if(you.armor < 0)
        {
            you.armor = 0;
        }
    }
}