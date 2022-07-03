package de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;
import de.hsrm.mi.swt.main.App;
/**
 * Studiensemester, ist ein spezielles Semester, welches eine Liste der Module in dem Semester enthält
 * @author Marie Bohnert, Beate Arnold, Arthur Fieguth
 */
public class Studiensemester {

    private List<Modul> modulListe;
    private Modul currentModul;
    public static final String ADD_MODUL_TO_SEMESTER = "addModulToSemester";
    public static final String REMOVE_MODUL_FROM_SEMESTER = "removeModulFromSemester";
    
    /**
     * Konstruktor für das Studiensemester, erzeugt die ModulListe
     * @param app
     */
    public Studiensemester(App app) {
        this.modulListe = new ArrayList<>();
    }

    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    
    /** 
     * Fügt Property Change listener hinzu
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    
    /** 
     * Löscht Property Change listener
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    
    /** 
     * Fügt ein Modul der ModulListe hinzu
     * @param m : Modul
     */
    public void add(Modul m){
        modulListe.add(m);
    }

    
    /** 
     * Fügt ein Modul dem ModulListe hinzu und feuert ein PropertyChange
     * @param m : Modul, welches hinzugefügt werden soll
     */
    public void addToSemester(Modul m){
        currentModul = m;
        List<Modul> modulListePre = new ArrayList<>(getModulListe()); 
        modulListe.add(m);      
        List<Modul> modulListeNew = getModulListe();  
        this.pcs.firePropertyChange(ADD_MODUL_TO_SEMESTER, modulListePre, modulListeNew);
        System.out.println(currentModul + "Neuer Modulliste hinzugefuegt");   
    }

    
    /** 
     * Löscht ein Modul aus der ModulListe und feuert ein PropertyChange
     * @param m
     */
    public void removeFromSemester(Modul m){
        currentModul = m;
        List<Modul> modulListePre = new ArrayList<>(getModulListe());   
        modulListe.remove(m);     
        List<Modul> modulListeNew = getModulListe();     
        this.pcs.firePropertyChange(REMOVE_MODUL_FROM_SEMESTER, modulListePre, modulListeNew);
        System.out.println(currentModul + "aus alter Modulliste geloescht");
    }
    
    /** 
     * Gibt die ModulListe zurück
     * @return List<Modul>
     */
    public List<Modul> getModulListe() {
        return modulListe;
    }

    /** 
     * Gibt das aktuelle Modul zurück
     * @return Modul
     */
    public Modul getCurrentModul() {
        return currentModul;
    }
    
    /** 
     * Setzt das aktuelle Modul
     * @param currentModul
     */
    public void setCurrentModul(Modul currentModul) {
        this.currentModul = currentModul;
    }
    
    /** 
     * ToString Methode vom Studiensemester
     * @return String
     */
    @Override
    public String toString() {
        return "Studiensemester [currentModul=" + currentModul + ", modulListe=" + modulListe + "]";
    }

    
    

    
    
}
