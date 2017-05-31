package com.example.tristan.textbasedgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Tristan on 7/05/2017.
 */

public class Game extends AppCompatActivity{

    enum Finished {GOING, WIN, LOSS};

    int health; //The health of the player
    String name; //The player's name
    Finished gameGoing; //The current state of the game
    final String[] weapons = {null, "Butter Knife", "Dagger", "Dual Daggers", "Sword", "Broadsword", "Dual Swords", "Katana"}; //Weapon names
    final String[] spells = {"Nothing", "Fireball", "Icebrick", "Windgust", "Blue Flame", "Arctic Cold", "Tornado wind", "Minotaur's Pain"}; //Spell Names
    int currentWeapon; //The index of the array for the current weapon
    int currentSpell; //The index of the array for the current spell
    BadGuy[] enemy; //Array for the different enemies
    boolean inTheGame = false;
    int currentBadGuy; //Index of the game, used in conjunction with enemy[]

    TextView playerName, enemyName; //Name fields
    TextView enemyType; //Enemy type fields
    TextView playerHealth, enemyHealth; //Health fields
    TextView playerWeapon, enemyWeapon; //Weapon fields
    TextView playerMagic; //Player magic

    Button attackWeapon, attackMagic, attackHands; //Buttons for different types of attacks
    Button handsPunch, handsSlap; //Buttons for the different types of hand attacks
    Button slapPimp, slapBitch; //Buttons for the different types of slaps
    Button runAway; //Button to choose to run away
    Button runAwayYes, runAwayNo; //Buttons for confirming or denying running away

    TextView noSpell, minotaurSpell; //Textfields for spell casting errors

    LinearLayout runAwayLayout; //The layout containing the confirmation buttons for running away

    TextView bonusBox, bonusBox2, bonusMore; //The textfields explaining the bonus box

    Button bonusYes, bonusNo, bonusContinue; //Buttons for accepting or denying the bonus box

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        name = getIntent().getStringExtra("name");
        gameGoing = Finished.GOING;
        enemy = new BadGuy[10];
        currentWeapon = 1;
        currentSpell = 0;
        health = 1000;
        currentBadGuy = 1;

        //Setting according to views
        playerName = (TextView) findViewById(R.id.player_name);
        playerName.setText(name);
        enemyName = (TextView) findViewById(R.id.badguy_name);

        enemyType = (TextView) findViewById(R.id.badguy_type);

        playerHealth = (TextView) findViewById(R.id.player_health);
        enemyHealth = (TextView) findViewById(R.id.badguy_health);

        playerWeapon = (TextView) findViewById(R.id.player_weapon);
        enemyWeapon = (TextView) findViewById(R.id.badguy_weapon);

        playerMagic = (TextView) findViewById(R.id.player_magic);

        attackWeapon = (Button) findViewById(R.id.attack_weapon);
        attackHands = (Button) findViewById(R.id.attack_hands);
        attackMagic = (Button) findViewById(R.id.attack_magic);
        attackWeapon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attack(1);
            }
        });
        attackHands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attack(2);
            }
        });
        attackMagic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attack(3);
            }
        });

        handsPunch = (Button) findViewById(R.id.hands_punch);
        handsSlap = (Button) findViewById(R.id.hands_slap);

        slapPimp = (Button) findViewById(R.id.slap_pimp);
        slapBitch = (Button) findViewById(R.id.slap_bitch);

        runAway = (Button) findViewById(R.id.run_away);
        runAwayYes = (Button) findViewById(R.id.run_away_yes);
        runAwayNo = (Button) findViewById(R.id.run_away_no);

        noSpell = (TextView) findViewById(R.id.no_spells);
        minotaurSpell = (TextView) findViewById(R.id.minotaur_spell);

        runAwayLayout = (LinearLayout) findViewById(R.id.run_away_layout);

        runAway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attack(4);
            }
        });

        bonusBox = (TextView) findViewById(R.id.box_text);
        bonusBox2 = (TextView) findViewById(R.id.box_text2);
        bonusMore = (TextView) findViewById(R.id.bonus_after_text);

        bonusYes = (Button) findViewById(R.id.prize_yes);
        bonusNo = (Button) findViewById(R.id.prize_no);
        bonusContinue = (Button) findViewById(R.id.bonus_after_button);

        start(currentBadGuy-1); //Starts with the first bad guy

    }

    public void attack(int attackType){
        int damage; //The damage the player will deal
        hideAppropriate();

        switch(attackType){
            case 1:
                //Sword
                if(currentWeapon <= 3){
                    damage = ((int)(Math.random()*5+1))*(currentWeapon*10);
                }else if(currentWeapon > 3 && currentWeapon >= 6){
                    damage = ((int)(Math.random()*10+1))*(currentWeapon*10);
                }else{
                    damage = ((int)(Math.random()*10+6))*(currentWeapon*10);
                }
                updateGame(damage);
                break;
            case 2:
                //Hands

                //For a punch
                handsPunch.setVisibility(View.VISIBLE);
                handsPunch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int damage = ((int)(Math.random()*40+1)*10);
                        updateGame(damage);
                    }
                });

                //For a slap
                handsSlap.setVisibility(View.VISIBLE);
                handsSlap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handsPunch.setVisibility(View.GONE);

                        //Bitch slap
                        slapBitch.setVisibility(View.VISIBLE);
                        slapBitch.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int damage = ((int)(Math.random()*30+1))*10;
                                if(enemy[currentBadGuy -1].type.equals("Troll")){
                                    damage = damage * 2;
                                }
                                updateGame(damage);
                            }
                        });

                        //Pimp slap
                        slapPimp.setVisibility(View.VISIBLE);
                        slapPimp.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int damage = ((int)(Math.random()*50+1))*10;
                                if(enemy[currentBadGuy -1].type.equals("Minotaur")){
                                    damage = damage * 2;
                                }
                                updateGame(damage);
                            }
                        });
                    }
                });
                break;
            case 3:
                //Magic
                if(currentSpell==0){
                    //Don't have a spell
                    noSpell.setVisibility(View.VISIBLE);
                }else if(currentSpell == 7 && currentBadGuy != 10){
                    //The player has Minotaur's Pain but is not facing a Minotaur
                    minotaurSpell.setVisibility(View.VISIBLE);
                }else{
                    //Hit with Magic
                    if(currentSpell == 1 || currentSpell == 2 || currentSpell == 3){
                        //First tier spells
                        damage = ((int)(Math.random()*30+1))*10;
                    }else if(currentSpell == 4 || currentSpell == 5 || currentSpell == 6){
                        //Second tier spells
                        damage = ((int)(Math.random()*30+21))*10;
                    }else{
                        damage = ((int)(Math.random()*30+41)*10);
                    }
                    updateGame(damage);
                }
                break;
            case 4:
                //Run away button
                runAwayLayout.setVisibility(View.VISIBLE);

                runAwayYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        endGame();
                    }
                });
                runAwayNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideAppropriate();
                    }
                });
                break;
        }
    }

    /**
     * Sets all initial variables for a bad guy
     * @param pos the number the bad guy is
     */
    public void start(int pos){

        enemy[pos] = new BadGuy(pos+1);
        enemyName.setText(enemy[pos].name);
        enemyType.setText(enemy[pos].type);
        enemyHealth.setText("" + enemy[pos].health);
        String weapon;
        if(enemy[pos].type.equals("Ogre")){
            weapon = "Club";
        }
        else if(enemy[pos].type.equals("Minotaur")){
            weapon = "Broadsword";
        }
        else{
            weapon = "Hands";
        }
        enemyWeapon.setText(weapon);
    }

    /**
     * Called after each turn to update the states and variables of everything
     */
    public void updateGame(int damage){
        hideAppropriate();
        String attackSpiel = "Your attack dealt " + damage + " damage.";
        enemy[currentBadGuy-1].health -= damage;
        String enemySpiel;

        if(enemy[currentBadGuy-1].health > 0){
            int enemyDamage = ((int)(Math.random()*10+1))*enemy[currentBadGuy -1].damageMult;
            health -= enemyDamage;
            enemySpiel = "The enemy dealt " + enemyDamage + " damage";
            if(health > 0){
                playerHealth.setText(health);
                enemySpiel += ".";
            }else{
                gameGoing = Finished.LOSS;
                enemySpiel += " and killed you.";
            }
        }else{
            enemySpiel = "You defeated them, congratulations.";
        }
        
    }

    public void hideAppropriate(){
        //Removing everything that shouldn't be there at the start of a turn.
        handsPunch.setVisibility(View.GONE);
        handsSlap.setVisibility(View.GONE);
        slapBitch.setVisibility(View.GONE);
        slapPimp.setVisibility(View.GONE);
        noSpell.setVisibility(View.GONE);
        minotaurSpell.setVisibility(View.GONE);
        runAwayLayout.setVisibility(View.GONE);

    }

    public void endBadGuy(){

    }

    public void bonusThing(int turn){
        String output = "As you walk past " + enemy[currentBadGuy-1].name + ", You see a box, ";
        String output2;
        switch (turn){
            case 1:
                //Generates a weapons box
                output += "it is a weapons box.";
                output2 = "Would you like to open it and take the weapon inside? (Bear in mind this will replace your current weapon)";
                bonusYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int turn;
                        turn = (int)(Math.random()*7+1);
                        String text;
                        if(turn == 3 || turn == 6){
                            text = "You have replaced your " + weapons[currentWeapon] + " with " + weapons[turn];
                        }else{
                            text = "You have replaced your " + weapons[currentWeapon] + " with a " + weapons[turn];
                        }
                        bonusMore.setText(text);
                        currentWeapon = turn;
                        switchBonus();
                    }
                });
                String nope = "So you decide to stick with your trusty " + weapons[currentWeapon] + ".  Fair Enough, I guess we'll never know if it was better";
                setNoButton(nope);
                break;
            case 2:
                //Generates a spell box
                output += "it is a spell box.";
                output2 = "Would you like to open it and learn the spell inside? (Bear in mind this will replace your current spell)";
                bonusYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int turn = (int)(Math.random()*7+1);
                        String text;
                        if(currentSpell == 0){
                            text = "You have learnt the " + spells[turn] + " spell.";
                        }else{
                            text = "You have replaced your " + spells[currentSpell] + " spell with " + spells[turn];
                        }
                        bonusMore.setText(text);
                        currentSpell = turn;
                        switchBonus();
                    }
                });
                String nopey = "So you decide to stick with your trusty " + spells[currentSpell] + ".  Fair Enough, I guess we'll never know if it was better";
                setNoButton(nopey);
                break;
            case 3:
                //Generates a health box
                output += "it is a health potion box.";
                output2 = "Would you like to drink it? (You can't take it with you to drink later)";
                bonusYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int turn = ((int)(Math.random()*10+1))*100;
                        health += turn;
                        String text = "You have added " + turn + " to your health, to bring it up to " + health;
                        bonusMore.setText(text);
                        switchBonus();
                    }
                });
                String moreNope = "That's pretty brave.  Respect.";
                setNoButton(moreNope);
                break;
            default:
                turn = (int)(Math.random()*3+1);
                bonusThing(turn);
                return;
        }

        bonusBox.setText(output);
        bonusBox2.setText(output2);

    }

    public void setNoButton(final String text){
        bonusNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bonusMore.setText(text);
                switchBonus();
            }
        });
    }

    public void switchBonus(){
        LinearLayout bonusLayout1 = (LinearLayout) findViewById(R.id.prize_layout);
        final LinearLayout bonusLayout2 = (LinearLayout) findViewById(R.id.bonus_secondary);

        bonusLayout1.setVisibility(View.GONE);
        bonusLayout2.setVisibility(View.VISIBLE);

        Button bonusContinue = (Button) findViewById(R.id.bonus_after_button);
        bonusContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bonusLayout2.setVisibility(View.GONE);
            }
        });

    }

    public void endGame(){

    }

}
