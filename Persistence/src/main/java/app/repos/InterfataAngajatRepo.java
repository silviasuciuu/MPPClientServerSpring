package app.repos;

import model.AngajatOficiu;
import model.Artist;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface InterfataAngajatRepo {
    public Connection connect();
    public List<Artist> cautaArtisti(LocalDate zi);
    public void vinde(String idSpectacol, String nume, int numarLocuri);

    public AngajatOficiu cautaAngatjat(String text, String text1) throws SQLException;
}
