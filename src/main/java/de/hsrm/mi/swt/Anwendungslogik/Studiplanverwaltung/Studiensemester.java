package de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;
import de.hsrm.mi.swt.main.App;

public class Studiensemester {

    private List<Modul> modulListe;
    private Modul currentModul;
    public static final String ADD_MODUL_TO_SEMESTER = "addModulToSemester";
    public static final String REMOVE_MODUL_FROM_SEMESTER = "removeModulFromSemester";
    
    public Studiensemester(App app) {
        this.modulListe = new ArrayList<>();
    }

    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    public void add(Modul m){
        modulListe.add(m);
    }

    public void addToSemester(Modul m){
        currentModul = m;
        List<Modul> modulListePre = new ArrayList<>(getModulListe()); 
        modulListe.add(m);      
        List<Modul> modulListeNew = getModulListe();  
        this.pcs.firePropertyChange(ADD_MODUL_TO_SEMESTER, modulListePre, modulListeNew);
        System.out.println("Neuer Modulliste hinzugefuegt");   
    }

    public void removeFromSemester(Modul m){
        currentModul = m;
        List<Modul> modulListePre = new ArrayList<>(getModulListe());   
        modulListe.remove(m);     
        List<Modul> modulListeNew = getModulListe();     
        this.pcs.firePropertyChange(REMOVE_MODUL_FROM_SEMESTER, modulListePre, modulListeNew);
        System.out.println("aus alter Modulliste geloescht");
    }



    public List<Modul> getModulListe() {
        return modulListe;
    }

    public void setModulListe(List<Modul> modulListe) {
        this.modulListe = modulListe;
    }

    public Modul getCurrentModul() {
        return currentModul;
    }

    public void setCurrentModul(Modul currentModul) {
        this.currentModul = currentModul;
    }

    @Override
    public String toString() {
        return "Studiensemester [currentModul=" + currentModul + ", modulListe=" + modulListe + "]";
    }

    
    

    
    
}
