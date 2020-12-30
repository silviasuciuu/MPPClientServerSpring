package model;

import java.io.Serializable;

public class AngajatOficiu implements Serializable {
    private String id;
    private String username;
    private String passw;

    public AngajatOficiu(String id, String username, String passw) {
       this.id=id;
        this.username = username;
        this.passw = passw;
    }

    public AngajatOficiu(String id) {
        this.id = id;
    }

    public AngajatOficiu(String username, String passw) {
        this.username = username;
        this.passw = passw;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }


}
