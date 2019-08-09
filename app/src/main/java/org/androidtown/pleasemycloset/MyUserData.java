package org.androidtown.pleasemycloset;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

// 접속한 사용자 계정의 정보를 전역으로 사용하기 위함.
// 접속하고 나서 init 해야해야함.
public class MyUserData {
    public static MyUserData instance;

    final String USERS = "users";
    final String ITEMS = "items";

    private FirebaseAuth auth;

    private FirebaseUser currentFirebaseUser;
    private StorageReference StorageRef;

    // table 2개, users, imagepath(items)
    private DatabaseReference UserDatabaseRef;
    private DatabaseReference ItemDatabaseRef;


    public static MyUserData getInstance(){
        if(instance == null){
            instance = new MyUserData();
        }
        return instance;
    }

    public void InitUserData(){
        auth = FirebaseAuth.getInstance();
        currentFirebaseUser = auth.getCurrentUser() ;
        StorageRef = FirebaseStorage.getInstance().getReference();
        UserDatabaseRef = FirebaseDatabase.getInstance().getReference(USERS);
        ItemDatabaseRef = FirebaseDatabase.getInstance().getReference(ITEMS);
    }

    public FirebaseUser getUser(){
        return currentFirebaseUser;
    }
    public String getUserUid() {return currentFirebaseUser.getUid();}

    public StorageReference getStorageRef() {return StorageRef;}
    public StorageReference getMyStorageRef() {return StorageRef.child(getUserUid());}
    public StorageReference getUserStorageRef(String uid) {return StorageRef.child(uid);}

    // 회원 가입 등록 후 테이블 등록
    public void addUserData(){
        UserDatabaseRef.child(getUserUid());
        ItemDatabaseRef.child(getUserUid());
    }

    // 일어나서 다시 보자.
    public DatabaseReference getUserDataTable(){return UserDatabaseRef.child(getUserUid());}
    // 친구 목록 보기용
    public DatabaseReference getUserDataTable(String uID){return UserDatabaseRef.child(uID);}

    // 사진 찍어서 스토리지에 저장하고 자신의 테이블에 등록
    public boolean addUserStorageAndTable(String category, String name, String path){
        //StorageRef.child(getUserUid()).child(category).child(path);

        ItemsData newItem = new ItemsData(name, path);

        ItemDatabaseRef.child(getUserUid()).child(category).push().setValue(newItem);
        return true;
    }
}
