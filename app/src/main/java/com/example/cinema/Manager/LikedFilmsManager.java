package com.example.cinema.Manager;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;

public class LikedFilmsManager {
    private SharedPreferences sharedPreferences;

    public LikedFilmsManager(Context context) {
        sharedPreferences = context.getSharedPreferences("LikedFilmsPrefs", Context.MODE_PRIVATE);
    }

    public synchronized void toggleFilmLiked(String filmId) {
        Set<String> currentFilms = sharedPreferences.getStringSet("likedFilms", new HashSet<>());
        Set<String> updatedFilms = new HashSet<>(currentFilms);  // Create a new set based on the current one

        if (!updatedFilms.add(filmId)) {  // Try to add, if it was already there, remove it
            updatedFilms.remove(filmId);
        }

        sharedPreferences.edit().putStringSet("likedFilms", updatedFilms).apply();  // Save the updated set
    }
    public Set<String> getLikedFilms() {
        // Fetch the set from SharedPreferences. If not present, return an empty set.
        Set<String> films = sharedPreferences.getStringSet("likedFilms", new HashSet<>());
        if (films == null) {
            return Collections.emptySet(); // Provide an empty set if null is somehow returned.
        }
        // Return a new HashSet to avoid any direct modifications and make it immutable before returning.
        return Collections.unmodifiableSet(new HashSet<>(films));
    }
    public boolean isFilmLiked(String filmId) {
        return getLikedFilms().contains(filmId);
    }
}
