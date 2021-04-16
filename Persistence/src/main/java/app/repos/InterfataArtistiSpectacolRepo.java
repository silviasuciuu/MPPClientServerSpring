package app.repos;

import model.Artist;
import model.Spectacol;

import java.sql.Connection;
import java.util.List;

public interface InterfataArtistiSpectacolRepo {
    public Connection connect();
    public void adauga(String idArtist, String idSpectacol);
    public List<Artist> cautaArtisti(String id);
    public List<Spectacol> cautaSpectaole(String id);
}
