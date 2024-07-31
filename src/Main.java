import java.util.Random;
import java.util.Scanner;

public class Main {

    private static Player player;
    private static Random random = new Random();
    private static Scanner input = new Scanner(System.in);


    private static final String[] areas = {"Forest", "Village", "Desert", "Mountains"};
    private static final String[] spells = {"Rain of Fire", "Great Heal" , "Magic Downpour", "Lightning Spear"};

    //spawns an enemy depending on area
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

    //gives the player a spell or choose if they want to change their spells if it was dropped after defeating the enemy
    private static void itemDrop(int spell, String[] spellList, String[] spells)
    {
        String[] info = {" which does extra damage to armor but less damage to health", 
                         " which heals you for 30 health",
                         " which does less damage but affects armor and health at the same time",
                         " which does extra damage to health but less damage to armor"};
        String choice2;
        //no spells
        if(spellList[0] == null)
        {
            System.out.println("You have learned a new spell called " + spells[spell] + info[spell] + "\n");
            spellList[0] = spells[spell];
            Functions.pause(2000);
        }

        //one spell
        else if(spellList[1] == null)
        {
            if(spellList[0].equals(spells[spell]))
            {
                return;
            }

            System.out.println("You have learned a new spell called " + spells[spell] + info[spell] + "\n");
            spellList[1] = spells[spell];
            Functions.pause(2000);
        }

        //two spells
        else
        {
            if(spellList[0].equals(spells[spell]) || spellList[1].equals(spells[spell]))
            {
                return;
            }

            System.out.println("You have learned a new spell called " + spells[spell] + info[spell]);
            System.out.println("However you can only have 2 spells at a time. You currently have " + spellList[0] + " and " + spellList[1] + "\nWould you like to switch one of your current spells?");
            Functions.pause(1500);

            do
            {
                System.out.println("\nType \n1 to switch out your first spell, \n2 to switch out your second spell, \n3 to keep your current spells");
                choice2 = input.nextLine();
                System.out.println();
            }while(!choice2.equals( "1") && !choice2.equals( "2") && !choice2.equals( "3"));

            switch(choice2){
                case("1"):
                    spellList[0] = spells[spell];
                    System.out.println("Your first spell was changed to " + spellList[0] + "\n");
                    break;

                case("2"):
                    spellList[1] = spells[spell];
                    System.out.println("Your second spell was changed to " + spellList[1] + "\n");
                    break;

                default:
                    System.out.println("Your spells were not changed\n");
                    break;
            }
        }
        Functions.pause(1500);
    }

    public static void main(String[] args) {

        String[] spellList = new String[2];
        int counter = 0;
        int enemyChoice;
        int difficulty;
        String currentArea = "";
        String tempArea;

        //intro
        System.out.print("Enter your character's name: ");
        String playerName = input.nextLine();
        System.out.println();

        player = new Player(playerName, 100, 15, 30, 50); 
        System.out.println("You start out with " + player.health + " health, " + player.attackPower + " attack power, " + player.armor + " armor, and " + player.mana + " mana");

        Functions.pause(1700);
        System.out.println("You set out traveling the lands trying to become the strongest warrior.\nDefeat enemies to level up and get stronger.\n");

        Functions.pause(2500);

        String choice;
        String action;
        int itemChance;

        //starts game
        while (player.isAlive()) {
            //gets area
            if((counter % 3) == 0)
            {
                tempArea = currentArea;
                currentArea = areas[random.nextInt(areas.length)];
                if(!tempArea.equals(currentArea))
                {
                    System.out.println("You have entered the " + currentArea);
                }
            }
            counter++;

            difficulty = player.getKillCount() / 3;
            //spawns an enemy
            Challenger enemy = spawnEnemy(currentArea, difficulty);
            System.out.println("You encounter a " + enemy.getName() );
            System.out.println(enemy.getName() + " has " + enemy.armor + " armor and " + enemy.health + " health\n");
            Functions.pause(1700);

            //check if you are still alive
            while (enemy.isAlive() && player.isAlive()) 
            {
                //no spells
                if(spellList[0] == null)
                {
                    //pick an action to perform
                    do
                    {
                        System.out.println("Type the number for what you will do\n1: attack, 2: heal, or 3: restore mana?");
                        action = input.nextLine();
                        System.out.println();

                    }while(!action.equals( "1") && !action.equals( "2") && !action.equals( "3") );
                }

                //1 spell
                else if(spellList[1] == null)
                {
                    //pick an action to perform
                    do
                    {
                        System.out.println("Type the number for what you will do or what spell to use for 10 mana\n1: attack, 2: heal, 3: restore mana, or 4: " + spellList[0] + "?");
                        action = input.nextLine();
                        System.out.println();
                        if(action.equals("4") && player.mana <= 10)
                        {
                            System.out.println("Not enough mana\n");
                            action = "";
                        }

                    }while(!action.equals( "1") && !action.equals( "2") && !action.equals( "3") && !action.equals("4") );
                }

                //2 spells
                else
                {
                    //pick an action to perform
                    do
                    {
                        System.out.println("Type the number for what you will do or what spell to use\n1: attack, 2: heal, 3: restore mana, 4: " + spellList[0] + ", or 5: " + spellList[1] + "?");
                        action = input.nextLine();
                        System.out.println();
                        if((action.equals("4") || action.equals("5")) && player.mana <= 10)
                        {
                            System.out.println("Not enough mana\n");
                            action = "";
                        }
                    }while(!action.equals( "1") && !action.equals( "2") && !action.equals( "3") && !action.equals("4") && !action.equals("5") );
                }

                if(action.equals("4"))
                {
                    action = spellList[0];
                }

                else if(action.equals("5"))
                {
                    action = spellList[1];
                }

                //calls method based on what you picked to do
                switch (action) {
                    case "1":
                        player.attack(enemy);
                        break;

                    case "2":
                        player.heal();
                        break; 

                    case "3":
                        player.restoreMana();
                        break;

                    case "Rain of Fire":
                        player.RoF(enemy);
                        break;

                    case "Great Heal":
                        player.GH();
                        break;

                    case "Magic Downpour":
                        player.MD(enemy);
                        break;
                    case "Lightning Spear":
                        player.LS(enemy);
                        break;

                    default:
                        break;

                }

                Functions.pause(1000);

                //checks if enemy is still alive
                if (enemy.isAlive()) 
                {
                    //shows enemy stats
                    System.out.println(enemy.getName() + " has " + enemy.armor + " armor and " + enemy.health + " health");
                    if(enemy.health < Math.round(enemy.maxHealth * .8))
                    {
                        enemyChoice = random.nextInt(101);
                        if(enemyChoice >= 0 && enemyChoice <= 50)
                        {
                            enemy.attack(player);
                        }
                        else if(enemyChoice > 50 && enemyChoice <= 80)
                        {
                            enemy.heal();
                            System.out.println(enemy.getName() + " now has " + enemy.armor + " armor and " + enemy.health + " health");

                        }
                        else
                        {
                            enemy.specialAttack(player);
                        }
                    }
                    else
                    {
                        enemyChoice = random.nextInt(101);
                        if(enemyChoice >= 0 && enemyChoice <= 80)
                        {
                            enemy.attack(player);
                        }
                        else 
                        {
                            enemy.specialAttack(player);
                        }
                    }
                    //enemy attacks
                    Functions.pause(1600);
                    //shows player stats if they are still alive
                    if(player.isAlive())
                    {
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" + player.getName() + " has " + player.armor + " armor, " + player.mana + " mana and " + player.health + " health");
                    }
                }
            }

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

                //shows your stats after the battle
                System.out.println("After resting you have " + player.attackPower + " attack power, " + player.health + "/" + player.getMaxHealth() + " health, " + player.armor + " armor , and " + player.mana + " mana\n");
                Functions.pause(2000);

                //have a chance to get a spell
                itemChance = random.nextInt(101);
                //has a 10% chance to get new spell and calls method to set it up
                if(itemChance > 0 && itemChance <= 3 )
                {
                    itemDrop(0, spellList, spells);
                }

                else if(itemChance > 30 && itemChance <= 33 )
                {
                    itemDrop(1, spellList, spells);
                }

                else if(itemChance > 60 && itemChance <= 63 )
                {
                    itemDrop(2, spellList, spells);
                }

                else if(itemChance > 90 && itemChance <= 93 )
                {
                    itemDrop(3, spellList, spells);
                }
            } 

            else 
            {
                System.out.println("You have been defeated!");
                break;
            }
        }
        System.out.println("You defeated " + player.getKillCount() + " enemies.");
    }   
}