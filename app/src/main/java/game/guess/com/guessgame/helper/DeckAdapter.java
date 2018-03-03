package game.guess.com.guessgame.helper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import game.guess.com.guessgame.R;

/**
 * Created by Abhishek on 10/10/2016.
 */
public class DeckAdapter extends RecyclerView.Adapter<MyViewHolder> implements View.OnClickListener {
    Context c;
    ArrayList<Data_Model> arrayList;
    public DeckAdapter(Context c,ArrayList<Data_Model> arrayList)
    {
        this.c=c;
        this.arrayList=arrayList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.deck_row, parent, false);

        MyViewHolder dataObjectHolder = new MyViewHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
holder.iv.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public void addItem(Data_Model dataObj, int index) {
        arrayList.add(index, dataObj);
        notifyItemInserted(index);
    }

    @Override
    public void onClick(View v) {

    }
}

