package de.hsrm.mi.swt.UI.StudentUI.Views;

import java.util.List;
import java.util.Map;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Fachsemester;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;


public class FlowPaneView extends FlowPane{

    private Fachsemester semester;
    private Map<Integer, ModulView> modulViewMap;

    public final String MODUL = "modul";

    public FlowPaneView(Fachsemester semester, Map<Integer, ModulView> modulViewMap){
        
        this.semester = semester;
        this.setMinWidth(200.0);
		this.setMinHeight(50.0);
        this.modulViewMap = modulViewMap;
        this.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        setModulViews();
        
        initialize();
    }

    private void setModulViews() {
        for(int k : modulViewMap.keySet()){
            this.getChildren().add(modulViewMap.get(k));
        }
    }

    private void initialize() {

        for(int k : modulViewMap.keySet()){
            modulViewMap.get(k).setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("Drag detected");
                    Dragboard dragboard = modulViewMap.get(k).startDragAndDrop(TransferMode.COPY);
                    ClipboardContent content = new ClipboardContent();
                    content.putString(String.valueOf(modulViewMap.get(k).getModul().getId()));
                    dragboard.setContent(content);
                    event.consume();
                }    
            });

            setOnMouseReleased(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    // System.out.println("KringelUI - onMouseReleased " + event);
                    System.out.println((int) event.getX() + " " + (int) event.getY());
                    modulViewMap.get(k).getModul().setVerschobenesFachsemester(semester);
                    event.consume();
                }
            });
    
        }

       
    }

    public Fachsemester getSemester() {
        return semester;
    }

    public Map<Integer, ModulView> getModulViewMap() {
        return modulViewMap;
    }
    
}
