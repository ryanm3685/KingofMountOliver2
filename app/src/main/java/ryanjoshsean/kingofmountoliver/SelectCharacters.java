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
    Button nextButton, prevButton;
    EditText playerName;
    int [] imageResources; //the id for each character image
    int imageResourceIndex; //index in array for the above id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_characters);

        initializeImageResources();

        howManyPlayers = getIntent().getIntExtra("how_many", 2);
        imagesArray = new int[howManyPlayers];
        namesArray = new String[howManyPlayers];
        aiArray = new boolean[howManyPlayers];
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
                    //set images here

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

    void initializeImageResources()
    {
        imageResources = new int[6];

        imageResources[0] = R.drawable.sebak230;
        imageResources[1] = R.drawable.fredrogers1;
        imageResources[2] = R.drawable.steelymcbeam;
        imageResources[3] = R.drawable.markmadden;
        imageResources[4] = R.drawable.jeffreed;
        imageResources[5] = R.drawable.weeknd;

        imageResourceID = imageResources[0];
        imageResourceIndex = 0;
    }

    public void changeChar(boolean isNext)
    {
        if (isNext) //next in list
        {
            if (imageResourceIndex < imageResources.length - 1)
            {
                imageResourceIndex++;
            }
            else
            {
                imageResourceIndex = 0;
            }
        }
        else //previous
        {
            if (imageResourceIndex > 0)
            {
                imageResourceIndex--;
            }
            else
            {
                imageResourceIndex = imageResources.length - 1;
            }
        }

        imageResourceID = imageResources[imageResourceIndex];
        characterImage.setImageResource(imageResourceID);
    }

}
