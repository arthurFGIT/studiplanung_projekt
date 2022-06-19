package de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung;

import java.util.ArrayList;
import java.util.List;

public class ErrorService {

    private List<String> errorMessages;

    public ErrorService(){
        errorMessages = new ArrayList<>();
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    //TODO: kann evtl entfernt werden
    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }
    

    

}
