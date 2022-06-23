package de.hsrm.mi.swt.UI.StudentUI.Views;

import de.hsrm.mi.swt.main.App;
import javafx.scene.layout.BorderPane;

public class StudentView extends BorderPane {

    private StudienplanungView studienplanungView;
    private UebersichtView uebersichtView;
	

    public StudentView(App app){

		
        studienplanungView = new StudienplanungView(app);
        uebersichtView = new UebersichtView(app);
       
        this.setLeft(studienplanungView);
        this.setRight(uebersichtView);

    }

}
