package de.hsrm.mi.swt.Anwendungslogik.Benutzerverwaltung;

public class Benutzer {

    private int id;
    private String benutzername;
    private String passwort;


    public Benutzer(int id, String benutzername, String passwort) {
        this.id = id;
        this.benutzername = benutzername;
        this.passwort = passwort;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getBenutzername() {
        return benutzername;
    }


    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }


    public String getPasswort() {
        return passwort;
    }


    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }  
    
}
