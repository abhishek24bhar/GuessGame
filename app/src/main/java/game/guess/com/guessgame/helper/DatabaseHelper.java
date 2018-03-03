package game.guess.com.guessgame.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Abhishek on 10/7/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static  final String DBNAME="CHARADES";
    public static final String TABLENAME="RECORDS";
    Context context;
    SQLiteDatabase db;
    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;
        String query="create table "+TABLENAME+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,Name varchar(100) UNIQUE,Category varchar(30))";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
              onCreate(db);
    }
    public void insert(String category,String name)
    {
        db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("Name",name);
        cv.put("Category",category);
        db.insert(TABLENAME,null,cv);
    }
    public Cursor retrieve(String category)
    {db=this.getReadableDatabase();
      Cursor cursor=  db.rawQuery("Select Name from " + TABLENAME + " where Category='" + category + "'", null);
        return cursor;
    }

}
