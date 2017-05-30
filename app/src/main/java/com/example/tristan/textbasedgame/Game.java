package com.example.tristan.textbasedgame;

import android.app.Activity;
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

    TextView noSpell; //The textfield for if the player doesn't have a spell

    LinearLayout runAwayLayout; //The layout containing the confirmation buttons for running away

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

        runAwayLayout = (LinearLayout) findViewById(R.id.run_away_layout);

    }

    public void attack(int attackType){
        int damage; //The damage the player will deal

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

                }else{

                }
        }
    }

    /**
     * Sets all initial variables for a bad guy
     * @param pos the number the bad guy is
     */
    public void start(int pos){

        enemy[pos] = new BadGuy(pos);
        enemyName.setText(enemy[pos].name);
        enemyType.setText(enemy[pos].type);
        enemyHealth.setText(enemy[pos].health);
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

    }



}
