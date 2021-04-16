package app.repos;

import model.Artist;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class ArtistRepo implements InterfataArtistRepo {
    private String url="jdbc:sqlite:C:\\Users\\User\\Desktop\\MPPClientServer\\bazededate";
    private static final Logger logger = LogManager.getLogger();

    public ArtistRepo(String url) {
        logger.info("Init app.repos.ArtistRepo with properties: {} ", url);
    }

    public ArtistRepo() {
        this.url="jdbc:sqlite:C:\\Users\\User\\Desktop\\MPPClientServer\\bazededate";
    }

    @Override
    public Connection connect() {
        String url = this.url;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    @Override
    public void adauga(Artist a) {
        logger.traceEntry("save artist with id {} ", a);
        String s = "INSERT INTO Artist(id,nume) VALUES (?,?) ";
        try (Connection con = this.connect();
             PreparedStatement ps = con.prepareStatement(s)) {
            try {
                ps.setString(1, a.getId());
                ps.setString(2, a.getNume());
                ps.executeUpdate();
            } catch (IllegalArgumentException e) {
                logger.error(e);
                System.out.println(e);
            }

        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        logger.traceExit();

    }

    public List<Artist> getAll() {
        List<Artist> rez = new ArrayList<>();
        String s = "SELECT * FROM Artist";
        Artist a = null;
        try (Connection con = this.connect();
             PreparedStatement ps = con.prepareStatement(s)) {
            try {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    a = new Artist(rs.getString("id"), rs.getString("nume"));
                    rez.add(a);
                }
            } catch (IllegalArgumentException e) {
                logger.error(e);
                System.out.println(e);
            }

        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        logger.traceExit();

        return rez;
    }

    @Override
    public Artist cauta(String id) {
        logger.traceEntry("search artist  with id {}", id);
        String s = "SELECT * FROM Artist WHERE id=?";
        Artist a = null;
        try (Connection con = this.connect();
             PreparedStatement ps = con.prepareStatement(s)) {
            try {
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                    a = new Artist(rs.getString("id"), rs.getString("nume"));
            } catch (IllegalArgumentException e) {
                logger.error(e);
                System.out.println(e);
            }

        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        logger.traceExit();
        return a;
    }
}