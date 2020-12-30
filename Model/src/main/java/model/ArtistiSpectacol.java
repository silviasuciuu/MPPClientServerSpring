package model;

import java.io.Serializable;

public class ArtistiSpectacol implements Serializable {
    private String id_artist;
    private String id_spectacol;

    public String getId_artist() {
        return id_artist;
    }

    public void setId_artist(String id_artist) {
        this.id_artist = id_artist;
    }

    public String getId_spectacol() {
        return id_spectacol;
    }

    public void setId_spectacol(String id_spectacol) {
        this.id_spectacol = id_spectacol;
    }

    public ArtistiSpectacol(String id_artist, String id_spectacol) {
        this.id_artist = id_artist;
        this.id_spectacol = id_spectacol;
    }
}
