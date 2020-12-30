package repos;

import model.Artist;
import model.ArtistiSpectacol;
import model.Spectacol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistiSpectacolRepo implements InterfataArtistiSpectacolRepo {
    public ArtistiSpectacolRepo(String url) {
        logger.info("Init repos.ArtistiSpectacolRepo with properties: {} ",url);
    }
    private static final Logger logger = LogManager.getLogger();
    private String url="jdbc:sqlite:C:\\Users\\User\\Desktop\\MPPClientServer(1)\\MPPClientServer\\bazededate";

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
    public void adauga(String idArtist, String idSpectacol) {
        logger.traceEntry("save artist with id {} at the concert with id {}",idArtist,idSpectacol);
        String s = "INSERT INTO ArtistiSpectacol(id_artist,id_spectacol) VALUES (?,?)";
        try (Connection con = this.connect();
             PreparedStatement ps = con.prepareStatement(s)) {
            try {
                ps.setString(1, idArtist);
                ps.setString(2, idSpectacol);
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


    @Override
    public List<Artist> cautaArtisti(String idSpec) {
        logger.traceEntry("Search artists with id {}",idSpec);
        ArtistRepo ar=new ArtistRepo(url);
        List<Artist> rez = new ArrayList<>();

        String s = "SELECT * FROM ArtistiSpectacol WHERE id_spectacol=?";
        ArtistiSpectacol a;
        try (Connection con = this.connect();
             PreparedStatement ps = con.prepareStatement(s)) {
            try {
                ps.setString(1, idSpec);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    a = new ArtistiSpectacol(rs.getString("id_artist"), rs.getString("id_spectacol"));
                    if ((a.getId_spectacol()).equals(idSpec))
                        rez.add(ar.cauta(a.getId_artist()));
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
    public List<Spectacol> cautaSpectaole(String idArtist) {
        SpectacolRepo ar=new SpectacolRepo(url);
        List<Spectacol> rez = new ArrayList<>();

        String s = "SELECT * FROM ArtistiSpectacol WHERE id_artist=?";
        ArtistiSpectacol a;
        try (Connection con = this.connect();
             PreparedStatement ps = con.prepareStatement(s)) {
            try {
                ps.setString(1, idArtist);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    a = new ArtistiSpectacol(rs.getString("id_artist"), rs.getString("id_spectacol"));
                    if ((a.getId_artist()).equals(idArtist))
                        rez.add(ar.cauta(a.getId_spectacol()));
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


}

