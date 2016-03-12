package ryanjoshsean.kingofmountoliver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;

public class HowManyPlayers extends ActionBarActivity {
    NumberPicker picker;
    ImageButton howManyOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_many_players);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        picker = (NumberPicker)findViewById(R.id.numberPicker);
        picker.setMaxValue(6);
        picker.setMinValue(2);

        howManyOK = (ImageButton)findViewById(R.id.howManyOK);

        howManyOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectCharacters.class);
                Bundle bundle = new Bundle();
                int howMany = picker.getValue();
                bundle.putInt("how_many", howMany);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}
