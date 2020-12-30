package model;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class DateConcertTabel implements Serializable {
    private String nume;
    private String locatie;
    private String ora;
    private int nrDisp;

    public DateConcertTabel(String nume, String locatie, String ora, int nrDisp) {
        this.nume = nume;
        this.locatie = locatie;
        this.ora = ora;
        this.nrDisp = nrDisp;
    }

    public String getNume() {
        return nume;
    }

    public String getLocatie() {
        return locatie;
    }


    public String getOra() {
        return ora;
    }


    public int getNrDisp() {
        return this.nrDisp;
    }

}

