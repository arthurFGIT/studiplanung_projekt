package de.hsrm.mi.swt.Anwendungslogik.Modulverwaltung;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
public class Modul {


    private int id;
    private String name;
    private String beschreibung;
    // private Prüfungsleistung prüfungsleistung;
    private int cpGesamt;
    private List<Kompetenz> kompetenzGesamt;
    private Fachsemester originalesFachsemester; //TODO: Fachsemester wieder als Klasse, um Koordianten zu speichern
    private Fachsemester verschobenesFachsemester;
    private Fachsemester vorherigesFachsemester;
    private boolean bestanden;
    private List<Lehrveranstaltung> lehrveranstaltungenGesamt;
    public static final String SET_VERSCH_SEMESTER = "verschobenesSemester"; //TODO: googlen was das macht
    private boolean falschVerschoben;

    public Modul(int id, String name, String beschreibung, int cpGesamt, List<Kompetenz> kompetenz,
            Fachsemester originalesFachsemester, Fachsemester verschobenesFachsemester, Fachsemester vorherigesFachsemester, boolean bestanden, List<Lehrveranstaltung> lehrveranstaltungenGesamt) {
        super();
        this.id = id;
        this.name = name;
        this.beschreibung = beschreibung;
        // this.prüfungsleistung = prüfungsleistung;
        this.cpGesamt = cpGesamt;
        this.kompetenzGesamt = kompetenz;
        this.originalesFachsemester = originalesFachsemester;
        this.verschobenesFachsemester = verschobenesFachsemester;
        this.vorherigesFachsemester = vorherigesFachsemester;
        this.bestanden = bestanden;
        this.lehrveranstaltungenGesamt = lehrveranstaltungenGesamt;
        this.falschVerschoben = false;
    }

    //changeEvents für Modul verschieben -> Kopie von DragAndDropFxDing
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    // public Prüfungsleistung getPrüfungsleistung() {
    //     return prüfungsleistung;
    // }

    // public void setPrüfungsleistung(Prüfungsleistung prüfungsleistung) {
    //     this.prüfungsleistung = prüfungsleistung;
    // }

    public int getCpGesamt() {
        return cpGesamt;
    }

    public void setCpGesamt(int cpGesamt) {
        this.cpGesamt = cpGesamt;
    }

    public List<Kompetenz> getKompetenzGesamt() {
        return kompetenzGesamt;
    }

    public void setKompetenzGesamt(List<Kompetenz> kompetenzGesamt) {
        this.kompetenzGesamt = kompetenzGesamt;
    }

    public List<Lehrveranstaltung> getLehrveranstaltungenGesamt() {
        return lehrveranstaltungenGesamt;
    }

    public void setLehrveranstaltungenGesamt(List<Lehrveranstaltung> lehrveranstaltungenGesamt) {
        this.lehrveranstaltungenGesamt = lehrveranstaltungenGesamt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public Fachsemester getOriginalesFachsemester() {
        return originalesFachsemester;
    }

    public void setOriginalesFachsemester(Fachsemester originalesFachsemester) {
        this.originalesFachsemester = originalesFachsemester;
    }

    public Fachsemester getVerschobenesFachsemester() {
        return verschobenesFachsemester;
    }

    public void setVerschobenesFachsemester(Fachsemester verschobenesFachsemester) {
        Fachsemester pre = this.verschobenesFachsemester;
        this.verschobenesFachsemester = verschobenesFachsemester;
        this.pcs.firePropertyChange(SET_VERSCH_SEMESTER, pre, this.verschobenesFachsemester);
        System.out.println("set verschobenes Fachsemester: "+ verschobenesFachsemester.getid());
    }

    public boolean isBestanden() {
        return bestanden;
    }

    public void setBestanden(boolean bestanden) {
        this.bestanden = bestanden;
    }


    @Override
    public String toString() {
        return "Modul [ bestanden=" + bestanden + ", cpGesamt=" + cpGesamt + ", id="
                + id +", name=" + name + ", originalesFachsemester=" + originalesFachsemester.getid()
                + ", verschobenesFachsemester=" + verschobenesFachsemester.getid()
                + ", vorherigesFachsemester=" + vorherigesFachsemester.getid() + "]";
    }

    public Fachsemester getVorherigesFachsemester() {
        return vorherigesFachsemester;
    }

    public void setVorherigesFachsemester(Fachsemester vorherigesFachsemester) {
        this.vorherigesFachsemester = vorherigesFachsemester;
    }

    public boolean isFalschVerschoben() {
        return falschVerschoben;
    }

    public void setFalschVerschoben(boolean falschVerschoben) {
        this.falschVerschoben = falschVerschoben;
    }

    

}
