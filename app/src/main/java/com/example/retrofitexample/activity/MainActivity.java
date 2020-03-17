package com.example.retrofitexample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.retrofitexample.Adapter.HeroesAdapter;
import com.example.retrofitexample.Adapter.MyAdapter;
import com.example.retrofitexample.Api;
import com.example.retrofitexample.Model.Hero;
import com.example.retrofitexample.Model.ListItem;
import com.example.retrofitexample.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private static Retrofit retrofit = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//
//        listItems = new ArrayList<>();
//
//        for (int i=0;i<10; i++){
//            ListItem item = new ListItem(
//                    "Heading " + (i+1),
//                    "hello how are you heading " + (i+1)
//            );
//
//            listItems.add(item);
//        }
//
//        adapter = new MyAdapter(listItems,this);
//
//        recyclerView.setAdapter(adapter);
//
        connectAndGetApiData();
//        final ListView  listView = findViewById(R.id.listView);
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Api.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        Api api = retrofit.create(Api.class);
//
//        Call<List<Hero>> call = api.getHeroes();
//
//        call.enqueue(new Callback<List<Hero>>() {
//            @Override
//            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
//                List<Hero>  heroes = response.body();
//
//                String[] heroesNames = new String[heroes.size()];
//
//                for (int i=0; i<heroes.size(); i++){
//                    heroesNames[i] = heroes.get(i).getName();
//                }
//
//                listView.setAdapter(new ArrayAdapter<String>(
//                        getApplicationContext(),
//                        android.R.layout.simple_list_item_1,
//                        heroesNames
//                ));
//
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Hero>> call, Throwable t) {
//                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    public void connectAndGetApiData() {

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Api.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Api api = retrofit.create(Api.class);

            Call<List<Hero>> call = api.getHeroes();

            call.enqueue(new Callback<List<Hero>>() {
                @Override
                public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                    List<Hero> heroes = response.body();

                    //adapter and setting adapter with recyclerView
                    adapter = new HeroesAdapter(heroes, getApplicationContext());

                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<List<Hero>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
