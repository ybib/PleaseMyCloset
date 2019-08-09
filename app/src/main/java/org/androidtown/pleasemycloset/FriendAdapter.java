package org.androidtown.pleasemycloset;

import android.app.Activity;
import android.content.Intent;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.BaseAdapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Dictionary;

class FriendData{
    public String name;
    public String contents;
    public String uid;
    public FriendData(){}
    public FriendData(String _name, String _contents, String _uid){
        name = _name;
        contents = _contents;
        uid = _uid;
    }
}

public class FriendAdapter extends BaseAdapter{
    /* 아이템을 세트로 담기 위한 어레이 */
    private ArrayList<FriendData> mFriends= new ArrayList<>();
    private Activity activity;

    @Override
    public int getCount() {
        return mFriends.size();
    }

    @Override
    public FriendData getItem(int position) {
        return mFriends.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        /* 'listview_custom' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_custom, parent, false);
        }

        /* 'listview_custom'에 정의된 위젯에 대한 참조 획득 */
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name) ;
        TextView tv_contents = (TextView) convertView.findViewById(R.id.tv_contents) ;
        Button btn_friendcloset = (Button)convertView.findViewById(R.id.friend_closet);

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        FriendData newFriend = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        tv_name.setText(newFriend.name);
        tv_contents.setText(newFriend.contents);

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */

        // 친구 옷장 이동 버튼.
        btn_friendcloset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyClosetActivity.class);
                intent.putExtra(MyClosetActivity.FLAG, MyClosetActivity.FRIEND_CLOSET);
                activity.startActivity(intent);
            }
        });
        return convertView;
    }

    public void SetActivity(Activity _activity){
        activity = _activity;
    }

    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
    public void addItem(FriendData newFriend) {
        mFriends.add(newFriend);
    }
}



class ItemsData{
    public String path;
    public String name;
    public ItemsData(){}
    public ItemsData(String _name, String _path){
        path = _path;
        name = _name;
    }
}

class ItemsAdapter extends BaseAdapter{
    /* 아이템을 세트로 담기 위한 어레이 */
    private ArrayList<ItemsData> Items = new ArrayList<>();

    @Override
    public int getCount() {
        return Items.size();
    }

    @Override
    public ItemsData getItem(int position) {
        return Items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();

        /* 'listview_custom' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_custom, parent, false);
        }

        /* 'listview_custom'에 정의된 위젯에 대한 참조 획득 */
        ImageView imgView;
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name) ;
        TextView tv_contents = (TextView) convertView.findViewById(R.id.tv_contents) ;

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        ItemsData newItem = getItem(position);

        // 스토리지에서 불러와서 그림 세팅
        //tv_contents.setText(newItem.path);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        tv_name.setText(newItem .name);

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */

        return convertView;
    }

    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
    public void addItem(ItemsData newItem) {
        Items.add(newItem);
    }
}


// 리사이클러 어댑터
class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<Dictionary> mList;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView id;
        protected TextView english;
        protected TextView korean;


        public CustomViewHolder(View view) {
            super(view);
            this.id = (TextView) view.findViewById(R.id.textview_recyclerview_id);
            this.english = (TextView) view.findViewById(R.id.textview_recyclerview_english);
            this.korean = (TextView) view.findViewById(R.id.textview_recyclerview_korean);
        }
    }

    public CustomAdapter(ArrayList<Dictionary> list) {
        this.mList = list;
    }

    // RecyclerView에 새로운 데이터를 보여주기 위해 필요한 ViewHolder를 생성해야 할 때 호출됩니다.
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, null);
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    // Adapter의 특정 위치(position)에 있는 데이터를 보여줘야 할때 호출됩니다.
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.id.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.english.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.korean.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

        viewholder.id.setGravity(Gravity.CENTER);
        viewholder.english.setGravity(Gravity.CENTER);
        viewholder.korean.setGravity(Gravity.CENTER);

//        viewholder.id.setText(mList.get(position).getId());
//        viewholder.english.setText(mList.get(position).getEnglish());
//        viewholder.korean.setText(mList.get(position).getKorean());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}