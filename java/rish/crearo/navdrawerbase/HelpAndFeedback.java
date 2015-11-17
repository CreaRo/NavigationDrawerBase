package rish.crearo.navdrawerbase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HelpAndFeedback extends AppCompatActivity {

    @Bind(R.id.haf_tool_bar)
    Toolbar toolbar;

    @Bind(R.id.haf_main_layout)
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_and_feedback);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }
}
