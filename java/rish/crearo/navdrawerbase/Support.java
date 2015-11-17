package rish.crearo.navdrawerbase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Support extends AppCompatActivity {

    @Bind(R.id.support_back)
    ImageView back;

    @Bind(R.id.support_seek)
    SeekBar seekBar;

    @Bind(R.id.support_seek_text)
    TextView seekText;

    @Bind(R.id.support_buy_btn)
    Button buyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        ButterKnife.bind(this);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                switch (i) {
                    case 0:
                        seekText.setText("Buy me a coffee ~ $1");
                        break;
                    case 1:
                        seekText.setText("Buy me a lemonade ~ $2");
                        break;
                    case 2:
                        seekText.setText("Buy me a sports drink ~ $5");
                        break;
                    case 3:
                        seekText.setText("Buy me a beer ~ $10");
                        break;
                    case 4:
                        seekText.setText("Buy me a new Android Phone! ~ $100");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
