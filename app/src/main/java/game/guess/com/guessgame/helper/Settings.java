package game.guess.com.guessgame.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Abhishek on 10/12/2016.
 */
public class Settings  {
    SharedPreferences prefs;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String IS_LOGIN = "ISLOGIN";
    public static final String nname = "NameKey";
    int PRIVATE_MODE = 0;
    Context c;
    long time;
    SharedPreferences.Editor editor;
    public Settings(Context c)
    {
        this.c=c;
        prefs=c.getSharedPreferences(MyPREFERENCES,PRIVATE_MODE);
        editor=prefs.edit();
    }
    public void setTime(long time)
    {
        editor.putLong("TIME",time);
        editor.commit();

    }
    public HashMap<String,Long> getTime()
    {
        HashMap<String,Long> map=new HashMap<String,Long>();
        map.put("TIME",prefs.getLong("TIME",60500));
        return map;
    }
}
