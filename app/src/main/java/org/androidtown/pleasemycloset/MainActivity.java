package org.androidtown.pleasemycloset;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.mycloset).setOnClickListener(this);
        findViewById(R.id.friendlist).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        // Activity 생성하면 작업해야함.
        switch(v.getId())
        {
            case R.id.mycloset:
                //intent = new Intent(MainActivity.class);
                break;
            case R.id.friendlist:
                intent = new Intent(MainActivity.this, Friendlist.class);
                break;
            default:
                String tmpStr = "Wrong ID : " + v.getId();
                Toast.makeText(getApplicationContext(),tmpStr,Toast.LENGTH_SHORT);
                break;
        }

        startActivity(intent);
    }
}
