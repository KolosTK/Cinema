package com.example.cinema.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.cinema.Adapters.ActorsListAdapter;
import com.example.cinema.Adapters.CategoryEachFilmListAdapter;
import com.example.cinema.Adapters.CategoryListAdapter;
import com.example.cinema.Adapters.FilmListAdapter;
import com.example.cinema.Domains.FilmItem;
import com.example.cinema.Domains.ListFilm;
import com.example.cinema.R;
import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ProgressBar progressBar;
    private TextView movieNameTxt,movieRating, movieLength, movieSummaryInfo,movieActorsInfo;

    private int idFilm;
    private ImageView image2,backImg;
    private RecyclerView.Adapter adapterActorList, adapterCategory;
    private RecyclerView recyclerViewActors, recyclerViewCategory;
    private NestedScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        idFilm=getIntent().getIntExtra("id",0);
        initView();
        sendRequest();
    }

    private void sendRequest() {
        mRequestQueue = Volley.newRequestQueue(this);
        progressBar.setVisibility(View.VISIBLE);
        mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/"+idFilm, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                progressBar.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);

                FilmItem item = gson.fromJson(response,FilmItem.class);
                Glide.with(DetailActivity.this)
                        .load(item.getPoster())
                        .into(image2);
                movieNameTxt.setText(item.getTitle());
                movieRating.setText(item.getImdbRating());
                movieLength.setText(item.getRuntime());
                movieSummaryInfo.setText(item.getPlot());
                movieActorsInfo.setText(item.getActors());
                if (item.getImages()!=null){
                    adapterActorList=new ActorsListAdapter(item.getImages());
                    recyclerViewActors.setAdapter(adapterActorList);
                }
                if(item.getGenres()!=null){
                    adapterCategory=new CategoryEachFilmListAdapter(item.getGenres());
                    recyclerViewCategory.setAdapter(adapterCategory);
                }
            }
        }, error -> {
            progressBar.setVisibility(View.GONE);
            Log.i("UiLover", "onErrorResponse:" + error.toString());
        });
        mRequestQueue.add(mStringRequest);
    }

    private void initView() {
        movieNameTxt=findViewById(R.id.movieNameTxt);
        progressBar=findViewById(R.id.progressBarDetail);
        scrollView=findViewById(R.id.scrollView3);
        image2=findViewById(R.id.movieImageDetail);
        movieRating=findViewById(R.id.movieRaiting);
        movieLength=findViewById(R.id.movieLength);
        movieSummaryInfo=findViewById(R.id.movieSummaryInfo);
        movieActorsInfo=findViewById(R.id.movieActorsInfo);
        backImg=findViewById(R.id.arrow);
        recyclerViewCategory=findViewById(R.id.genreView);
        recyclerViewActors=findViewById(R.id.actorsView);
        recyclerViewActors.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        backImg.setOnClickListener(v -> finish());

    }
}