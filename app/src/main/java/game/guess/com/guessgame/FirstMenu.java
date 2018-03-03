package game.guess.com.guessgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FirstMenu extends Activity implements View.OnClickListener {
    LongShadowTextView logo;
    ImageView quickplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_background);
         logo = (LongShadowTextView) findViewById(R.id.logo);
        quickplay=(ImageView)findViewById(R.id.quickplay);
        quickplay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.quickplay)
        {
            Intent i=new Intent(FirstMenu.this,SegmentsList.class);
            startActivity(i);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
