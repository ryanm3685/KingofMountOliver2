package ryanjoshsean.kingofmountoliver;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class GameActivity extends ActionBarActivity {
    boolean [] shouldReroll;
    TheGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        String[] playerNames = getIntent().getStringArrayExtra("names");
        boolean[] aiArray = getIntent().getBooleanArrayExtra("AI");
        //start the game logic here
        game = new TheGame(playerNames, aiArray);

        /*ImageView imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setVisibility();*/
//        DiceSides.diceSides.ATTACK;
    }

    int getDiceImageResource(Dice dice)
    {

    }


    private class RollListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

        }
    }

    private class

}
