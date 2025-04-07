# Text-Based Adventure Game

A Java-based text adventure game where players battle enemies, utilizing attacks, healing, and spells. The objective is to defeat as many enemies as possible before dying.

## Features
- **Turn-Based Combat:** Engage in battles where you can choose to attack, heal, restore mana, or cast spells.​
- **Randomized Encounters:** Face a variety of enemies with different strengths, randomized actions, and special events.​
- **Luck Factor:** Random chances influence strength and attack potency, adding unpredictability to both the user and enemy.​
- **Abstraction Implementation:** Utilizes object-oriented principles for game characters and actions.

## Gameplay Overview
- **Attacking:** Deal damage to enemies using standard attacks.​
- **Healing:** Recover your health during battles.​
- **Casting Spells:** Acquire spells during combat and use them for various effects, such as dealing extra damage or heals.​
- **Enemy Actions:** Enemies have a small chance at unleashing a more powerful attack. They also have a chance to heal if their health is low.
- **Enemy Spawning:** Different locations have certain enemies, each with a special enemy that has a special characteristic.

![image](https://github.com/user-attachments/assets/4e093d5e-063f-4a2c-87ac-91193471c0cb)


## Concepts & Techniques Used
- **Abstraction:** Common behaviors are abstracted into a superclass or interface (like character stats and combat actions).
- **Encapsulation:** Each class controls its own data, exposing only what's necessary through public methods.
-**Inheritance:** Shared functionality between player and enemy types is reused to reduce duplication.
- **Polymorphism:** Method behavior changes depending on the object (e.g., different types of enemies or attacks).

I added a feature to level up a certain attribute every time you defeat an enemy to make the game feel more rewarding
```java
 if (player.isAlive()) 
            {
                player.incrementKillCount();

                System.out.println("You defeated the " + enemy.getName() + " and leveled up");
                //choose what to level up
                do
                {
                    System.out.println("\nType which number for the attribute you will like to level up\n1: health, 2: attack, 3: mana, or 4: armor?");
                    choice = input.nextLine();
                }while(!choice.equals( "1") && !choice.equals( "2") && !choice.equals( "3") && !choice.equals( "4") );

                //calls mehtod based on what you leveled up
                switch (choice) {
                    case "1":
                        player.healthLvlUp(5);
                        player.restoreAfterBattle();
                        System.out.println("Your max health is now " + player.getMaxHealth());
                        break;

                    case "2":
                        player.attackLvlUp(5);
                        player.restoreAfterBattle();
                        System.out.println("You attack power is now " + player.attackPower);
                        break; 

                    case "3":
                        player.manaLvlUp(5);
                        player.restoreAfterBattle();
                        System.out.println("Your mana is now " + player.mana);
                        break;

                    case "4":
                        player.armorLvlUp(5);
                        player.restoreAfterBattle();
                        System.out.println("Your armor is now " + player.armor);
                        break;

                    default:
                        break;

                }
```

To keep the game from being too easy I added a function to increase the difficulty depending on how many enemies you have killed
```java
private static Challenger spawnEnemy(String area, int difficulty) 
    {
        int type = random.nextInt(101);
        int rand = random.nextInt(3);

        //randomly chooses between an easy or difficult enemy
        if(type > 0 && type <= 65)
        {
            switch (rand){
                case 0:
                    return new Goblin("Goblin", 60 + (difficulty * 5), 10 + (difficulty * 5), 10 + (difficulty * 5));
                case 1:
                    return new Mage("Mage", 65 + (difficulty * 5), 12 + (difficulty * 5), 15 + (difficulty * 5));
                case 2:
                    return new Bandit("Bandit", 65 + (difficulty * 5), 15 + (difficulty * 5), 20 + (difficulty * 5));
                default:
                    return new Goblin("Goblin", 60 + (difficulty * 5), 10 + (difficulty * 5), 10 + (difficulty * 5));
            }
        }
        else
        {
            switch (area) {
                case "Forest":
                    return new GiantSpider("Giant Spider", 90 + (difficulty * 5), 16 + (difficulty * 5), 10 + (difficulty * 5));
                case "Village":
                    return new Dragon("Dragon", 100 + (difficulty * 5), 18 + (difficulty * 5), 20 + (difficulty * 5));
                case "Desert":
                    return new GiantScorpion("Giant Scorpion", 80 + (difficulty * 5), 16 + (difficulty * 5), 20 + (difficulty * 5));
                case "Mountains":
                    return new Golem("Golem", 25 + (difficulty * 5), 17 + (difficulty * 5), 70 + (difficulty * 5));
                default:
                    return new Goblin("Goblin", 60 + (difficulty * 5), 10 + (difficulty * 5), 10 + (difficulty * 5));
            }
        }
    }
```

---

This project was a great way for me to apply concepts like abstraction, encapsulation, inheritance, and polymorphism in a fun, hands-on way. Designing the enemy system, balancing difficulty, and implementing upgrade mechanics helped reinforce my understanding of object-oriented programming and game logic. I’m proud of how the game evolved, and I look forward to continuing to build on projects like this as I grow as a developer.

