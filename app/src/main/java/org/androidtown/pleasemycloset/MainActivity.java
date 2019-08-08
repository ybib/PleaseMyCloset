package org.androidtown.pleasemycloset;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSeeMyCloset = (Button) findViewById(R.id.btnSeeMyCloset);
        Button btnSeeMyFriend = (Button) findViewById(R.id.btnSeeMyFriend);

        btnSeeMyCloset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MainActivity.this, MyClosetActivity.class);
                MainActivity.this.startActivity(mainIntent);
            }
        });
    }
}
