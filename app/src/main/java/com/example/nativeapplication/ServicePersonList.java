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
        serviceProfessionals = new ArrayList<>();
        try {
            getServiceProfessionals();  // Get the list from the REST api
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        ServicePersonAdapter adapter = new ServicePersonAdapter(this, serviceProfessionals);
        // Initialize the list and set an adapter for it
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.cons);
        layout.setBackgroundColor(Color.parseColor("#F1EDE7"));
        listView = findViewById(R.id.listArea);
        listView.setAdapter(adapter);
    }

    private void getServiceProfessionals() throws UnsupportedEncodingException {
        MainActivity.apiManager.getServiceProfessionalByCategory(new ApiManager.ApiCallback<List<ServiceProfessional>>() {
            @Override
            public void onSuccess(List<ServiceProfessional> response) {
                Log.d("API onSuccess", "response received");
                for(ServiceProfessional serviceProfessional: response){
                    ServiceProfessional serviceProfessional1 = new ServiceProfessional();
                    serviceProfessional1.setName(serviceProfessional.getName());
                    serviceProfessional1.setPrice(serviceProfessional.getPrice());
                    serviceProfessionals.add(serviceProfessional1);
                }
                if(serviceProfessionals.size()==0){
                    ListView listView = findViewById(R.id.listArea);
                    listView.setVisibility(View.GONE);
                    TextView noProfessionals = findViewById(R.id.noProfessionals);
                    noProfessionals.setVisibility(View.VISIBLE);
                }
//                System.out.println("+++++++++++response = "+response);
            }

            @Override
            public void onFailure(Throwable t) {
                // Show a message in the ListView when there are no service professionals available
                ListView listView = findViewById(R.id.listArea);
                listView.setVisibility(View.GONE);
                TextView noProfessionals = findViewById(R.id.noProfessionals);
                noProfessionals.setVisibility(View.VISIBLE);
            }
        }, subCategory);
    }

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
