package game.guess.com.guessgame.helper;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import game.guess.com.guessgame.R;

/**
 * Created by Abhishek on 10/7/2016.
 */
public class FragmentOne extends Fragment {
    RecyclerView deckRecycler;
    DeckAdapter adapter;
GridLayoutManager gridLayoutManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View v= inflater.inflate(R.layout.customlayout,container,false);
        deckRecycler=(RecyclerView)v.findViewById(R.id.deckrecycler);
        deckRecycler.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(getActivity(),2);
        deckRecycler.setLayoutManager(gridLayoutManager);
        ArrayList<Data_Model> arrayList=new ArrayList<>();
        adapter=new DeckAdapter(getActivity(),arrayList);

        deckRecycler.setAdapter(adapter);
       // ((DeckAdapter) adapter).addItem(obj, index);
        return v;
    }



}
