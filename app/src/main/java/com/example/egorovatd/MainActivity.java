package com.example.egorovatd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Context context;
    LayoutInflater layoutInflater;
    List<User> users= new ArrayList<>();
    UserListAdapter userListAdapter;
    FrameLayout UserPanel;
    TextView NameTextView,StateTextView,AgeTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddUsersInList();
        Init();
    }

    private void AddUsersInList() {
        users.add(new User("Волынский","Делает",19));
        users.add(new User("Иванов","Делает",19));
        users.add(new User("Егорова","Делает",19));
    }

    private void Init() {
        //Здесь компоненты перестали добавляться перетаскиванием,проблема будет решена у меня студия жутко глючит
        //UserPanel=findViewById(R.id.userPanel);
        NameTextView=findViewById(R.id.NameTextView);
        StateTextView=findViewById(R.id.StateTextView);
        //AgeTextView=findViewById(R.id.AgeTextView);
        listView=findViewById(R.id.User_LV);
        context=this;
        layoutInflater=LayoutInflater.from(context);
        UserListAdapter userListAdapter=new  UserListAdapter();
        listView.setAdapter(userListAdapter);
    }

    public void BackToList(View view) {
        UserVisibility(false);
    }

    private void UserVisibility(boolean visible) {
        if(visible)
            UserPanel.setVisibility(View.VISIBLE);
        else
            UserPanel.setVisibility(View.GONE);
    }

    private class UserListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public Object getItem(int position) {
            return users.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View currentView, ViewGroup parent) {
            User currentUser = (User) getItem(position);
            currentView=layoutInflater.inflate(R.layout.item_user,parent,  false);

            TextView nameView=currentView.findViewById(R.id.NameTextView);
            TextView stateViaw=currentView.findViewById(R.id.StateTextView);

            nameView.setText(currentUser.getName());
            stateViaw.setText(currentUser.getState());
            currentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // InitPanel(getItem(position));
                   // UserVisibility(visible true);
                }
            });

            return currentView;
        }

        private void InitPanel(User item) {
            NameTextView.setText(item.getName());
            StateTextView.setText((item.getState()));
            AgeTextView.setText(String.valueOf(item.getAge()));
        }
    }
}