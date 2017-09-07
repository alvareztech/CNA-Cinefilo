package tech.alvarez.cinefilo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.alvarez.cinefilo.model.AuthResponse;
import tech.alvarez.cinefilo.model.SessionResponse;
import tech.alvarez.cinefilo.util.Preferencias;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button logInButton;
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        logInButton = (Button) findViewById(R.id.logInButton);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });

        rootView = findViewById(R.id.rootView);
    }

    private void logIn() {
        TheMovieDatabaseService service = ServiceGenerator.createService(TheMovieDatabaseService.class);
        Call<AuthResponse> call = service.newToken(BuildConfig.THE_MOVIE_DB_API_KEY);

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    hacerLogIn(response.body().getToken());
                } else {
                    mostrarMessage("Algún error al crear token");
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                mostrarMessage("Error al crear token: " + t.getMessage());
            }
        });

    }

    private void hacerLogIn(String token) {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        TheMovieDatabaseService service = ServiceGenerator.createService(TheMovieDatabaseService.class);
        Call<AuthResponse> call = service.validateLogIn(BuildConfig.THE_MOVIE_DB_API_KEY, username, password, token);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    obtenerSessionId(response.body().getToken());
                } else {
                    mostrarMessage("Algún error al validar el log in");
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                mostrarMessage("Error al validar log In: " + t.getMessage());
            }
        });
    }

    private void obtenerSessionId(String token) {
        TheMovieDatabaseService service = ServiceGenerator.createService(TheMovieDatabaseService.class);
        Call<SessionResponse> call = service.newSession(BuildConfig.THE_MOVIE_DB_API_KEY, token);

        call.enqueue(new Callback<SessionResponse>() {
            @Override
            public void onResponse(Call<SessionResponse> call, Response<SessionResponse> response) {
                if (response.isSuccessful()) {
                    Preferencias.setSessionId(response.body().getSessionId(), LoginActivity.this);
                    irPantallaPrincipal();
                } else {
                    mostrarMessage("Algún error al obtener la sesión");
                }
            }

            @Override
            public void onFailure(Call<SessionResponse> call, Throwable t) {
                mostrarMessage("Error al crear sesión: " + t.getMessage());
            }
        });
    }

    private void irPantallaPrincipal() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void habilitarViews() {
        usernameEditText.setEnabled(true);
        passwordEditText.setEnabled(true);
        logInButton.setEnabled(true);
    }

    private void deshabilitarViews() {
        usernameEditText.setEnabled(false);
        passwordEditText.setEnabled(false);
        logInButton.setEnabled(false);
    }

    private void mostrarMessage(String mensaje) {
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_LONG).show();
    }
}

