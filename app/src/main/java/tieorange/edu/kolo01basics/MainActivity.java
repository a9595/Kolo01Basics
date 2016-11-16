package tieorange.edu.kolo01basics;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button previous;
    private Button next;
    private TextView text;
    private EditText editText;

    private List<String> insultsList = new ArrayList<>();
    private CheckBox slideShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();

        initInsults();

        initListeners();

        setRandomPicture();

    }

    private void setRandomPicture() {
        // picasso:
        String url = "http://loremflickr.com/320/240";

        Picasso.with(this).invalidate(url);

        Picasso.with(this)
                .load(url)
                .into(imageView);
    }

    private void setRandomInsult() {
        int randomIndex = getRandomIndex();
        String insult = insultsList.get(randomIndex);
        text.setText(insult);
    }

    private int getRandomIndex() {
        Random random = new Random();
        int randomIndex = random.nextInt(insultsList.size());
        return randomIndex;
    }

    private void initInsults() {
        // insults:
        insultsList.add("Łzy Chucka Norrisa leczą raka. Ale jest tak hardkorowy, że nigdy nie zapłakał.");
        insultsList.add("Chuck Norris nie śpi. Czeka.");
        insultsList.add("Głównym towarem eksportowym Chucka Norrisa jest ból.\n");
        insultsList.add(" Chuck Norris doliczył do nieskończoności. Dwa razy.\n");
    }

    private void initListeners() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable = getDrawable(R.mipmap.ic_launcher);
                imageView.setImageDrawable(drawable);

                String personName = editText.getText().toString();
                Toast.makeText(MainActivity.this, "Hello " + personName, Toast.LENGTH_LONG).show();
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable = getDrawable(R.mipmap.ic_launcher_round);
                imageView.setImageDrawable(drawable);
            }
        });

        slideShow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    mTimer.run();
                } else {
                    handler.removeCallbacks(mTimer);
                }
            }
        });
    }

    private void initViews() {
        // Initialize views:
        imageView = (ImageView) findViewById(R.id.image);
        previous = (Button) findViewById(R.id.previous);
        next = (Button) findViewById(R.id.next);
        text = (TextView) findViewById(R.id.text);
        editText = (EditText) findViewById(R.id.name);
        slideShow = (CheckBox) findViewById(R.id.slideShow);
    }

    Handler handler = new Handler();

    Runnable mTimer = new Runnable() {
        @Override
        public void run() {
            setRandomPicture();
            setRandomInsult();
            handler.postDelayed(mTimer, 2000);
        }
    };
}
