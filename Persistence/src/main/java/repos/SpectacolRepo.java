package repos;

import model.Spectacol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SpectacolRepo implements InterfataSpectacolRepo {
    private String url="jdbc:sqlite:C:\\Users\\User\\Desktop\\MPPClientServer(1)\\MPPClientServer\\bazededate";
    private static final Logger logger = LogManager.getLogger();

    public SpectacolRepo(String url) {
        logger.info("Init repos.SpectacolRepo with properties: {} ", url);
    }

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
    public void adauga(Spectacol sp) {
        logger.traceEntry("save concert {} ", sp);
        String s = "INSERT INTO Spectacol VALUES (?,?,?,?,?)";
        try (Connection con = this.connect();
             PreparedStatement ps = con.prepareStatement(s)) {
            try {
                ps.setString(1, sp.getId());
                ps.setString(2, String.valueOf(sp.getData()));
                ps.setString(3, sp.getLocatie());
                ps.setInt(4, sp.getNrLocuriDisponibile());
                ps.setInt(5, sp.getNrLocuriDejaVandute());
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
    public Spectacol cauta(String id) {
        logger.traceEntry("search concert with id {}",id);
        String s = "SELECT * FROM Spectacol WHERE ID=?";
        Spectacol a = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        try (Connection con = this.connect();
             PreparedStatement ps = con.prepareStatement(s)) {
            try {
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                    a = new Spectacol(rs.getString("id"), LocalDateTime.parse(rs.getString("data"), formatter), rs.getString("locatie"), rs.getInt("nrLocuriDisponibile"), rs.getInt("nrLocuriDejaVandute"));
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

    @Override
    public void modifica(Spectacol s) {
        logger.traceEntry("modify concert {}",s);
        String sql = "UPDATE Spectacol SET id = ? , " + "data = ? " + " ,locatie = ? " + " ,nrLocuriDisponibile=?" + ",nrLocuriDejaVandute=?"
                + " WHERE id = ?";


        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try {
                pstmt.setString(1, s.getId());
                pstmt.setString(2, s.getData().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
                pstmt.setString(3, s.getLocatie());
                pstmt.setInt(4, s.getNrLocuriDisponibile());
                pstmt.setInt(5, s.getNrLocuriDejaVandute());
                pstmt.setString(6, s.getId());
                pstmt.executeUpdate();
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
    public List<String> cautaDupaZi(LocalDate zi) {
        List<String> rez = new ArrayList<>();
        String s = "SELECT * FROM Spectacol";
        Spectacol a;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        try (Connection con = this.connect();
             PreparedStatement ps = con.prepareStatement(s)) {
            try {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    a = new Spectacol(rs.getString("id"), LocalDateTime.parse(rs.getString("data"), formatter), rs.getString("locatie"), rs.getInt("nrLocuriDisponibile"), rs.getInt("nrLocuriDejaVandute"));
                    if (a.getData().toLocalDate().equals(zi))
                        rez.add(a.getId());
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
    public List<Spectacol> getAll() {
        List<Spectacol> rez = new ArrayList<>();
        String s = "SELECT * FROM Spectacol";
        Spectacol a;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        try (Connection con = this.connect();
             PreparedStatement ps = con.prepareStatement(s)) {
            try {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    a = new Spectacol(rs.getString("id"), LocalDateTime.parse(rs.getString("data"), formatter), rs.getString("locatie"), rs.getInt("nrLocuriDisponibile"), rs.getInt("nrLocuriDejaVandute"));
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
}
