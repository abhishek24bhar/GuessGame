package game.guess.com.guessgame.helper;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service implements SensorEventListener {
    private SensorManager sManager;
    private static final String TAG=MyService.class.getName();
    BroadcastReceiver broadcaster;
    Intent intent;
    static final public String BROADCAST_ACTION = "com.abhishek.broadcast";

    @Override
    public void onCreate() {
        super.onCreate();
        sManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        intent = new Intent(BROADCAST_ACTION);
        sManager.registerListener(this, sManager.getDefaultSensor(Sensor.TYPE_GRAVITY),SensorManager.SENSOR_DELAY_FASTEST);


    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }



    @Override
    public synchronized int onStartCommand(Intent intent, int flags, int startId) {


        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE)
        {
            return;
        }
        float[] values = event.values;
        //sendResult(""+"x value is="+event.values[0]+"\n"+" Y value is="+event.values[1]+"\n"+" Z value is= "+event.values[2]);
        //if(values[2]<-5.60)
        if(values[2]<-5.60)
        {
            Log.e(TAG,"SUCCESS value is"+ values[2]);
            sendResult("SUCCESS");

              // tv2.setText("SUCCESS");
        }
        //else if(values[2]>5.20)
        else if(values[2]>5.20)
        {  Log.e(TAG,"PASS value is"+ values[2]);
            sendResult("PASS");

            //tv2.setText("PASS");
              }
        else{
            sendResult("");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.e(TAG,"Accuracy is:- "+accuracy);
    }
    @Override
    public synchronized void onDestroy() {
        super.onDestroy();

        sManager.unregisterListener(this);

    }


    public void sendResult(String message) {
        intent.putExtra("sensorval", message);

        sendBroadcast(intent);
    }

}
