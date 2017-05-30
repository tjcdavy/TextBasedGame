package com.example.tristan.textbasedgame;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Tristan on 7/05/2017.
 */

public class Game extends AppCompatActivity{

    enum Finished {GOING, WIN, LOSS};

    int health = 1000;
    String name;
    Finished gameGoing;
    String userInput;
    String[] weapons = {null, "Butter Knife", "Dagger", "Dual Daggers", "Sword", "Broadsword", "Dual Swords", "Katana"};
    String[] spells = {"Nothing", "Fireball", "Icebrick", "Windgust", "Blue Flame", "Arctic Cold", "Tornado wind", "Minotaur's Pain"};
    int currentWeapon = 1;
    int currentSpell = 0;
    BadGuy[] enemy;
    boolean inTheGame = false;

    Button attackWeapon, attackMagic, attackHands;
    Button handsPunch, handsSlap;
    Button slapPimp, slapBitch;
    Button runAway;
    Button runAwayYes, runAwayNo;

    TextView noSpell;

    LinearLayout runAwayLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        gameGoing = Finished.GOING;
        enemy = new BadGuy[10];

        attackWeapon = (Button) findViewById(R.id.attack_weapon);
        attackHands = (Button) findViewById(R.id.attack_hands);
        attackMagic = (Button) findViewById(R.id.attack_magic);

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

    public void start(int pos){

        enemy[pos] = new BadGuy(pos);

    }

    public void updateGame(){

    }

}
