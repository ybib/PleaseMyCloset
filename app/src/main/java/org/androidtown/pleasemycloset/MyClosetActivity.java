package org.androidtown.pleasemycloset;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

public class MyClosetActivity extends AppCompatActivity {
    public static final int MY_CLOSET = 1000;
    public static final int FRIEND_CLOSET = 1001;
    public static final String FLAG = "closet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_closet);

        // 진입 때 자신의 것인지, 친구의 것인지 확인.
        Intent intent = getIntent();

        int _flag = intent.getExtras().getInt(FLAG);

        Button btnCamerOn = findViewById(R.id.cameraOn);

        switch (_flag)
        {
            case MY_CLOSET: break;
            case FRIEND_CLOSET: btnCamerOn.setVisibility(View.GONE); break;
            default: break;
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout) ;

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition() ;
                changeView(pos) ;
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btnCamerOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camIntent = new Intent(MyClosetActivity.this, CamActivity.class);
                MyClosetActivity.this.startActivity(camIntent);
            }
        });


    }

    private void changeView(int index) {
        TextView textView1 = (TextView) findViewById(R.id.text1) ;
        TextView textView2 = (TextView) findViewById(R.id.text2) ;
        TextView textView3 = (TextView) findViewById(R.id.text3) ;

        switch (index) {
            case 0 :
                textView1.setVisibility(View.VISIBLE) ;
                textView2.setVisibility(View.INVISIBLE) ;
                textView3.setVisibility(View.INVISIBLE) ;
                break ;
            case 1 :
                textView1.setVisibility(View.INVISIBLE) ;
                textView2.setVisibility(View.VISIBLE) ;
                textView3.setVisibility(View.INVISIBLE) ;
                break ;
            case 2 :
                textView1.setVisibility(View.INVISIBLE) ;
                textView2.setVisibility(View.INVISIBLE) ;
                textView3.setVisibility(View.VISIBLE) ;
                break ;

        }
    }

}
