package repos;

import model.AngajatOficiu;
import model.Artist;
import model.Bilet;
import model.Spectacol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class AngajatOficiuRepo implements InterfataAngajatRepo {
    private String url="jdbc:sqlite:C:\\Users\\User\\Desktop\\MPPClientServer(1)\\MPPClientServer\\bazededate";
    private static final Logger logger = LogManager.getLogger();

    public AngajatOficiuRepo(String url)
    {
        logger.info("Init repos.AngajatOficiuRepo with properties: {} ",url);
    }

    public AngajatOficiuRepo() {
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
    public List<Artist> cautaArtisti(LocalDate zi) {
        logger.traceEntry("Cautare artisti care au concert in ziua {} ",zi);
        SpectacolRepo srepo = new SpectacolRepo(url);
        List<String> spec = srepo.cautaDupaZi(zi); //spectacole dintr-o anumita zi
        ArtistiSpectacolRepo asrepo = new ArtistiSpectacolRepo(url);
        List<Artist> rez3 = new ArrayList<>();
        for (String i : spec) {
            asrepo.cautaArtisti(i).forEach(x -> rez3.add(x));

        }
        logger.traceExit();
        return rez3;

    }

    @Override
    public void vinde(String idSpectacol, String nume, int numarLocuri) {
        logger.traceEntry("vinde {} bilete la spectacolul cu id-ul {},cumparaatorului cu numele {} ",numarLocuri,idSpectacol,nume);
        BiletRepo brepo = new BiletRepo(url);
        SpectacolRepo srepo = new SpectacolRepo(url);
        brepo.adauga(new Bilet(idSpectacol, nume, numarLocuri));
        Spectacol s = srepo.cauta(idSpectacol);
        int nrv = s.getNrLocuriDejaVandute();
        int nrd = s.getNrLocuriDisponibile();
        if(nrd-numarLocuri>=0)
        { s.setNrLocuriDejaVandute(nrv + numarLocuri);
        s.setNrLocuriDisponibile(nrd - numarLocuri);
        srepo.modifica(s);}

        logger.traceExit();


    }
    public AngajatOficiu cautaAngatjat(String nume, String parola) throws SQLException {
        String s = "SELECT * FROM AngajatOficiu WHERE username= ? AND passw=?; ";
        AngajatOficiu rez=null;
        Connection c=this.connect();
        PreparedStatement ps = c.prepareStatement(s);
        ps.setString(1,nume);
        ps.setString(2,parola);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
            rez=new AngajatOficiu(rs.getString("id"),rs.getString("username"),rs.getString("passw"));
        return rez;
    }

    public List<AngajatOficiu> getAll() throws SQLException {
        List<AngajatOficiu>rezz=new ArrayList<>();
        String s = "SELECT * FROM AngajatOficiu ";
        AngajatOficiu rez=null;
        Connection c=this.connect();
        PreparedStatement ps = c.prepareStatement(s);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {rez=new AngajatOficiu(rs.getString("id"),rs.getString("username"),rs.getString("passw"));
        rezz.add(rez);}
        return rezz;
    }

}
