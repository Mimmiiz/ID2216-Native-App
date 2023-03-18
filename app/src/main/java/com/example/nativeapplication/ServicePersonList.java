package com.example.nativeapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.nativeapplication.model.ServiceProfessional;
import com.example.nativeapplication.retrofit.ApiManager;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ServicePersonList extends AppCompatActivity {
    private SearchView searchView;
    private ListView listView;
    private List<ServiceProfessional> serviceProfessionals;
    private String subCategory;
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
        subCategory = getIntent().getStringExtra("type");
        Log.d("++++++++++++++++++",subCategory);
        serviceProfessionals = new ArrayList<>();
        try {
            getServiceProfessionals();  // Get the list content from the REST api
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
//        List<ServiceProfessional> servicePersons = new ArrayList<>();
//        ServiceProfessional joe = new ServiceProfessional();
//        joe.setName("Joe Biden");joe.setPrice(250.0);
//        ServiceProfessional jane = new ServiceProfessional();
//        jane.setName("Jane Smith");jane.setPrice(349.0);
//        ServiceProfessional daniel = new ServiceProfessional();
//        daniel.setName("Daniel Lind");daniel.setPrice(300.0);
//        ServiceProfessional vera = new ServiceProfessional();
//        vera.setName("Vera Nilsson");vera.setPrice(499.0);
//        ServiceProfessional paul = new ServiceProfessional();
//        paul.setName("Paul George");paul.setPrice(449.0);
//        serviceProfessionals.add(joe);serviceProfessionals.add(jane);serviceProfessionals.add(daniel);serviceProfessionals.add(vera);serviceProfessionals.add(paul);
        /*
        List<Person> servicePersons = new ArrayList<>();
        Person[] persons = {
                new Person(R.drawable.avatar, "Joe Biden", "250 SEK"),
                new Person(R.drawable.salon, "Jane Smith", "349 SEK"),
                new Person(R.drawable.avatar3, "Daniel Lind", "300 SEK"),
                new Person(R.drawable.avatar4, "Vera Nilsson", "499 SEK"),
                new Person(R.drawable.salon, "Paul George", "449 SEK"),
        };
        for (Person person : persons) {
            servicePersons.add(new Person(person.getAvatar(), person.getName(), person.getPrice()));
        }
        */
        ServicePersonAdapter adapter = new ServicePersonAdapter(this, serviceProfessionals);
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

    private void getServiceProfessionals() throws UnsupportedEncodingException {
        MainActivity.apiManager.getServiceProfessionalByCategory(new ApiManager.ApiCallback<List<ServiceProfessional>>() {
            @Override
            public void onSuccess(List<ServiceProfessional> response) {
//                System.out.println("_+_+_+_+_+_+_"+response.get(0));
//                serviceProfessionals = response;
                Log.d("API onSuccess", "response received");
                for(ServiceProfessional serviceProfessional: response){
                    ServiceProfessional serviceProfessional1 = new ServiceProfessional();
                    System.out.println("------"+serviceProfessional.getName());
                    serviceProfessional1.setName(serviceProfessional.getName());
                    serviceProfessional1.setPrice(serviceProfessional.getPrice());
                    serviceProfessionals.add(serviceProfessional1);

                }
//                System.out.println("+++++++++++response = "+response);
            }

            @Override
            public void onFailure(Throwable t) {
                // Show a message in the ListView when there are no service professionals available

//                List<ServiceProfessional> servicePersons = new ArrayList<>();
//                ServiceProfessional joe = new ServiceProfessional();
//                joe.setName("Joe Biden");joe.setPrice(250.0);
//                ServiceProfessional jane = new ServiceProfessional();
//                jane.setName("Jane Smith");jane.setPrice(349.0);
//                ServiceProfessional daniel = new ServiceProfessional();
//                daniel.setName("Daniel Lind");daniel.setPrice(300.0);
//                ServiceProfessional vera = new ServiceProfessional();
//                vera.setName("Vera Nilsson");vera.setPrice(499.0);
//                ServiceProfessional paul = new ServiceProfessional();
//                paul.setName("Paul George");paul.setPrice(449.0);
//                serviceProfessionals.add(joe);serviceProfessionals.add(jane);serviceProfessionals.add(daniel);serviceProfessionals.add(vera);serviceProfessionals.add(paul);

                ListView listView = findViewById(R.id.listArea);
                listView.setVisibility(View.GONE);
                TextView noProfessionals = findViewById(R.id.noProfessionals);
                noProfessionals.setVisibility(View.VISIBLE);
            }
        }, subCategory);
    }

//    public class Person {
//        private int avatar;
//        private String name;
//        private String price;
//
//        public Person(int avatar, String name, String price) {
//            this.avatar = avatar;
//            this.name = name;
//            this.price = price;
//        }
//
//        public int getAvatar() {
//            return avatar;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public String getPrice() {
//            return price;
//        }
//    }

    public class ServicePersonAdapter extends BaseAdapter {

        private Context context;
        private List<ServiceProfessional> servicePersons;

        public ServicePersonAdapter(Context context, List<ServiceProfessional> servicePersons) {
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

            ServiceProfessional servicePerson = servicePersons.get(position);
            //avatarImageView.setImageResource(servicePerson.getAvatar());
            avatarImageView.setImageResource(R.drawable.avatar);
            nameTextView.setText(servicePerson.getName());
            priceTextView.setText(servicePerson.getPrice().toString()+"SEK");

            convertView.setOnClickListener(v -> {
                Intent intent = new Intent(context, BookingActivity.class);
                //intent.putExtra("personAvatar", servicePerson.getAvatar());
                intent.putExtra("personAvatar", R.drawable.avatar);
                intent.putExtra("personName", servicePerson.getName());
                intent.putExtra("personPrice", servicePerson.getPrice());
                context.startActivity(intent);
            });

            return convertView;
        }
    }

}
