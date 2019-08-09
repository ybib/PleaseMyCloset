package org.androidtown.pleasemycloset;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Friendlist extends AppCompatActivity {
    private ListView ListView;
    ValueEventListener listener;
    DatabaseReference DatabaseRef;
    FriendAdapter mMyAdapter = new FriendAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendlist);

        /* 위젯과 멤버변수 참조 획득 */
        ListView = (ListView)findViewById(R.id.listView);
        collectUserFriendlist();
        mMyAdapter.SetActivity(Friendlist.this);
    }

    private void collectUserFriendlist(){
        DatabaseRef = FirebaseDatabase.getInstance().getReference("users");

        listener = DatabaseRef.child(MyUserData.getInstance().getUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    // mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "name_" + i, "contents_" + i);

                    FriendData tmp = snapshot.getValue(FriendData.class);
                    Toast.makeText(getApplicationContext(), tmp.name+ " " + tmp .contents, Toast.LENGTH_SHORT).show();
                    mMyAdapter.addItem(tmp);
                }
                /* 리스트뷰에 어댑터 등록 */
                ListView.setAdapter(mMyAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        // 화면 제거되면 리스너 해제.
        DatabaseRef.removeEventListener(listener);
    }
}
