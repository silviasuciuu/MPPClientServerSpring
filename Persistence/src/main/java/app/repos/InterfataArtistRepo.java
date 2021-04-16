package app.repos;

import model.Artist;

import java.sql.Connection;
import java.util.List;

public interface InterfataArtistRepo {
    public Connection connect();
    public void adauga(Artist a);
    public Artist cauta(String id);
    public List<Artist> getAll();

}
