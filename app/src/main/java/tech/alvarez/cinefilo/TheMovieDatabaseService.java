package tech.alvarez.cinefilo;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import tech.alvarez.cinefilo.model.AuthResponse;
import tech.alvarez.cinefilo.model.Pelicula;
import tech.alvarez.cinefilo.model.PeliculasCineResponse;
import tech.alvarez.cinefilo.model.SessionResponse;

public interface TheMovieDatabaseService {

    @GET("authentication/token/new")
    Call<AuthResponse> newToken(@Query("api_key") String apiKey);

    @GET("authentication/session/new")
    Call<SessionResponse> newSession(@Query("api_key") String apiKey, @Query("request_token") String token);

    @GET("authentication/token/validate_with_login")
    Call<AuthResponse> validateLogIn(@Query("api_key") String apiKey, @Query("username") String username, @Query("password") String password, @Query("request_token") String token);

    @POST("movie/{movie_id}/rating")
    Call<AuthResponse> calificarPelicula(@Path("movie_id") int movieId, @Query("api_key") String apiKey, @Query("session_id") String sessionId);

    @GET("movie/now_playing?language=es")
    Call<PeliculasCineResponse> obtenerPeliculasEnCines(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<Pelicula> obtenerDetallePelicula(@Path("id") int id, @Query("api_key") String apiKey);

}
