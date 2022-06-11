package de.hsrm.mi.swt.Anwendungslogik.Benutzerverwaltung;

public class Nutzer {

    private int id;
    private String benutzername;
    private String passwort;
    private Nutzertyp nutzertyp;


    public Nutzer(int id, String benutzername, String passwort, Nutzertyp nutzertyp) {
        this.id = id;
        this.benutzername = benutzername;
        this.passwort = passwort;
        this.nutzertyp = nutzertyp;
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


    public Nutzertyp getNutzertyp() {
        return nutzertyp;
    }


    public void setNutzertyp(Nutzertyp nutzertyp) {
        this.nutzertyp = nutzertyp;
    }

    


    
    
}
