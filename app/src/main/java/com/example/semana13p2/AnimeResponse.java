package com.example.semana13p2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AnimeResponse {
    private List<Anime> data;

    public List<Anime> getData() {
        return data;
    }

    // Clase interna que representa un anime
    public static class Anime {
        private int mal_id;
        private String title;
        private String image_url;
        private String synopsis;

        public Anime(JSONObject jsonObject) {
            try {
                this.mal_id = jsonObject.getInt("mal_id");
                this.title = jsonObject.getString("title");
                this.image_url = jsonObject.getString("image_url");
                this.synopsis = jsonObject.getString("synopsis");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Getters
        public int getMal_id() {
            return mal_id;
        }

        public String getTitle() {
            return title;
        }

        public String getImage_url() {
            return image_url;
        }

        public String getSynopsis() {
            return synopsis;
        }
    }

    // Método para convertir JSON a lista de Animes
    public static List<Anime> fromJsonArray(JSONArray jsonArray) {
        List<Anime> animeList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject animeObject = jsonArray.getJSONObject(i);
                animeList.add(new Anime(animeObject));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return animeList;
    }
}
