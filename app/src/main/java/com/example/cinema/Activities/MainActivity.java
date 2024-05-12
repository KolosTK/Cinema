package com.example.cinema.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cinema.Adapters.FilmListAdapter;
import com.example.cinema.Domains.ListFilm;
import com.example.cinema.R;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
   private  RecyclerView.Adapter adapterNowMovies,adapterUpcomming,adapterCategory;
   private RecyclerView recyclerViewNowMovies, recyclerViewUpcomming,recyclerViewCategory;
   private RequestQueue mRequestQueue;
   private StringRequest mStringRequest, mStringRequest2,getmStringRequest3;
   private ProgressBar loading1,loading2,loading3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        sendReques();
    }

    private void sendReques() {
        mRequestQueue= Volley.newRequestQueue(this);
        loading1.setVisibility(View.VISIBLE);
        mStringRequest=new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=2", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                loading1.setVisibility(View.GONE);
                ListFilm items=gson.fromJson(response,ListFilm.class);
                adapterNowMovies=new FilmListAdapter(items);
                recyclerViewNowMovies.setAdapter(adapterNowMovies );
            }
        }, new Response.ErrorListener(){
                @Override
                        public void onErrorResponse(VolleyError error){
                        loading1.setVisibility(View.GONE);
                    Log.i("UiLover","onErrorResponse:"+error.toString());
                }
            });
        mRequestQueue.add(mStringRequest);
    }

    private void initView(){
        recyclerViewNowMovies=findViewById(R.id.recyclerView);
        recyclerViewNowMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerViewUpcomming=findViewById(R.id.recyclerView2);
        recyclerViewUpcomming.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerViewCategory=findViewById(R.id.recyclerView3);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        loading1=findViewById(R.id.progressBar1);
        loading2=findViewById(R.id.progressBar2);
        loading3=findViewById(R.id.progressBar3);
    }
}