package com.example.cinema.Manager;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashSet;
import java.util.Set;

public class LikedFilmsManager {
    private SharedPreferences sharedPreferences;

    public LikedFilmsManager(Context context) {
        sharedPreferences = context.getSharedPreferences("LikedFilmsPrefs", Context.MODE_PRIVATE);
    }

    public void toggleFilmLiked(String filmId) {
        Set<String> likedFilms = new HashSet<>(sharedPreferences.getStringSet("likedFilms", new HashSet<>()));
        if (!likedFilms.add(filmId)) {  // If add returns false, filmId was already in the set
            likedFilms.remove(filmId);  // Toggle off
        }
        sharedPreferences.edit().putStringSet("likedFilms", likedFilms).apply();
    }

    public Set<String> getLikedFilms() {
        return sharedPreferences.getStringSet("likedFilms", new HashSet<>());
    }

    public boolean isFilmLiked(String filmId) {
        Set<String> likedFilms = getLikedFilms();
        return likedFilms.contains(filmId);
    }
}
