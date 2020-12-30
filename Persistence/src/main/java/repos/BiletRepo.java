package repos;

import model.Bilet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BiletRepo implements InterfataBiletRepo {
    private String url="jdbc:sqlite:C:\\Users\\User\\Desktop\\MPPClientServer(1)\\MPPClientServer\\bazededate";
    private static final Logger logger = LogManager.getLogger();

    public BiletRepo(String url) {
        logger.info("Init repos.BiletRepo with properties: {} ", url);
    }

    public BiletRepo() {
        this.url="jdbc:sqlite:C:\\Users\\User\\Desktop\\MPPClientServer(1)\\MPPClientServer\\bazededate";
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
    public void adauga(Bilet b) {
        System.out.println("Bilet adaugaaaaaaaaaaaaat");
        logger.traceEntry("save ticket {}", b);
        String s = "INSERT INTO Bilet VALUES(?,?,?)";
        try (Connection con = this.connect();
             PreparedStatement ps = con.prepareStatement(s)) {
            try {
                ps.setString(1, b.getNumeCumparator());
                ps.setString(2, b.getId());
                ps.setInt(3, b.getNumarLocuri());
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

}

