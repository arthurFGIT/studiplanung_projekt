package de.hsrm.mi.swt.UI.StudentUI.Views;

import de.hsrm.mi.swt.main.App;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class StudentView extends BorderPane{

    private StudienplanungView studienplanungView;
    private UebersichtView uebersichtView;

    private Button btnTest;


    public StudentView(App app){

        studienplanungView = new StudienplanungView();
        uebersichtView = new UebersichtView();


        btnTest = new Button("Test");

        this.setCenter(btnTest);

    }    
}
