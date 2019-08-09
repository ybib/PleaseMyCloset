package org.androidtown.pleasemycloset;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 파이어베이스 유저 데이터 세팅.
        MyUserData.getInstance().InitUserData();

        findViewById(R.id.btnSeeMyCloset).setOnClickListener(this);
        findViewById(R.id.btnSeeMyFriend).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        // Activity 생성하면 작업해야함.
        switch(v.getId())
        {
            case R.id.btnSeeMyCloset:
                intent = new Intent(MainActivity.this, MyClosetActivity.class);
                intent.putExtra(MyClosetActivity.FLAG, MyClosetActivity.MY_CLOSET);
                break;
            case R.id.btnSeeMyFriend:
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
