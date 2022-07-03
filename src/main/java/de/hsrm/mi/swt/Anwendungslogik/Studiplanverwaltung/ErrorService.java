package de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * ErrorService, der eine Observable Liste an Strings enthält, welche alle Fehlermeldungen speichert
 * @author Marie Bohnert, Beate Arnold, Arthur Fieguth
 */
public class ErrorService {

    private ObservableList<String> observableMessages;
    private List<String> errorMessages;

    /**
     * Konstruktor für den ErrorService
     * Erzeugt die ObservableList
     */
    public ErrorService(){
        errorMessages = new ArrayList<>();
        observableMessages = FXCollections.observableArrayList(errorMessages);
    }

    
    /** 
     * Gibt die ObservableList zurück
     * @return ObservableList<String> : Liste von Strings mit den Error Messages
     */
    public ObservableList<String> getObservableMessages() {
        return observableMessages;
    }
}
