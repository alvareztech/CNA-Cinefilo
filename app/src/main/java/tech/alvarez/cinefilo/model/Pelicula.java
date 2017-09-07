package tech.alvarez.cinefilo.model;

import com.google.gson.annotations.SerializedName;

public class Pelicula {

    private int id;
    private String title;
    private String overview;
    private double popularity;

    @SerializedName("backdrop_path")
    private String imagen;

    @SerializedName("poster_path")
    private String poster;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getImagen() {
        return imagen;
    }

    public String getPoster() {
        return poster;
    }
}
