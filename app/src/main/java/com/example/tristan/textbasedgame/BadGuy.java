package com.example.tristan.textbasedgame;

/**
 * Created by Tristan on 7/05/2017.
 */

public class BadGuy {

    int health;  //How much health the bad guy will have
    String type;  //Which type of bad guy they will be
    int damageMult;  //The damage multiplier that they will have
    String name;  //The name of the bad guy
    String[] names = {"Bob", "Rob","Bert", "Terry","Jerry","Margaret",
            "Joe", "Moe"};  //Array of possible names

    /**
     * The constructor method for a bad guy
     * @param i The number this bad guy is in the playing order
     */
    public BadGuy(int i){
        int j = (int)(Math.random()*names.length);  //Random number to decide the name
        name = names[j];
        if(i <= 4){
            //To make sure players get to try out each enemy once, the first four turns will have one of each bad guy
            leSwitch(i);
        }else if(i > 4 && i < 10){
            //Otherwise it'll just be a random bad guy
            i = (int)(Math.random()*4 + 1);
            leSwitch(i);
        }else{
            //On the last turn (10th) they will have to fight the boss; the minotaur
            minotaur();
        }
    }

    /**
     * A switch to decide which beast constructor is called
     * @param j The number for the switch
     */
    public void leSwitch(int j){
        switch(j){
            case 1:
                goblin();
                break;
            case 2:
                orc();
                break;
            case 3:
                troll();
                break;
            case 4:
                ogre();
                break;
        }
    }

    /**
     * Creates the bad guy as a goblin
     */
    public void goblin(){
        type = "Goblin";
        health = 100;
        damageMult = 10;
    }

    /**
     * Creates the bad guy as an orc
     */
    public void orc(){
        type = "Orc";
        health = 200;
        damageMult = 20;
    }

    /**
     * Creates the bad guy as a troll
     */
    public void troll(){
        type = "Troll";
        health = 300;
        damageMult = 40;
    }

    /**
     * Creates the bad guy as an orc
     */
    public void ogre(){
        type = "Ogre";
        health = 500;
        damageMult = 50;
    }

    /**
     * Creates the final boss, the minotaur
     */
    public void minotaur(){
        name = "Steve";
        type = "Minotaur";
        health = 2000;
        damageMult = 100;
    }
}
