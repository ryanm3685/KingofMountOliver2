package ryanjoshsean.kingofmountoliver;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Iterator;

public class GameActivity extends ActionBarActivity {
    Button rollButton, finishRollButton;
    ImageButton helpButton, statsButton;
    boolean [] shouldReroll;
    int[] imagesArray;
    TheGame game;
    RollListener rollListener;
    HelpListener helpListener;
    Context activityContext;
    //StatsListener statsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        activityContext = this;
        shouldReroll = new boolean[6];
        for (int i = 0; i < shouldReroll.length; i++) shouldReroll[i] = true;
        String[] playerNames = getIntent().getStringArrayExtra("names");
        boolean[] aiArray = getIntent().getBooleanArrayExtra("AI");
        imagesArray = getIntent().getIntArrayExtra("images");
        setPlayerImages();
        //start the game logic here
        game = new TheGame(playerNames, aiArray, imagesArray);
        rollButton = (Button)findViewById(R.id.rollButton);

        rollListener = new RollListener();
        rollButton.setOnClickListener(rollListener);

        finishRollButton = (Button)findViewById(R.id.finishRollButton);

        helpButton = (ImageButton)findViewById(R.id.gameHelpButton);
        helpListener = new HelpListener();
        helpButton.setOnClickListener(helpListener);

//        statsButton = (ImageButton)findViewById(R.id.statsButton);
//        statsListener = new StatsListener();
//        statsButton.setOnClickListener(statsListener);

        printCurrentPlayerRoll();
        printPlayerStatuses();
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

    void printPlayerStatuses()
    {
        TextView text1, text2, text3, text4, text5, text6;
        text1 = (TextView)findViewById(R.id.player1Text);
        text2 = (TextView)findViewById(R.id.player2Text);
        text3 = (TextView)findViewById(R.id.player3Text);
        text4 = (TextView)findViewById(R.id.player4Text);
        text5 = (TextView)findViewById(R.id.player5Text);
        text6 = (TextView)findViewById(R.id.player6Text);

        text1.setText(getStatsStringForPlayer(game.getPlayers().get(0)));
        text2.setText(getStatsStringForPlayer(game.getPlayers().get(1)));

        if (game.getPlayers().size() >= 3) text3.setText(getStatsStringForPlayer(game.getPlayers().get(2)));
        else text3.setVisibility(View.INVISIBLE);

        if (game.getPlayers().size() >= 4) text4.setText(getStatsStringForPlayer(game.getPlayers().get(3)));
        else text4.setVisibility(View.INVISIBLE);

        if (game.getPlayers().size() >= 5) text5.setText(getStatsStringForPlayer(game.getPlayers().get(4)));
        else text5.setVisibility(View.INVISIBLE);

        if (game.getPlayers().size() >= 6) text6.setText(getStatsStringForPlayer(game.getPlayers().get(5)));
        else text6.setVisibility(View.INVISIBLE);
    }


    public void setPlayerImages()
    {
        ImageView image1, image2, image3, image4, image5, image6;

        image1 = (ImageView)findViewById(R.id.imageView);
        image2 = (ImageView)findViewById(R.id.imageView2);
        image3 = (ImageView)findViewById(R.id.imageView3);
        image4 = (ImageView)findViewById(R.id.imageView4);
        image5 = (ImageView)findViewById(R.id.imageView5);
        image6 = (ImageView)findViewById(R.id.imageView6);

        image1.setImageResource(imagesArray[0]);
        image2.setImageResource(imagesArray[1]);

        if (imagesArray.length >= 3) image3.setImageResource(imagesArray[2]);
        else image3.setVisibility(View.INVISIBLE);

        if (imagesArray.length >= 4) image4.setImageResource(imagesArray[3]);
        else image4.setVisibility(View.INVISIBLE);

        if (imagesArray.length >= 5) image5.setImageResource(imagesArray[4]);
        else image5.setVisibility(View.INVISIBLE);

        if (imagesArray.length >= 6) image6.setImageResource(imagesArray[5]);
        else image6.setVisibility(View.INVISIBLE);
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

    public String getStatsStringForPlayer(Player player)
    {
        return String.format("%s\nMojo:\t%s\nJuice:\t%s\nEnergy:\t%s\n", player.getName(), player.getMojo(), player.getJuice(), player.getEnergy());
    }

    public String getStatsForAllPlayers()
    {
        StringBuilder sb = new StringBuilder();
        for (Player p : game.getPlayers())
        {
            sb.append(getStatsStringForPlayer(p));
        }

        return sb.toString();
    }

    public void printCurrentPlayerRoll()
    {
        StringBuilder sb = new StringBuilder();
        String playerName = game.currentPlayer.getName();
        EnumClass.rollStatus rollStatus = game.getCurrentRollStatus();
        String rollString = rollStatus.toString();
        String brownsville, south18th;
        if (null == game.brownsvillePlayer) brownsville = "No one";
        else  brownsville = game.brownsvillePlayer.getName();
        if (null == game.south18thPlayer) south18th = "No one";
        else  south18th = game.south18thPlayer.getName();

        sb.append(playerName)
            .append(" ")
            .append(rollString)
            .append("\n")
            .append(String.format("%s is on Brownsville Road\n", brownsville))
            .append(String.format("%s is on South 18th Street\n", south18th));
        String toastString = sb.toString();

        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), toastString, duration);
        toast.show();
    }


    private class RollListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            //roll all dice that should be rolled
            boolean turnOver = game.rollDice(shouldReroll);

            if (turnOver)
            {
                if (null != game.getLastAttackerOfCenter())
                {
                    //create alert to get out of center, if you were attacked
                    if (null != game.brownsvillePlayer)
                    {
                        showAlert(game.brownsvillePlayer.getName(), "Brownsville Road");
                    }

                    if (null != game.south18thPlayer)
                    {
                        showAlert(game.south18thPlayer.getName(), "South 18th Street");
                    }
                }

                //finish turn
                game.finishTurn();
            }
            //determine correct images for dice
            setDiceImages();
            printCurrentPlayerRoll();
            printPlayerStatuses();
        }

        void showAlert(String whichPlayer, final String location)
        {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activityContext);
            alertDialogBuilder.setMessage(String.format("%s:  do you want to leave %s?", whichPlayer
                                                                                            ,location));

            alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    if (location.equals("Brownsville Road")) game.leaveBrownsville();
                    else game.leaveSouth18th();
                }
            });

            alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

    }

//    private class StatsListener implements View.OnClickListener {
//
//        @Override
//        public void onClick(View view) {
//            String stats = getStatsForAllPlayers();
//            Intent statsIntent = new Intent(getApplicationContext(), StatsActivity.class);
//            statsIntent.putExtra("stats", stats);
//            startActivity(statsIntent);
//        }
//    }

    private class HelpListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent helpIntent = new Intent(getApplicationContext(), HelpActivity.class);
            startActivity(helpIntent);
        }
    }
}
