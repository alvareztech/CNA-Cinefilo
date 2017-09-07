package tech.alvarez.cinefilo.model;

import java.util.List;

public class PeliculasCineResponse {

    private List<Pelicula> results;

    public List<Pelicula> getResults() {
        return results;
    }

    public void setResults(List<Pelicula> results) {
        this.results = results;
    }
}
