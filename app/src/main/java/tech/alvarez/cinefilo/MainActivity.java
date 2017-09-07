package tech.alvarez.cinefilo;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.alvarez.cinefilo.adapters.PeliculasAdapter;
import tech.alvarez.cinefilo.model.PeliculasCineResponse;

public class MainActivity extends AppCompatActivity {

    private RecyclerView peliculasRecyclerView;
    private ProgressBar progressBar;
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peliculasRecyclerView = (RecyclerView) findViewById(R.id.peliculasRecyclerView);
        peliculasRecyclerView.setHasFixedSize(true);
        peliculasRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        rootView = findViewById(R.id.rootView);
    }

    @Override
    protected void onStart() {
        super.onStart();

        cargarDatos();
    }

    private void cargarDatos() {
        progressBar.setVisibility(View.VISIBLE);

        TheMovieDatabaseService service = ServiceGenerator.createService(TheMovieDatabaseService.class);
        Call<PeliculasCineResponse> call = service.obtenerPeliculasEnCines(BuildConfig.THE_MOVIE_DB_API_KEY);

        call.enqueue(new Callback<PeliculasCineResponse>() {
            @Override
            public void onResponse(Call<PeliculasCineResponse> call, Response<PeliculasCineResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    PeliculasAdapter adaptador = new PeliculasAdapter(response.body().getResults(), MainActivity.this);
                    peliculasRecyclerView.setAdapter(adaptador);
                } else {
                    mostrarMessage("Ocurrío un problema en el servidor");
                }
            }

            @Override
            public void onFailure(Call<PeliculasCineResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                mostrarMessage("Error al obtener películas: " + t.getMessage());
            }
        });
    }

    private void mostrarMessage(String mensaje) {
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_LONG).show();
    }
}
