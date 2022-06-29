package de.hsrm.mi.swt.UI.StudentUI.Views;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Fachsemester;
import de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung.Modul;
import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.CheckService;
import de.hsrm.mi.swt.Anwendungslogik.Studiplanverwaltung.Studiensemester;
import de.hsrm.mi.swt.main.App;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

public class FlowPaneView extends FlowPane implements PropertyChangeListener {

    private Fachsemester semester;
    private Map<Integer, ModulView> modulViewMap;
    private Studiensemester studiensemester;
    private App app;
    private Map<Integer, Map<Integer, ModulView>> modulViewsListe;
    private CheckService checkService;

    public FlowPaneView(App app, Fachsemester semester, Map<Integer, ModulView> modulViewMap, Map<Integer, Map<Integer, ModulView>> modulViewsListe,
            Studiensemester studiensemester) {
        
        this.app = app;
        this.semester = semester;
        this.studiensemester = studiensemester;
        this.checkService = app.getCheckService();
        this.setMinWidth(200.0);
        this.setMinHeight(50.0);
        this.modulViewMap = modulViewMap;
        this.modulViewsListe = modulViewsListe;
        this.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        setModulViews();
            
        System.out.println("Observer added");

        studiensemester.addPropertyChangeListener(this);

        initialize();

    }

    private void setModulViews() {
        for (int k : modulViewMap.keySet()) {
            this.getChildren().add(modulViewMap.get(k));
        }
    }

    private void setModulViewsNew() {
        this.getChildren().clear();
        for (int k : modulViewMap.keySet()) {            
            this.getChildren().add(modulViewMap.get(k));
        }
        initialize();
    }

    private void initialize() {

        for (int k : modulViewMap.keySet()) {
            modulViewMap.get(k).setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("Drag detected");
                    Dragboard dragboard = modulViewMap.get(k).startDragAndDrop(TransferMode.MOVE);
                    ClipboardContent content = new ClipboardContent();
                    content.putString(String.valueOf(modulViewMap.get(k).getModul().getId()));
                    dragboard.setContent(content);
                    event.consume();
                }
            });

            modulViewMap.get(k).setOnMouseReleased(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    
                    Modul m = modulViewMap.get(k).getModul();

                    
                    // checkService.checkFortschrittsregel(modul, zielFachsemester);
                    // checkService.checkKompetenzen(modul, zielFachsemester);

   
                    m.setVerschobenesFachsemester(semester);
                    
                    app.getModulService().getStudienplan()
                    .holeStudiensemesterMitId(
                            modulViewMap.get(k).getModul().getVerschobenesFachsemester().getid())
                    .addToSemester(modulViewMap.get(k).getModul());
                    System.out.println("Modul zu neuer Liste");
                    
                    app.getModulService().getStudienplan()
                            .holeStudiensemesterMitId(
                                    modulViewMap.get(k).getModul().getVorherigesFachsemester().getid())
                            .removeFromSemester(modulViewMap.get(k).getModul());
                        System.out.println("Modul aus alter Liste entfernt");
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

    @Override
    public void propertyChange(PropertyChangeEvent event) {

        studiensemester = (Studiensemester) event.getSource();
        Modul modul = studiensemester.getCurrentModul();
        
        switch (event.getPropertyName()) {
            case Studiensemester.ADD_MODUL_TO_SEMESTER:
                
                Map<Integer, ModulView> modulViewMapTemp = modulViewsListe.get(modul.getVorherigesFachsemester().getid());
                ModulView modulViewTemp = null;
                for(int k : modulViewMapTemp.keySet()){
                    if(modulViewMapTemp.get(k).getModul().getId() == modul.getId()){        
                        modulViewTemp = modulViewMapTemp.get(k);
                        break;
                    }
                }

                modulViewMap.put(modulViewMap.size(), modulViewTemp);
                setModulViewsNew();
                
                System.out.println("Modul auf der GUI zu neuer View");
                break;
            case Studiensemester.REMOVE_MODUL_FROM_SEMESTER:
                Map<Integer, ModulView> modulViewMapTemp2 = modulViewsListe.get(modul.getVorherigesFachsemester().getid());
                ModulView modulViewTemp2 = null;
                for(int k : modulViewMapTemp2.keySet()){
                    if(modulViewMapTemp2.get(k).getModul().getId() == modul.getId()){        
                        this.getModulViewMap().remove(k);
                        break;
                    }
                }
                
                setModulViewsNew();

                System.out.println("Modul auf der GUI aus alter View");
                break;
            default:
                throw new IllegalArgumentException("UnbehandeltesEvent " + event);
        }

    }

}
