package com.example.semana13p2;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.jikan.moe/v4/";
    private RecyclerView recyclerView;
    private AmineAdapter animeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        animeAdapter = new AmineAdapter(null);
        recyclerView.setAdapter(animeAdapter);

        // Configuración de Retrofit con ScalarsConverterFactory
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)  // URL base de la API
                .addConverterFactory(ScalarsConverterFactory.create())  // Usamos ScalarsConverterFactory para obtener la respuesta como String
                .build();
        // Crear la API
        JikanApi jikanApi = retrofit.create(JikanApi.class);

        Call<String> call = jikanApi.getAnimes();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    try {
                        String responseBody = response.body();
                        JSONObject jsonResponse = new JSONObject(responseBody);
                        JSONArray dataArray = jsonResponse.getJSONArray("data");

                        List<AnimeResponse.Anime> animeList = AnimeResponse.fromJsonArray(dataArray);
                        animeAdapter.setAnimeList(animeList);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("API Error", "Error en la respuesta");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("API Error", t.getMessage());
            }
        });
    }
}