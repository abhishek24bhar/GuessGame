package game.guess.com.guessgame;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
    private ArrayList<Integer> mDataset;
    ArrayList<String> moviesname=new ArrayList<>();
    ArrayList<String> actitout=new ArrayList<>();
Context c;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView icon;


        public ViewHolder(View v) {
            super(v);
            icon = (ImageView) v.findViewById(R.id.icon2);

        }
    }

    /*public void add(int position, int item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }
*/
    /*public void remove(String item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }*/

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyRecyclerAdapter(Context c,ArrayList<Integer> myDataset) {
        mDataset = myDataset;
        this.c=c;
        moviesname.add("Hello Brother");
        moviesname.add("Singh is King");
        moviesname.add("Kuch Kuch Hota Hai");
        moviesname.add("Tum Bin");
        moviesname.add("Gadar");
        moviesname.add("Dabangg");
        moviesname.add("Kick");
        moviesname.add("Hero");
        moviesname.add("Dilwale");
        moviesname.add("Lakshya");
        moviesname.add("Border");
        moviesname.add("Dil Maange More");
        moviesname.add("Lagaan");
        moviesname.add("Chak De India");
        moviesname.add("Mary Kom");
        moviesname.add("Agneepath");
        moviesname.add("Student of the Year");
        moviesname.add("2 States");
        moviesname.add("Housefull3");
        moviesname.add("Bajirao Mastani");
        moviesname.add("Zindagi Na Milegi Dobara");
        moviesname.add("Yeh Jawaani hai Deewani");
        moviesname.add("Bahubali");
        moviesname.add("Padmavat");
        moviesname.add("Chandni Chowk to China");



        actitout.add("mesmerise");
        actitout.add("cooking");
        actitout.add("doing homework");
        actitout.add("playing cricket");
        actitout.add("surfing");
        actitout.add("Beautiful");
        actitout.add("Worried");
        actitout.add("dressing");
        actitout.add("playing Guitar");
        actitout.add("pampering");
        actitout.add("Genious");
        actitout.add("reading book");
        actitout.add("playing Football");
        actitout.add("Scuba Diving");
        actitout.add("Swimming");
        actitout.add("Celebrating Diwali");
        actitout.add("Running");
        actitout.add("Worshipping");
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.icon.setImageResource(mDataset.get(position));
        holder.icon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                if(position==0){
                builder.setMessage("You have to act for any word or phrase")
                        .setCancelable(true)
                        .setPositiveButton("Play", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(c, CheckSensor.class);
                                i.putExtra("Key",actitout);
                                c.startActivity(i);
                                //do things
                            }
                        });}
                else if(position==1){
                    builder.setMessage("You have to act for movies name")
                            .setCancelable(true)
                            .setPositiveButton("Play", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent i = new Intent(c, CheckSensor.class);
                                    i.putExtra("Key",moviesname);
                                    c.startActivity(i);
                                    //do things
                                }
                            });}
                AlertDialog alert = builder.create();
                alert.show();

            }
        });



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}