package game.guess.com.guessgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import game.guess.com.guessgame.helper.Model;
import game.guess.com.guessgame.helper.MyListAdapter;

public class SegmentsList extends Activity {
RecyclerView recyclerview;
    MyRecyclerAdapter adapter;
    MyListAdapter adapter2;
    ListView lv;
    Model model;
    LayoutInflater inflator;
    Integer[] imageIds={R.drawable.actitoutcombine,R.drawable.combinemovie,R.drawable.animalking,R.drawable.combinesports,R.drawable.wood};
    String[] listtext={"Home","All","Custom","Settings"};
    Integer[] listicons={R.drawable.home,R.drawable.copy,R.mipmap.ic_launcher,R.drawable.ball};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segments_list);
        recyclerview=(RecyclerView)findViewById(R.id.my_recycler_view);
        lv=(ListView)findViewById(R.id.lv);
        recyclerview.setHasFixedSize(true);
         inflator=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        recyclerview.setLayoutManager(new GridLayoutManager(this, 2));
        ArrayList<Model> arrayList=new ArrayList<>();

        for(int k=0;k<listtext.length;k++) {
             model = new Model(listtext[k], listicons[k]);
                arrayList.add(model);
        }
           adapter2=new MyListAdapter(SegmentsList.this,arrayList);
        lv.setAdapter(adapter2);
            ArrayList<Integer> al = new ArrayList<>();
            for (int i = 0; i < imageIds.length; i++)
                al.add(imageIds[i]);
            adapter = new MyRecyclerAdapter(SegmentsList.this,al);
            recyclerview.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==2)
                {Intent i=new Intent(SegmentsList.this,MainActivity.class);
                    startActivity(i);

                   /* FragmentOne fragmentt = new FragmentOne();
                    android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame1,fragmentt);
                    fragmentTransaction.commit();*/
                }
                if(position==3)
                {Intent i=new Intent(SegmentsList.this,SettingsActivity.class);
                    startActivity(i);

                   /* FragmentOne fragmentt = new FragmentOne();
                    android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame1,fragmentt);
                    fragmentTransaction.commit();*/
                }

            }
        });


        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),FirstMenu.class
        );

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
