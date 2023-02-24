package com.example.nativeapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

public class ServicePersonList extends AppCompatActivity {
    private SearchView searchView;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize the search bar and set a listener for it
        setContentView(R.layout.activity_person_list);
        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Do something when the user submits a search query
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Do something when the user changes the search query
                return true;
            }
        });

        List<Person> servicePersons = new ArrayList<>();
        Person[] persons = {
                new Person(R.drawable.avatar, "Joe Biden", "250 SEK"),
                new Person(R.drawable.salon, "Jane Smith", "349 SEK"),
                new Person(R.drawable.avatar, "Bob Johnson", "300 SEK")
        };
        for (Person person : persons) {
            servicePersons.add(new Person(person.getAvatar(), person.getName(), person.getPrice()));
        }
        ServicePersonAdapter adapter = new ServicePersonAdapter(this, servicePersons);
        // Initialize the list and set an adapter for it
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.cons);
        layout.setBackgroundColor(Color.parseColor("#F1EDE7"));
        listView = findViewById(R.id.listArea);
        listView.setAdapter(adapter);
//        adapter.getView(0,null,listView).setOnClickListener(v -> {
//            Intent i = new Intent(ServicePersonList.this, OrderConfirmation.class);
//            startActivity(i);
//        });
    }
    public class Person {
        private int avatar;
        private String name;
        private String price;

        public Person(int avatar, String name, String price) {
            this.avatar = avatar;
            this.name = name;
            this.price = price;
        }

        public int getAvatar() {
            return avatar;
        }

        public String getName() {
            return name;
        }

        public String getPrice() {
            return price;
        }
    }

    public class ServicePersonAdapter extends BaseAdapter {

        private Context context;
        private List<Person> servicePersons;

        public ServicePersonAdapter(Context context, List<Person> servicePersons) {
            this.context = context;
            this.servicePersons = servicePersons;
        }

        @Override
        public int getCount() {
            return servicePersons.size();
        }

        @Override
        public Object getItem(int position) {
            return servicePersons.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.activity_list_item, parent, false);
            }

            ImageView avatarImageView = convertView.findViewById(R.id.avatar);
            TextView nameTextView = convertView.findViewById(R.id.name);
            TextView priceTextView = convertView.findViewById(R.id.price);

            Person servicePerson = servicePersons.get(position);
            avatarImageView.setImageResource(servicePerson.getAvatar());
            nameTextView.setText(servicePerson.getName());
            priceTextView.setText(servicePerson.getPrice());

            return convertView;
        }
    }

}
