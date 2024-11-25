public class AdaptiveList {
    //AdaptiveList als einfach verkettete Liste
    //next = null: Letztes Element der Liste
    //Jede Liste hat mindestens ein Element - leere Liste wird nicht betrachtet
    //Rekursion und MAX 1 SCHLEIFE erlaubt
    //Keine Hilfsmethoden oder Bibliotheksfunktionen
    //Zugriffsmodifikatoren & static erklaeren

    private int value;
    private AdaptiveList next;
    
    public AdaptiveList(int value, AdaptiveList next) {
        this.value = value;
        this.next = next;
    }

    //Selektoren sollen angewendet werden!
    //Fuer alle Selektoren gilt: public, weil entsprechend der Datenabstraktion ein Zugriff von aussen gewuenscht ist.
    // Non-static, weil zur Ausfuehrung der Aufgaben der Zugriff auf Attribute eines Objekts notwendig ist
    //Rueckgabetyp: int, da der value der Adaptive-List als int definiert ist
    public int getValue() {
        return this.value;
    }    

    //Rueckgabetyp: AdaptiveList, weil das next-Attribut als AdaptiveList definiert wird.
    public AdaptiveList getNext() {
        return this.next;
    }

    //Setter haben keinen Rueckgabetyp
    public void setValue(int value) {
        this.value = value;
    }

    //Setter haben keinen Rueckgabetyp
    public void setNext(AdaptiveList next) {
        this.next = next;
    }

    //public, da man von aussen auf die Methode zugreifen koennen soll, um eine neue einelementige Liste zu erstellen
    //static, da Methode mit der Klasse aufgerufen werden soll und kein Zugriff auf Objektattribute erforderlich ist
    //Rueckgabetyp: AdativeList, da eine neue, einelementige Liste erzeugt wird und zurueckgegeben werden soll
    public static AdaptiveList singletonList(int value) {
        return new AdaptiveList(value, null); //Es wird eine neue, einelementige Liste zurueckgegeben, die nur den Wert value enthaelt
    }

    //public, weil Zugriff von aussen auf Methode gewuenscht (man koennte sie theoretisch auch als interne Hilfsmethode betrachten und private setzen, das geht aus der Aufgabenstellung nicht hervor9
    //non-static, weil Zugriif auf Attribute des Objekts notwendig
    //Rueckgabetyp: boolean (true/false), um zu zeigen, ob es sich um das letzte Element der Liste handelt oder nicht
    public boolean isLast() {
        //return true, falls das aufrufende Element das letzte ELement der Liste ist, sonst false (heisst, es handelt sich um eine einelementige Liste)
        if (this.getNext() == null) { //Es handelt sich beim aufrufenden ELement (AdaptiveList) um das letzte Element einer Liste, wenn das naechste Element null ist (nicht existiert) -> gebe true zurueck
            return true;
        }
        return false;
    }

    //public, weil Zugriff von aussen zum Vorsetzen neuer Elemente gewuenscht
    //non-static, da Zugriff auf Objekteigenschaften notwendig zur Bearbeitung dieser notwendig.
    //Rueckgabetyp: AdaptiveList, da das neu erzeugte ELement (Datentyp: AdaptiveList) zurueckgeben werden soll
    public AdaptiveList prepend(int value) {
        AdaptiveList help = new AdaptiveList(this.getValue(), this.getNext());
        this.setValue(value);
        this.setNext(help);
        return this;
        //gibt ein neues AdaptiveList Element zurueck, dessen value der uebergebene value ist. Next ist die aufrufende Liste
        // sodass die neue AdaptiveList am Anfang der Liste steht (das neue Element wird vorne eingefuegt)
    }

    //public, weil Zugriff von aussen auf die Methode gewuenscht, um die Liste zu bearbeiten
    //non-static, da Methode Zugriff auf die Objekteigenschaften des aufrufenden Objekts benoetigt, um diese zu bearbeiten
    //Rueckgabetyp: AdaptiveList, da das erste Element der neu erzeugten Liste zurueckgegeben werden soll. Dieses hat den Datentyp AdaptiveList.
    public AdaptiveList append(int value) {
        //Erweitert die Liste hinten um den Wert value
        if (getNext() == null) {
            AdaptiveList newNext = new AdaptiveList(value, null); //Es wird ein neues Element mit dem uebergebenen Wert erzeugt. next ist null, da Element am Ende eingefuegt wird.
            this.setNext(newNext); //Vom aktuellen aufrufenden ELement aus gesehen soll das naechste Element das neu erstellte sein
        }
        else {
            this.getNext().append(value); //Durchlaufe die Liste so lange, bis das letztre Element erreicht ist
        }
        return this; //Zum Schluss soll das erste Element (aktuelle Element) zurueckgegeben werden
    }

    //public, weil Zugriff von ausserhalb der Klasse sinnvoll und erwuenscht.
    //non-static, weil die Methode auf das Objekt (die Liste) zugreifen muss, auf dem sie aufgerufen wurde.
    //Rueckgabetyp: boolean (true/false), um zu zeigen, ob der gesuchte Wert in der Liste enthalten ist oder nicht
    public boolean contains(int value) {
        //Methode soll zurueckgeben, ob ein Wert value in einer Liste enthalten ist
        if (this.isLast() && this.getValue() != value) {
            return false; //Wenn es sich um das letzte Element handelt UND der Wert nicht gleich dem gesuchten Wert ist, gebe false zurueck (sonst gaebe es Fehler bei den weiteren Pruefungen)
        }
        else if (this.getValue() == value) {
            return true; //Falls das aktuelle Blatt den gesuchten Wert hat, gebe true zurueck (Mehrere Elemente mit selbenm Wert soll nicht betrachtet werden)
        }
        return this.getNext().contains(value); //Sonst wiederhole die Methode mit dem naechsten Element.
    }

    //public, weil die Methode von ausserhalb der Klasse aus aufgerufen und ausgefuehrt werden soll
    //non-static, weil die Methode auf die Objekteigenschaften des aufrufenden Objekts zugreifen muss, um zu funktionieren
    //Rueckgabetyp: boolean (true/false), um zu zeigen, ob der gesuchte Wert in der Liste enthalten ist oder nicht
    public boolean containsAdaptive(int value) {
        if (this.isLast()) {
            return this.getValue() == value; //Wenn es sich um das letzte Element handelt, pruefe, ob der Wert stimmt (falls ja: gebe true zurueck, sonst false)
        }
        else if (this.getNext().getValue() == value) {
            //Falls das naechste Element das gesuchte ist: Erzeuge ein Hilfselement des aktuellen Elements, setze fuer das
            // aktuelle Element den gesuchten Wert und ersetze den Wert des naechsten Elementes durch den Wert aus dem Hilfselement
            AdaptiveList help = new AdaptiveList(this.getValue(), this.getNext());
            this.setValue(value);
            this.getNext().setValue(help.getValue());
            return true;
        } else if (this.getValue() == value) { //Wenn die Liste mehrere Elemente hat und das gesuchte direkt das erste ist: Keine Vertauschung noetig, nur true zurueckgeben
            return true;
        }
        return this.getNext().containsAdaptive(value); //Wenn nicht enthalten: Fuehre das Verfahren mit dem naechsten Element fort
    }

    //public, weil man ausserhalb der Klasse auf die Methode zugreifen koennen und die Metode ausfuehren soll
    //non-static, da Methode zur Bearbeitung der Reihenfolge in einer Liste und Suche eines Elements Zugriff auf diese benoetigt
    //Rueckgabetyp: boolean (true/false), um zu zeigen, ob der gesuchte Wert in der Liste enthalten ist oder nicht
    public boolean containsTopPriority(int value) {
        if (!contains(value)) { //Wenn das Element gar nicht erst in der Liste enthalten ist, muss nicht weiter geprueft werden
            return false;
        }
        while (this.getValue() != value) { //Da das Ziel der Methode ist, das gesuchte Element auf die erste Position zu
            // bringen, kann man auch einfach die zuvor implementierte Methode containsAdaptive, um das Element eine
            // Position nach vorne zu verschieben, so lange anwenden, bis es ganz vorne ist.
            this.containsAdaptive(value);
        }
        return true;
    }


    //Nicht in der HA gefordert - nur zur Vereinfachung
    //@TODO: VOR ABGABE ENTFERNEN!
    public String toString() {
        if (this.isLast()) {
            return this.getValue() + ". ";
        }
        return this.getValue() + " - " + this.getNext().toString();
    }




}
