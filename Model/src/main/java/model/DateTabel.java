package model;


import java.io.Serializable;

public class DateTabel implements Serializable {
    String numeArtist;
    String IDS;
    String data;
    String locatie;
    int disponibil;
    int vandut;

    public DateTabel(String numeArtist, String IDS, String data, String locatie, int disponibil, int vandut) {
        this.numeArtist = new String(numeArtist);
        this.IDS = new String(IDS);
        this.data = new String(data);
        this.locatie = new String(locatie);
        this.disponibil = disponibil;
        this.vandut = vandut;
    }

    public String getNumeArtist() {
        return numeArtist;
    }

    public void setNumeArtist(String numeArtist) {
        this.numeArtist = numeArtist;
    }

    public String getIDS() {
        return IDS;
    }

    public void setIDS(String IDS) {
        this.IDS = IDS;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public int getDisponibil() {
        return disponibil;
    }

    public void setDisponibil(int disponibil) {
        this.disponibil = disponibil;
    }

    public int getVandut() {
        return vandut;
    }

    public void setVandut(int vandut) {
        this.vandut = vandut;
    }
}
