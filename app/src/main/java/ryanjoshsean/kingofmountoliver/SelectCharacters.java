package ryanjoshsean.kingofmountoliver;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SelectCharacters extends ActionBarActivity {

    int howManyPlayers;
    int [] imagesArray;
    String [] namesArray;
    boolean [] aiArray;
    ImageView characterImage;
    int imageResourceID;
    boolean isRick = true;//TODO get more than rick sebak and fred rogers
    Button nextButton, prevButton;
    EditText playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_characters);

        howManyPlayers = getIntent().getIntExtra("how_many", 2);
        imagesArray = new int[howManyPlayers];
        namesArray = new String[howManyPlayers];
        characterImage = (ImageView)findViewById(R.id.selectCharacterImage);
        playerName = (EditText)findViewById(R.id.editTextPlayerName);

        nextButton = (Button)findViewById(R.id.nextCharButton);
        prevButton = (Button)findViewById(R.id.prevCharButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeChar(true);
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeChar(false);
            }
        });

        characterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //until we support multiple people players
                imagesArray[0] = imageResourceID;
                namesArray[0] = playerName.getText().toString();
                aiArray[0] = false;
                for (int i = 1; i < howManyPlayers; i++)
                {
                    if (isRick)
                    {
                        imagesArray[i] = R.drawable.fredrogers1;
                    }
                    else
                    {
                        imagesArray[i] = R.drawable.sebak230;
                    }

                    namesArray[i] = String.format("Player %s", Integer.toString(i));
                    aiArray[i] = true;
                }

                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArray("names", namesArray);
                bundle.putBooleanArray("AI", aiArray);
                bundle.putIntArray("images", imagesArray);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    public void changeChar(boolean isNext)
    {
        //very temporary, lol
        if (isRick)
        {
            imageResourceID = R.drawable.fredrogers1;

        }
        else
        {
            imageResourceID = R.drawable.sebak230;
        }

        characterImage.setImageResource(imageResourceID);
        isRick = !isRick;
    }

}
