package ryanjoshsean.kingofmountoliver;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class GameActivity extends ActionBarActivity {
    Button rollButton, finishRollButton;
    ImageButton helpButton, statsButton;
    boolean [] shouldReroll;
    TheGame game;
    RollListener rollListener;
    HelpListener helpListener;
    StatsListener statsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        shouldReroll = new boolean[6];
        for (int i = 0; i < shouldReroll.length; i++) shouldReroll[i] = true;
        String[] playerNames = getIntent().getStringArrayExtra("names");
        boolean[] aiArray = getIntent().getBooleanArrayExtra("AI");
        //start the game logic here
        game = new TheGame(playerNames, aiArray);

        rollButton = (Button)findViewById(R.id.rollButton);

        rollListener = new RollListener();
        rollButton.setOnClickListener(rollListener);

        finishRollButton = (Button)findViewById(R.id.finishRollButton);

        helpButton = (ImageButton)findViewById(R.id.gameHelpButton);
        helpListener = new HelpListener();
        helpButton.setOnClickListener(helpListener);

        statsButton = (ImageButton)findViewById(R.id.statsButton);
        statsListener = new StatsListener();
        statsButton.setOnClickListener(statsListener);
    }

    int getDiceImageResource(Dice dice)
    {
        int returnValue = R.drawable.energy;
        if (dice.value == EnumClass.diceSides.ENERGY) returnValue = R.drawable.energy;
        else if (dice.value == EnumClass.diceSides.ATTACK) returnValue = R.drawable.attack;
        else if (dice.value == EnumClass.diceSides.MOJO) returnValue = R.drawable.mojo;
        else if (dice.value == EnumClass.diceSides.ONE) returnValue = R.drawable.one;
        else if (dice.value == EnumClass.diceSides.TWO) returnValue = R.drawable.two;
        else if (dice.value == EnumClass.diceSides.THREE) returnValue = R.drawable.three;

        return returnValue;
    }




    public void setDiceImages()
    {
        Dice [] theDice = game.getDiceArray();
        ImageView dice1, dice2, dice3, dice4, dice5, dice6;
        dice1 = (ImageView)findViewById(R.id.diceImage1);
        dice2 = (ImageView)findViewById(R.id.diceImage2);
        dice3 = (ImageView)findViewById(R.id.diceImage3);
        dice4 = (ImageView)findViewById(R.id.diceImage4);
        dice5 = (ImageView)findViewById(R.id.diceImage5);
        dice6 = (ImageView)findViewById(R.id.diceImage6);

        dice1.setImageResource(getDiceImageResource(theDice[0]));
        dice2.setImageResource(getDiceImageResource(theDice[1]));
        dice3.setImageResource(getDiceImageResource(theDice[2]));
        dice4.setImageResource(getDiceImageResource(theDice[3]));
        dice5.setImageResource(getDiceImageResource(theDice[4]));
        dice6.setImageResource(getDiceImageResource(theDice[5]));

    }

    private class RollListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            //roll all dice that should be rolled
            game.rollDice(shouldReroll);
            //determine correct images for dice
            setDiceImages();
        }
    }

    private class StatsListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

        }
    }

    private class HelpListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent helpIntent = new Intent(getApplicationContext(), HelpActivity.class);
            startActivity(helpIntent);
        }
    }
}
