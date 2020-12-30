package model;

import java.io.Serializable;

public class Bilet implements Serializable {
    private String id;
    private String numeCumparator;
    private int numarLocuri;

    public Bilet(String id, String numeCumparator, int numarLocuri) {
        this.id = id;
        this.numeCumparator = numeCumparator;
        this.numarLocuri = numarLocuri;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getNumeCumparator() {
        return numeCumparator;
    }

    public void setNumeCumparator(String numeCumparator) {
        this.numeCumparator = numeCumparator;
    }

    public int getNumarLocuri() {
        return numarLocuri;
    }

    public void setNumarLocuri(int numarLocuri) {
        this.numarLocuri = numarLocuri;
    }
}
