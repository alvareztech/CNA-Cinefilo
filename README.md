# Cinéfilo

Android networking course demo application. Retrofit, Glide, RecyclerView.

## Instrucciones

Este proyecto requiere que crees el archivo `gradle.properties` que tenga lo siguiente:

```
TheMovieDatabaseApiKey="TU API KEY AQUI"
```

### Paso 1

En `LoginActivity.java` en el método `obtenerSessionId()`.

```java
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
```


### Paso 2

En `MainActivity.java` en el método `cargarDatos()`.

```java

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
```
