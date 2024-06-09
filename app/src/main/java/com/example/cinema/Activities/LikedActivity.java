package com.example.cinema.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.Adapters.FilmListAdapter;
import com.example.cinema.Domains.Datum;
import com.example.cinema.Domains.FilmItem;
import com.example.cinema.Domains.ListFilm;
import com.example.cinema.Manager.LikedFilmsManager;
import com.example.cinema.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LikedActivity extends AppCompatActivity {
    private ImageView backImage;

    private FilmListAdapter filmListAdapter;
    private LikedFilmsManager likedFilmsManager;
    private ListFilm likedFilmsList;
    private RecyclerView likedFilmsRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked);

        initView();
        updateLikedFilms();
    }
    private void initView() {
        backImage = findViewById(R.id.arrow3);
        backImage.setOnClickListener(v -> finish());

        likedFilmsRecyclerView = findViewById(R.id.likedFilmsRecyclerView);
        likedFilmsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter with an empty ListFilm
        likedFilmsList = new ListFilm();
        likedFilmsList.setData(new ArrayList<>());
        filmListAdapter = new FilmListAdapter(likedFilmsList);
        likedFilmsRecyclerView.setAdapter(filmListAdapter);
    }
    private void updateLikedFilms() {
        List<Datum> likedFilms = fetchLikedFilmsDetails();
        if (likedFilms.isEmpty()) {
            Log.d("LikedActivity", "No liked films to display");
        } else {
            likedFilmsList.setData(likedFilms);
            filmListAdapter.notifyDataSetChanged();
        }
    }
    private List<Datum> fetchLikedFilmsDetails() {
        LikedFilmsManager likedFilmsManager = new LikedFilmsManager(this);
        Set<String> filmIds = likedFilmsManager.getLikedFilms();
        List<Datum> likedFilms = new ArrayList<>();
        for (String id : filmIds) {
            Datum film = fetchFilmById(id);
            if (film != null) {
                likedFilms.add(film);
            }
        }
        return likedFilms;
    }

    private Datum fetchFilmById(String filmId) {
        if (filmId == null || filmId.isEmpty()) {
            Log.e("LikedActivity", "Invalid film ID: " + filmId);
            return null;
        }

        Datum film = new Datum();
        try {
            int id = Integer.parseInt(filmId);
            film.setId(id);
        } catch (NumberFormatException e) {
            Log.e("LikedActivity", "Error parsing film ID: " + filmId, e);
            return null;
        }
        film.setTitle("Sample Film " + filmId);
        film.setPoster("URL_of_Poster_for_Film_" + filmId);
        return film;
    }

}
