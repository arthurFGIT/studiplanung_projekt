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
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
/**
 * FlowPaneView ist eine View (ScrollPane), die ein Semester darstellt
 * @author Marie Bohnert, Beate Arnold, Arthur Fieguth
 */
public class FlowPaneView extends FlowPane implements PropertyChangeListener {

    private Fachsemester semester;
    private Map<Integer, ModulView> modulViewMap;
    private Studiensemester studiensemester;
    private App app;
    private Map<Integer, Map<Integer, ModulView>> modulViewsListe;
    private CheckService checkService;
    private Text semZahl;

    /**
     * Konstruktor für die FlowPaneView
     * @param app
     * @param semester : jeweilige Semester von der FlowPane
     * @param modulViewMap : Map von ModulViews
     * @param modulViewsListe : Liste von ModulViewMaps (Alle Semester)
     * @param studiensemester : das jeweilige Studiensemester
     */
    public FlowPaneView(App app, Fachsemester semester, Map<Integer, ModulView> modulViewMap,
            Map<Integer, Map<Integer, ModulView>> modulViewsListe,
            Studiensemester studiensemester) {

        this.app = app;
        this.semester = semester;
        this.studiensemester = studiensemester;
        this.checkService = app.getCheckService();
        this.setMinWidth(1300.0);
        this.setMinHeight(70.0);
        this.modulViewMap = modulViewMap;
        this.modulViewsListe = modulViewsListe;
        this.getStylesheets().add("style.css");
        this.getStyleClass().add("flow-pane");

        semZahl = new Text(String.valueOf(this.semester.getid()));
        semZahl.getStyleClass().add("sem-Zahl");

        setModulViews();

        System.out.println("Observer added");

        studiensemester.addPropertyChangeListener(this);

        initialize();

    }

    /**
     * Setzt die ModulViews in die ScrollPane
     */
    private void setModulViews() {
        this.getChildren().add(semZahl);
        for (int k : modulViewMap.keySet()) {
            this.getChildren().add(modulViewMap.get(k));
        }
    }

    /**
     * Setzt die ModulViews in die ScrollPane neu
     */
    private void setModulViewsNew() {
        this.getChildren().clear();
        this.getChildren().add(semZahl);
        for (int k : modulViewMap.keySet()) {
            this.getChildren().add(modulViewMap.get(k));
        }
        initialize();
    }

    /**
     * Fügt für jede ModulViewMap einen setOnDragDetected Handler und einen setOnMouseReleased Handler
     */
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

                    System.out.println("Vorheriges Sem: " + semester.getid());
                    System.out.println("Zum Verschieben Sem: " +  m.getVerschobenesFachsemester().getid());

                    if (semester.getid() != m.getVerschobenesFachsemester().getid()) {
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
                }
            });

        }

    }
    
    /** 
     * Gibt Fachsemster zurück
     * @return Fachsemester
     */
    public Fachsemester getSemester() {
        return semester;
    }

    
    /** 
     * Gibt ModulViewMap zurück
     * @return Map<Integer, ModulView>
     */
    public Map<Integer, ModulView> getModulViewMap() {
        return modulViewMap;
    }

    
    /** 
     * Reagiert auf Change Events
     * (ADD_MODUL_TO_SEMESTER : Fügt ModulView in die passende ModulViewMap ein)
     * (REMOVE_MODUL_FROM_SEMESTER : löscht die ModulView aus der passenden ModulViewMap) 
     * @param event
     */
    @Override
    public void propertyChange(PropertyChangeEvent event) {

        studiensemester = (Studiensemester) event.getSource();
        Modul modul = studiensemester.getCurrentModul();

        switch (event.getPropertyName()) {
            case Studiensemester.ADD_MODUL_TO_SEMESTER:

                Map<Integer, ModulView> modulViewMapTemp = modulViewsListe
                        .get(modul.getVorherigesFachsemester().getid());
                ModulView modulViewTemp = null;
                for (int k : modulViewMapTemp.keySet()) {
                    if (modulViewMapTemp.get(k).getModul().getId() == modul.getId()) {
                        modulViewTemp = modulViewMapTemp.get(k);
                        break;
                    }
                }

                modulViewMap.put(modul.getId(), modulViewTemp);
                setModulViewsNew();

                System.out.println("Modul auf der GUI zu neuer View");
                break;
            case Studiensemester.REMOVE_MODUL_FROM_SEMESTER:
                Map<Integer, ModulView> modulViewMapTemp2 = modulViewsListe
                        .get(modul.getVorherigesFachsemester().getid());
                for (int k : modulViewMapTemp2.keySet()) {
                    if (modulViewMapTemp2.get(k).getModul().getId() == modul.getId()) {
                        this.getModulViewMap().remove(k);
                        break;
                    }
                }

                setModulViewsNew();
                System.out.println(modulViewMap);
                System.out.println("Modul auf der GUI aus alter View");
                break;
            default:
                throw new IllegalArgumentException("UnbehandeltesEvent " + event);
        }
      
    }

}
