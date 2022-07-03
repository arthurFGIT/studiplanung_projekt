package de.hsrm.mi.swt.UI.StudentUI.Views;

import de.hsrm.mi.swt.main.App;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ErrorView extends ScrollPane{

    private App app;
    private ObservableList<String> observableMessages;
    private VBox messagesBox;

    
    public ErrorView(App app){
        this.app = app;
        observableMessages = app.getErrorService().getObservableMessages();

        this.getStylesheets().add("style.css");
        this.getStyleClass().add("body");

        this.setMaxHeight(70.0);

        messagesBox = new VBox();


        this.setContent(messagesBox);

        initialize();
    }

    public void createVBox(){

        messagesBox.getChildren().clear();

        for(int i = 0; i < observableMessages.size(); i++){
            Text text = new Text(observableMessages.get(i));
            messagesBox.getChildren().add(text);
        }

    }

    private void initialize() {

        observableMessages.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                createVBox();
            }

        });

    }


}
