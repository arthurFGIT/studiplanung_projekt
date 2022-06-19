package de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;


public class StudienPlan {

    	// PropertyChangeEvent-Strings
	public static final String ADD_EVENT = "spielfeld.add";
    private Map<Integer, Modul> modulMap = new HashMap<>();

	
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

	public void add(int id, Modul b) {
		System.out.println("Spielfeld.add " + b);
		modulMap.put(id, b);
		this.pcs.firePropertyChange(ADD_EVENT, null, b);
	}



    public StudienPlan(Map<Integer, Modul> modulMap) {
        this.modulMap = modulMap;
    }

    public Map<Integer, Modul> getModulMap() {
        return modulMap;
    }

    public void setModulMap(Map<Integer, Modul> modulMap) {
        this.modulMap = modulMap;
    }
    

    
}
