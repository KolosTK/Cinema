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
import com.example.cinema.Adapters.CategoryListAdapter;
import com.example.cinema.Adapters.FilmListAdapter;
import com.example.cinema.Domains.GenreItem;
import com.example.cinema.Domains.ListFilm;
import com.example.cinema.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   private  RecyclerView.Adapter adapterNowMovies,adapterUpcomming,adapterCategory;
   private RecyclerView recyclerViewNowMovies, recyclerViewUpcomming,recyclerViewCategory;
   private RequestQueue mRequestQueue;
   private StringRequest mStringRequest, mStringRequest2, mStringRequest3;
   private ProgressBar loading1,loading2,loading3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        initView();
        sendRequestNowInCinema();
        sendRequestCategory();
        sendRequestUpcomming();
    }

    private void sendRequestNowInCinema() {
        mRequestQueue = Volley.newRequestQueue(this);
        loading1.setVisibility(View.VISIBLE);
        mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=3", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                loading1.setVisibility(View.GONE);
                ListFilm items = gson.fromJson(response, ListFilm.class);
                adapterNowMovies = new FilmListAdapter(items);
                recyclerViewNowMovies.setAdapter(adapterNowMovies);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading1.setVisibility(View.GONE);
                Log.i("UiLover", "onErrorResponse:" + error.toString());
            }
        });
        mRequestQueue.add(mStringRequest);
    }

    private void sendRequestUpcomming() {
        mRequestQueue = Volley.newRequestQueue(this);
        loading3.setVisibility(View.VISIBLE);
        mStringRequest3 = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=4", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                loading3.setVisibility(View.GONE);
                ListFilm items = gson.fromJson(response, ListFilm.class);
                adapterUpcomming = new FilmListAdapter(items);
                recyclerViewUpcomming.setAdapter(adapterUpcomming);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading3.setVisibility(View.GONE);
                Log.i("UiLover", "onErrorResponse:" + error.toString());
            }
        });
        mRequestQueue.add(mStringRequest3);
    }

    private void sendRequestCategory() {
        mRequestQueue = Volley.newRequestQueue(this);
        loading2.setVisibility(View.VISIBLE);
        mStringRequest2 = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/genres", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                loading2.setVisibility(View.GONE);
                ArrayList<GenreItem> categoryList = gson.fromJson(response,new TypeToken<ArrayList<GenreItem>>(){
                }.getType());
                adapterCategory = new CategoryListAdapter(categoryList);
                recyclerViewCategory.setAdapter(adapterCategory);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading2.setVisibility(View.GONE);
                Log.i("UiLover", "onErrorResponse:" + error.toString());
            }
        });
        mRequestQueue.add(mStringRequest2);
    }

    private void initView(){
        recyclerViewNowMovies=findViewById(R.id.recyclerView);
        recyclerViewNowMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerViewUpcomming=findViewById(R.id.recyclerView3);
        recyclerViewUpcomming.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerViewCategory=findViewById(R.id.recyclerView2);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        loading1=findViewById(R.id.progressBar1);
        loading2=findViewById(R.id.progressBar2);
        loading3=findViewById(R.id.progressBar3);
    }
}