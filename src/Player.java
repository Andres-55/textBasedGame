class Player extends Character {
    protected int mana;
    private int killCount;
    private int maxHealth;
    private int maxArmor;
    private int maxMana;

    public Player(String name, int health, int attackPower, int armor, int mana) {
        super(name, health, attackPower, armor);
        this.mana = mana;
        this.killCount = 0;
        this.maxHealth = health;
        this.maxArmor = armor;
        this.maxMana = mana;
    }

    public void levelUp(String choice) {
        switch (choice.toLowerCase()) {
            case "health":
                this.health += 10;
                break;
            case "attack":
                this.attackPower += 2;
                break;
            case "armor":
                this.armor += 1;
                break;
            case "mana":
                this.mana += 10;
                break;
        }
    }

    @Override
    public void heal()
    {
        this.health += 20;
        if(this.health > this.maxHealth)
        {
            this.health = this.maxHealth;
        }
        System.out.println("You have restored some of your health. You have " + this.health + "/" + this.maxHealth + " health.\n");
    }

    public void restoreMana()
    {
        this.mana += 15;
        if(this.mana > maxMana)
        {
            this.mana = this.maxMana;
        }
        System.out.println("You have restored some of your mana. You have " + this.mana + "/" + this.maxMana + " mana.\n");

    }
    @Override
    public void attack(Character enemy) {
        if (enemy.armor > 0)
        {
            enemy.armor -= this.attackPower;
            System.out.println(this.name + " attacks " + enemy.name + " for " + this.attackPower + " damage\n");
            if(enemy.armor <= 0)
            {
                enemy.armor = 0;
                System.out.println(enemy.name + " armor is destroyed");
            }
        }
        else
        {
            enemy.health -= this.attackPower;
            System.out.println(this.name + " attacks " + enemy.name + " for " + this.attackPower + " damage\n");
        }
    }

    public void RoF(Character enemy)
    {
      int damage = this.attackPower - 10;
        if (enemy.armor > 0)
        {
            enemy.armor -= (damage + 25);
            System.out.println(this.name + " attacks " + enemy.name + " for " + (damage + 25) + " damage\n");
            if(enemy.armor <= 0)
            {
                enemy.armor = 0;
                System.out.println(enemy.name + " armor is destroyed");
            }
        }
        else
        {
            enemy.health -= damage;
            System.out.println(this.name + " attacks " + enemy.name + " for " + damage + " damage\n");
        }
        this.mana -= 10;
    }

    public void GH()
    {
        int x = 30;
        this.health += x;
        if(this.health > this.maxHealth)
        {
            this.health = this.maxHealth;
        }
      System.out.println("You have healed for " + x + " health.");
      this.mana -= 10;
    }

    public void MD(Character enemy)
    {
      int damage = this.attackPower - 10;

        enemy.armor -= damage;
        enemy.health -= damage;
        System.out.println(this.name + " attacks " + enemy.name + "'s armor and health for " + damage + " damage\n");
        if(enemy.armor <= 0)
        {
            enemy.armor = 0;
            System.out.println(enemy.name + " armor is destroyed");
        }

        this.mana -= 10;
    }

    public void LS(Character enemy)
    {
      int damage = this.attackPower - 10;
        if (enemy.armor > 0)
        {
            enemy.armor -= damage;
            System.out.println(this.name + " attacks " + enemy.name + " for " + damage + " damage\n");
            if(enemy.armor <= 0)
            {
                enemy.armor = 0;
                System.out.println(enemy.name + " armor is destroyed");
            }
        }
        else
        {
            enemy.health -= (damage + 25);
            System.out.println(this.name + " attacks " + enemy.name + " for " + (damage + 25) + " damage\n");
        }
        this.mana -= 10;
    }

    public void restoreAfterBattle() {
        this.mana = maxMana;
        this.health += Math.round(maxHealth * 0.2);
        if(this.health > maxHealth)
        {
            this.health = maxHealth;
        }
        this.armor = maxArmor;
    }

    public int getKillCount() {
        return killCount;
    }

    public void incrementKillCount() {
        killCount++;
    }

    public void healthLvlUp(int HLvl)
    {
        this.maxHealth += HLvl;
    }

    public void armorLvlUp(int ALvl)
    {
        this.maxArmor += ALvl;
    }

    public void manaLvlUp(int MLvl)
    {
        this.maxMana += MLvl;
    }

    public void attackLvlUp(int ALvl)
    {
        this.attackPower += ALvl;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}
