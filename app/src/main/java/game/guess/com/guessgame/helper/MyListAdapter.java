package game.guess.com.guessgame.helper;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import game.guess.com.guessgame.R;

/**
 * Created by Abhishek on 10/5/2016.
 */
public class MyListAdapter extends BaseAdapter {
    Context c;
    LayoutInflater layoutInflater;
    ArrayList<Model> arrayList;
public MyListAdapter(Context c,ArrayList<Model> arraylist)
{this.c=c;
    layoutInflater=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
this.arrayList=arraylist;
}
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh=new ViewHolder();
        Model m=arrayList.get(position);
        convertView=layoutInflater.inflate(R.layout.listadapterrow,parent,false);
        vh.iv=(ImageView)convertView.findViewById(R.id.listiv);
        vh.tv=(TextView)convertView.findViewById(R.id.listtext);
        vh.iv.setImageResource(m.getListIcon());
        vh.tv.setText(m.getListText());
        return convertView;
    }
    class ViewHolder{
        ImageView iv;
        TextView tv;
    }
}
