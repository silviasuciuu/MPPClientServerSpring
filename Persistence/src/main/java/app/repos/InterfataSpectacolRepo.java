package app.repos;

import model.Spectacol;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public interface InterfataSpectacolRepo {
    public Connection connect();
    public void adauga(Spectacol s);
    public Spectacol cauta(String id);
    public void modifica(Spectacol s);
    public List<String> cautaDupaZi(LocalDate zi);
    public List<Spectacol> getAll();
}
