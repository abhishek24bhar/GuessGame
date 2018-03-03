package game.guess.com.guessgame.helper;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import game.guess.com.guessgame.R;

/**
 * Created by Abhishek on 10/10/2016.
 */
public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView iv;
    public MyViewHolder(View itemView) {
        super(itemView);
        iv=(ImageView)itemView.findViewById(R.id.addtodeck);

    }

    @Override
    public void onClick(View v) {

    }

}
