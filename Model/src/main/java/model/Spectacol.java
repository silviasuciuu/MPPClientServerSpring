package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Spectacol extends Entity<String> implements Serializable {
    private String id;
    private LocalDateTime data;
    private String locatie;
    private int nrLocuriDisponibile;
    private int nrLocuriDejaVandute;


    public Spectacol(String id, LocalDateTime data, String locatie, int nrLocuriDisponibile, int nrLocuriDejaVandute) {
        this.id = id;
        this.data = data;
        this.locatie = locatie;
        this.nrLocuriDisponibile = nrLocuriDisponibile;
        this.nrLocuriDejaVandute = nrLocuriDejaVandute;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public int getNrLocuriDisponibile() {
        return nrLocuriDisponibile;
    }

    public void setNrLocuriDisponibile(int nrLocuriDisponibile) {
        this.nrLocuriDisponibile = nrLocuriDisponibile;
    }

    public int getNrLocuriDejaVandute() {
        return nrLocuriDejaVandute;
    }

    public void setNrLocuriDejaVandute(int nrLocuriDejaVandute) {
        this.nrLocuriDejaVandute = nrLocuriDejaVandute;
    }

}
