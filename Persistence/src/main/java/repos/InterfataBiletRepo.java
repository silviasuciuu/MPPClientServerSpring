package repos;

import model.Bilet;

import java.sql.Connection;

public interface InterfataBiletRepo {
    public Connection connect();
    public void adauga(Bilet b);
}
