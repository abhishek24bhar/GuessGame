package game.guess.com.guessgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FinalResult extends Activity {
   private TextView correct_ans,wrong_ans,final_score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);
        Intent i=getIntent();
      int passCount=  i.getIntExtra("Passes", 0);
      int successCount = i.getIntExtra("Success",0);
        correct_ans=(TextView)findViewById(R.id.correct_ans);
        wrong_ans=(TextView)findViewById(R.id.wrong_ans);
        final_score=(TextView)findViewById(R.id.final_score);
        correct_ans.setText(""+successCount);
        wrong_ans.setText(""+passCount);
        int final_Mscore=successCount-passCount;
        final_score.setText(""+final_Mscore);
    }
public void onClick(View view)
{
    if(view.getId()==R.id.play_again){
        Intent i=new Intent(getApplicationContext(),SegmentsList.class);

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),SegmentsList.class);

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }


}
