package game.guess.com.guessgame;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import game.guess.com.guessgame.helper.DatabaseHelper;

public class MainActivity extends Activity implements View.OnClickListener {
    /*private SensorManager mSensorManager;
    private Sensor mSensor;*/
    DatabaseHelper helper;
    Button insert;
    EditText sendcategory,sendvalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insert=(Button)findViewById(R.id.insert);
        sendcategory=(EditText)findViewById(R.id.sendcategory);
        sendvalue=(EditText)findViewById(R.id.sendvalue);
        helper=new DatabaseHelper(MainActivity.this);
        insert.setOnClickListener(this);
       // mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
       // mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.insert)
        {
            helper.insert(sendcategory.getText().toString(),sendvalue.getText().toString());
            Log.e("inserted","done");
        }
    }
}
