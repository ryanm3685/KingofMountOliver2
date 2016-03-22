package ryanjoshsean.kingofmountoliver;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class StatsActivity extends ActionBarActivity {
    TextView statsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String stats = getIntent().getStringExtra("stats");

        statsTextView = (TextView)findViewById(R.id.statsTextView);
        statsTextView.setText(stats);
    }

}
