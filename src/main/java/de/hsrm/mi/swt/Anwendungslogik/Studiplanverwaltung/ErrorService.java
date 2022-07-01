package de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ErrorService {

    private ObservableList<String> observableMessages;
    private List<String> errorMessages;

    public ErrorService(){
        errorMessages = new ArrayList<>();
        observableMessages = FXCollections.observableArrayList(errorMessages);
    }

    public ObservableList<String> getObservableMessages() {
        return observableMessages;
    }

    public void setObservableMessages(ObservableList<String> observableMessages) {
        this.observableMessages = observableMessages;
    }    

}
