package game.guess.com.guessgame;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

import game.guess.com.guessgame.helper.Settings;

public class SettingsActivity extends Activity implements View.OnClickListener {
TextView t1,t2,t3;
Settings settings;
    long time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        t1=(TextView)findViewById(R.id.tv1);
        t2=(TextView)findViewById(R.id.tv2);
        t3=(TextView)findViewById(R.id.tv3);
        settings=new Settings(SettingsActivity.this);
        t1.setOnClickListener(this);
        t2.setOnClickListener(this);
        t3.setOnClickListener(this);
        HashMap<String,Long> hasmap=settings.getTime();
        time= hasmap.get("TIME");

        if(time==60500)
        {
            t1.setBackgroundColor(Color.GRAY);
            t2.setBackgroundColor(Color.WHITE);
            t3.setBackgroundColor(Color.WHITE);

        }
        else if(time==90500)
        {
            t2.setBackgroundColor(Color.GRAY);
            t1.setBackgroundColor(Color.WHITE);
            t3.setBackgroundColor(Color.WHITE);

        }
        else if(time==120500)
        {t2.setBackgroundColor(Color.WHITE);
            t1.setBackgroundColor(Color.WHITE);
            t3.setBackgroundColor(Color.GRAY);

        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.tv1)
        {
            t1.setBackgroundColor(Color.GRAY);
            t2.setBackgroundColor(Color.WHITE);
            t3.setBackgroundColor(Color.WHITE);
            settings.setTime(60500);
        }
       else if(v.getId()==R.id.tv2)
        {
            t2.setBackgroundColor(Color.GRAY);
            t1.setBackgroundColor(Color.WHITE);
            t3.setBackgroundColor(Color.WHITE);
            settings.setTime(90500);
        }
       else if(v.getId()==R.id.tv3)
        {t2.setBackgroundColor(Color.WHITE);
            t1.setBackgroundColor(Color.WHITE);
            t3.setBackgroundColor(Color.GRAY);
            settings.setTime(120500);
        }
    }
}
