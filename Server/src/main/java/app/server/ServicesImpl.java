package app.server;

import model.*;
import app.repos.*;
import services.AppException;
import services.AppServices;
import services.Observer;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServicesImpl implements AppServices {

    private InterfataAngajatRepo userRepository;
    private InterfataBiletRepo biletRepository;
    List<Observer> observers;
    public ServicesImpl(AngajatOficiuRepo uRepo, BiletRepo mRepo) {

        userRepository = uRepo;
        biletRepository = mRepo;
        observers=new ArrayList<>();

    }


    public synchronized void login(AngajatOficiu user, Observer client) throws AppException, SQLException {
        AngajatOficiu userR = userRepository.cautaAngatjat(user.getUsername(), user.getPassw());
        observers.add(client);

    }


    private final int defaultThreadsNo = 5;



    public synchronized void ticketSold(Bilet b) throws AppException, SQLException {

        userRepository.vinde(b.getId(), b.getNumeCumparator(), b.getNumarLocuri());
        biletRepository.adauga(b);
        notifyAllUsers();


    }

    @Override
    public void logout(Observer client) throws AppException, SQLException {
        observers.remove(client);
    }

    private void notifyAllUsers() {
        ExecutorService executor= Executors.newFixedThreadPool(defaultThreadsNo);
        for(Observer observer:observers){
            executor.execute(()->{
                try{
                    System.out.println("notifying ...");
                    List<DateTabel> sp=getAllShows();
                    observer.tickedSold(sp);
                }catch (AppException | SQLException | IOException e){
                    System.out.println("error notifying ...");
                }
            });

        }
        executor.shutdown();
    }



    public synchronized void logout(AngajatOficiu user, Observer client) throws AppException, SQLException {
       observers.remove(client);

    }

    @Override
    public List<DateTabel> getAllShows() throws AppException, SQLException, IOException {
        System.out.println(getProp());

        InterfataArtistRepo artistRepo=new ArtistRepo(getProp());
        InterfataArtistiSpectacolRepo artistSpectacolRepo=new ArtistiSpectacolRepo(getProp());
        List<DateTabel> rez =new ArrayList<>();

        List<Artist> listArtisti = artistRepo.getAll();
        List<String> idArtisti = new ArrayList<>();
        listArtisti.forEach(x -> idArtisti.add(x.getId()));
        List<Spectacol> spectacole = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        for (Artist a : listArtisti) {
            spectacole = artistSpectacolRepo.cautaSpectaole(a.getId());
            spectacole.forEach(x -> rez.add(new DateTabel(a.getNume(), x.getId(), formatter.format(x.getData()), x.getLocatie(), x.getNrLocuriDisponibile(), x.getNrLocuriDejaVandute())));
        }
        return rez;
    }


    @Override
    public List<DateConcertTabel> findByDay(LocalDate zi) throws IOException {
        InterfataSpectacolRepo specRepo=new SpectacolRepo(getProp());
        InterfataSpectacolRepo spRepo=new SpectacolRepo(getProp());
        List<DateConcertTabel>rez=new ArrayList<>();
        InterfataArtistiSpectacolRepo artistSpectacolRepo=new ArtistiSpectacolRepo(getProp());
        List<String> spectacole = spRepo.cautaDupaZi(zi); //spectacole dintr-o zi
        spectacole.forEach(x -> {
            List<Artist> artisti = artistSpectacolRepo.cautaArtisti(x);
            artisti.forEach(y -> rez.add(new DateConcertTabel(y.getNume(), specRepo.cauta(x).getLocatie(), String.valueOf(specRepo.cauta(x).getData().getHour()) + ":" + String.valueOf(specRepo.cauta(x).getData().getMinute()), specRepo.cauta(x).getNrLocuriDisponibile())));
        });
        return rez;
    }

    public String getProp() throws IOException {
        Properties prop = new Properties();
        prop.load(ServicesImpl.class.getResourceAsStream("/config.properties"));

        return prop.getProperty("proprietate");
    }}



