package game.guess.com.guessgame;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import game.guess.com.guessgame.helper.MyService;
import game.guess.com.guessgame.helper.Settings;

public class CheckSensor extends Activity //implements SensorEventListener
{Settings settings;
    int flag=0,i;
ArrayList<String> map=new ArrayList<String>();
    ArrayList<Integer> storedvalue,storedvalue2;
    boolean result;
    int passcount=0,successcount=0;
    int index=0;static int num=0;
    private Random randomGenerator;
    CounterClass2 timer2;
    HashMap<String,Integer> map2=new HashMap<>();
    CounterClass timer;
    int sum=0;
    private TextView tv,tv2,tv3;
    static int count=0;

    BroadcastReceiver receiver;
    Intent serviceIntent;
     //the Sensor Manager
    private SensorManager sManager;
    ArrayList<String> category;
    LinearLayout linearLayout2;
    SensorManager sensorManager=null;
    Sensor mSensor=null;
    long time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_sensor);
        randomGenerator = new Random();
        tv = (TextView) findViewById(R.id.xvalue);
        tv2 = (TextView) findViewById(R.id.yvalue);
        tv3 = (TextView) findViewById(R.id.zvalue);
        settings=new Settings(CheckSensor.this);
        tv2.setVisibility(View.GONE);
        tv3.setVisibility(View.GONE);
        storedvalue=new ArrayList<Integer>();
        storedvalue2=new ArrayList<Integer>();
        linearLayout2=(LinearLayout)findViewById(R.id.linearlayout2);
        Intent intent=getIntent();
        category=intent.getStringArrayListExtra("Key");
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
HashMap<String,Long> hasmap=settings.getTime();
        time= hasmap.get("TIME");

        num = randomGenerator.nextInt(category.size());
//sensorManager.registerListener(this,mSensor,SensorManager.SENSOR_DELAY_FASTEST);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String sensorval = intent.getStringExtra("sensorval");
                Log.e("sensorval","" +sensorval);
                if(sensorval.contentEquals("PASS")) {
                    //up condition
                   // passcount++;
                       count=1;
                    num = randomGenerator.nextInt(category.size());
                    Log.e("indexval", "pass val " + num);
                    tv3.setText("" + sensorval);
                    tv2.setVisibility(View.VISIBLE);
                    linearLayout2.setBackgroundColor(Color.RED);
                    tv2.setText("PASS");
                    result=true;
                   // Log.e("Passcount",""+passcount);

     }

                else if(sensorval.contentEquals("SUCCESS"))
                {   result=true;
                    num = randomGenerator.nextInt(category.size());
                    Log.e("indexval", "success val " + num);
                    tv3.setText("" + sensorval);
                    tv2.setVisibility(View.VISIBLE);
                    linearLayout2.setBackgroundColor(Color.GREEN);
                    tv2.setText("SUCCESS");

                }
                else
                {   result=false;

                    tv3.setText("" + sensorval);
                    Log.e("indexvalin else", "" + index);
                    // num = randomGenerator.nextInt(category.size());
                    linearLayout2.setBackgroundResource(R.drawable.circles);
                    if (tv2.getText().toString().equalsIgnoreCase("PASS"))
                      passcount++;
                   else if (tv2.getText().toString().equalsIgnoreCase("SUCCESS"))
                        successcount++;
                    Log.i("Counting of Success ", "" + successcount);
                    Log.i("Counting of Pass ", "" + passcount);
                    tv2.setText(category.get(num));
                    if(map2.containsKey(category.get(num))){
                    map2.put(category.get(num),1);
                }
                    else{
                    map2.put(category.get(num),1);
                }



                }
                Log.e("Always","execute");
            }
        };

            linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag++;
                Toast.makeText(getApplicationContext(), "" + flag, Toast.LENGTH_SHORT).show();
                if (flag == 1) {
                    timer = new CounterClass(5500, 1000);

                    timer.start();
                    tv2.setVisibility(View.VISIBLE);
                } else {
                    num = randomGenerator.nextInt(category.size());
                    tv2.setVisibility(View.VISIBLE);
                    linearLayout2.setBackgroundColor(Color.RED);
                    tv2.setText("PASS");
                    index++;
                    Log.e("indexval","on touch "+index);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            linearLayout2.setBackgroundResource(R.drawable.circles);

                            tv2.setText(category.get(num));
                        }
                    }, 1000);
                  }
            }
            });
            }

   @Override
   protected void onPause() {
       super.onPause();
       Log.e("on pause","called");
        }
    @Override
    protected void onStop() {
        if(serviceIntent!=null) {
            stopService(serviceIntent);
            flag=0;
            try {
                unregisterReceiver(receiver);
            }
            catch (Exception e){

            }
            finally {
                timer2.cancel();
            }
         }

        super.onStop();
    }
    @Override
    protected void onDestroy() {

      //  sensorManager.unregisterListener(this);

        super.onDestroy();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e("on Back press","presed");
        flag=0;
        if(timer2!=null) {
            timer2.cancel();
            if (serviceIntent != null) {
                stopService(serviceIntent);
                try {
                    unregisterReceiver(receiver);
                } catch (Exception e) {

                }
            }
            Intent i = new Intent(getApplicationContext(), SegmentsList.class
            );

            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
    }
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
        }

        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
            linearLayout2.setEnabled(false);
            long millis = millisUntilFinished;
            long hms = TimeUnit.MILLISECONDS.toSeconds(millis) ;
            // System.out.println(hms);
            tv.setText(""+hms);

        }
        @Override
        public void onFinish() {
            // TODO Auto-generated method stub
            linearLayout2.setEnabled(true);
            tv.setText("Go");

            timer2 = new CounterClass2(time, 1000);

            timer2.start();

            serviceIntent = new Intent(getApplicationContext(),
                    MyService.class);
            startService(serviceIntent);
            registerReceiver(receiver, new IntentFilter(
                    MyService.BROADCAST_ACTION));
            Log.e("Register", "receiver regist");
            // ... Respond to touch events
        }


    }
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    class CounterClass2 extends CountDownTimer {

        public CounterClass2(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
        }

        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub

            long millis = millisUntilFinished;
            long hms =TimeUnit.MILLISECONDS.toSeconds(millis) ;
            // System.out.println(hms);
            tv.setText(""+hms);
        }
        @Override
        public void onFinish() {
            // TODO Auto-generated method stub

            tv2.setText("FINISH");

            if(serviceIntent!=null)
                stopService(serviceIntent);
            try {
                unregisterReceiver(receiver);
            }
            catch (Exception e){

            }
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    linearLayout2.setBackgroundResource(R.drawable.circles);
                    Intent myintent=new Intent(CheckSensor.this,FinalResult.class);
                    myintent.putExtra("Passes",passcount);
                    myintent.putExtra("Success",successcount);
                   // Log.e("Passcount2",""+passcount);
                  Log.e("passnum",""+map2);
                  //  myintent.putExtra("PassNum",storedvalue2.size());
                    startActivity(myintent);
                }
            }, 2000);
            // ... Respond to touch events
        }
       }
}
