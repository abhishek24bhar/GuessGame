package game.guess.com.guessgame;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import game.guess.com.guessgame.helper.SensorFusion;

public class AccessGyroscope extends Activity
{public String DEBUG_TAG="Result";
    private Handler mHandler ;
 int flag=0,i;
   ArrayList<Integer> storedvalue;
 boolean result;
    int index=0;static int num=0;
    private Random randomGenerator;
    CounterClass2 timer2;
    CounterClass timer;
    private TextView tv,tv2,tv3;
    static int count=0;

    BroadcastReceiver receiver;
    Intent serviceIntent;

    LinearLayout llayout;
    //the Sensor Manager
    private SensorManager sManager;
ArrayList<String> category;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_access_gyroscope);

         llayout=(LinearLayout)findViewById(R.id.linearlayout);
        Intent intent=getIntent();
        category=intent.getStringArrayListExtra("Key");
        storedvalue=new ArrayList<Integer>();
        randomGenerator = new Random();
         num = randomGenerator.nextInt(category.size());
        //get the TextView from the layout file
        tv = (TextView) findViewById(R.id.tv);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
      tv2.setVisibility(View.GONE);
        //get a hook to the sensor service
       // sManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                int sensorval = intent.getIntExtra("sensorval",0);
                Log.e("sensorval","" +sensorval);
             //  if(result==false)

     if(sensorval==1) {
    //up condition
         num = randomGenerator.nextInt(category.size());

         //index++;
         Log.e("indexval", "pass val " + num);
    tv3.setText("" + sensorval);
    tv2.setVisibility(View.VISIBLE);
    llayout.setBackgroundColor(Color.RED);
    tv2.setText("PASS");
      result=true;
   /* Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
        @Override
        public void run() {
            llayout.setBackgroundResource(R.drawable.circles);
            tv2.setText(category.get(num));
        }
    }, 1000);*/

}
               else if(sensorval==2)
                {result=true;
                    num = randomGenerator.nextInt(category.size());
                    Log.e("indexval", "success val " + num);
                    tv3.setText("" + sensorval);
                    tv2.setVisibility(View.VISIBLE);
                    llayout.setBackgroundColor(Color.GREEN);
                    tv2.setText("SUCCESS");

                  /*  Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            llayout.setBackgroundResource(R.drawable.circles);

                            tv2.setText(category.get(num));
                        }
                    }, 1000);*/

                    //down
                }
                else if(sensorval==0)
                {result=false;
                    tv3.setText(""+sensorval);
                    Log.e("indexvalin else", "" + index);
                   // num = randomGenerator.nextInt(category.size());
                            llayout.setBackgroundResource(R.drawable.circles);
                           tv2.setText(category.get(num));
                        }



            }
        };
        llayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag++;
                Toast.makeText(getApplicationContext(),""+flag,Toast.LENGTH_SHORT).show();
                if (flag == 1) {
                    timer = new CounterClass(5000, 1000);

                    timer.start();
                    tv2.setVisibility(View.VISIBLE);
                } else {
                    num = randomGenerator.nextInt(category.size());
                    tv2.setVisibility(View.VISIBLE);
                    llayout.setBackgroundColor(Color.RED);
                    tv2.setText("PASS");
                    index++;
                    Log.e("indexval","on touch "+index);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            llayout.setBackgroundResource(R.drawable.circles);

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
    }
    //when this Activity starts
    @Override
    protected void onResume()
    {
        super.onResume();

         }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e("on Back press","presed");
        flag=0;
        timer2.cancel();
        if(serviceIntent!=null){
        stopService(serviceIntent);
            try {
                unregisterReceiver(receiver);
            }
            catch (Exception e){

            }
           }
        Intent i=new Intent(getApplicationContext(),SegmentsList.class
        );

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
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
llayout.setEnabled(false);
            long millis = millisUntilFinished;
            long hms =TimeUnit.MILLISECONDS.toSeconds(millis) ;
           // System.out.println(hms);
            tv.setText(""+hms);

        }
        @Override
        public void onFinish() {
            // TODO Auto-generated method stub
            llayout.setEnabled(true);
            tv.setText("Go");

             timer2 = new CounterClass2(60000, 1000);

            timer2.start();

           serviceIntent = new Intent(getApplicationContext(),
                    SensorFusion.class);
            startService(serviceIntent);
            registerReceiver(receiver, new IntentFilter(
                    SensorFusion.BROADCAST_ACTION));
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
                    llayout.setBackgroundResource(R.drawable.circles);
                     Intent myintent=new Intent(AccessGyroscope.this,FinalResult.class);
                    startActivity(myintent);
                }
            }, 2000);
            // ... Respond to touch events
        }


    }
}