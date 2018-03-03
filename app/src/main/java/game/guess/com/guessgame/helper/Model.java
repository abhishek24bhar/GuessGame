package game.guess.com.guessgame.helper;

/**
 * Created by Abhishek on 10/5/2016.
 */
public class Model {
    String listtext;
    int listicons;
    public  Model(String listtext,int listicons)
    {
        this.listicons=listicons;
        this.listtext=listtext;

    }
    public String getListText()
    {
        return listtext;
    }
    public int getListIcon()
    {
        return  listicons;
    }
}
