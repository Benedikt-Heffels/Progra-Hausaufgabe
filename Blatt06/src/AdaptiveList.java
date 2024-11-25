public class AdaptiveList {
    //AdaptiveList als einfach verkettete Liste
    //next = null: Letztes Element der Liste
    //Jede Liste hat mindestens ein Element - leere Liste wird nicht betrachtet
    //Rekursion und MAX 1 SCHLEIFE erlaubt
    //Keine Hilfsmethoden oder Bibliotheksfunktionen
    //Zugriffsmodifikatoren & static erklären

    private int value;
    private AdaptiveList next;
    
    public AdaptiveList(int value, AdaptiveList next) {
        this.value = value;
        this.next = next;
    }

    //Selektoren sollen angewendet werden!
    //Für alle Selektoren gilt: public, weil entsprechend der Datenabstraktion ein Zugriff von außen gewünscht ist. Non
    // -static, weil zur Ausführung der Aufgaben der Zugriff auf Attribute eines Objekts notwendig ist
    public int getValue() {
        return this.value;
    }    
    
    public AdaptiveList getNext() {
        return this.next;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public void setNext(AdaptiveList next) {
        this.next = next;
    }

    public static AdaptiveList singletonList(int value) {
        return new AdaptiveList(value, null); //Es wird eine neue, einelementige Liste zurückgegeben, die nur den Wert value enthält
    }

    //public, weil Zugriff von außen auf Methode gewuenscht (man koennte sie theoretisch auch als interne Hilfsmethode betrachten und private setzen, das geht aus der Aufgabenstellung nicht hervor9
    //Non-static, weil Zugriif auf Attribute des Objekts notwendig
    public boolean isLast() {
        //return true, falls das aufrufende Element das letzte ELement der Liste ist, sonst false (heißt, es handelt sich um eine einelementige Liste)
        if (this.getNext() == null) { //Es handelt sich beim aufrufenden ELement (AdaptiveList) um das letzte Element einer Liste, wenn das nächste Element null ist (nicht existiert) -> gebe true zurück
            return true;
        }
        return false;
    }

    //public, weil Zugriff von außen zum Vorsetzen neuer Elemente gewünscht
    //non-static, da Zugriff auf Objekteigenschaften notwendig zur Bearbeitung dieser notwendig.
    public AdaptiveList prepend(int value) {
        return new AdaptiveList(value, this);
        //gibt ein neues AdaptiveList Element zurück, dessen value der übergebene value ist. Next ist this als
        // aufrufendes Element (erstes Element der alten Liste), sodass die neue AdaptiveList am Anfang der Liste steht (das neue Element wird vorne eingefügt)
    }

    //public, weil Zugriff von außen auf die Methode gewünscht, um die Liste zu bearbeiten
    //non-static, da Methode Zugriff auf die Objekteigenschaften des aufrufenden Objekts benötigt, um diese zu bearbeiten
    public AdaptiveList append(int value) {
        //Erweitert die Liste hinten um den Wert value
        if (getNext() == null) {
            AdaptiveList newNext = new AdaptiveList(value, null); //Es wird ein neues Element mit dem übergebenen Wert erzeugt. next ist null, da Element am Ende eingefügt wird.
            this.setNext(newNext); //Vom aktuellen aufrufenden ELement aus gesehen soll das nächste Element das neu erstellte sein
        }
        else {
            this.getNext().append(value); //Durchlaufe die Liste so lange, bis das letztre Element erreicht ist
        }
        return this; //Zum Schluss soll das erste Element (aktuelle Element) zurückgegeben werden
    }

    //public, weil Zugriff von außerhalb der Klasse sinnvoll und erwünscht.
    //non-static, weil die Methode auf das Objekt (die Liste) zugreifen muss, auf dem sie aufgerufen wurde.
    public boolean contains(int value) {
        //Methode soll zurückgeben, ob ein Wert value in einer Liste enthalten ist
        if (this.isLast() && this.getValue() != value) {
            return false; //Wenn es sich um das letzte Element handelt UND der Wert nicht gleich dem gesuchten Wert ist, gebe false zurück (sonst gäbe es Fehler bei den weiteren Prüfungen)
        }
        else if (this.getValue() == value) {
            return true; //Falls das aktuelle Blatt den gesuchten Wert hat, gebe true zurück (Mehrere Elemente mit selbenm Wert soll nicht betrachtet werden)
        }
        return this.getNext().contains(value); //Sonst wiederhole die Methode mit dem nächsten Element.
    }

    //public, weil die Methode von außerhalb der Klasse aus aufgerufen und ausgeführt werden soll
    //non-static, weil die Methode auf die Objekteigenschaften des aufrufenden Objekts zugreifen muss, um zu funktionieren
    public boolean containsAdaptive(int value) {
        if (this.isLast()) {
            return this.getValue() != value; //Wenn es sich um das letzte Element handelt, prüfe, ob der Wert stimmt (falls ja: gebe true zurück, sonst false)
        }
        else if (this.getNext().getValue() == value) {
            //Falls das nächste Element das gesuchte ist: Erzeuge ein Hilfselement des aktuellen Elements, setze für das
            // aktuelle Element den gesuchten Wert und ersetze den Wert des nächsten Elementes durch den Wert aus dem Hilfselement
            AdaptiveList help = new AdaptiveList(this.getValue(), this.getNext());
            this.setValue(value);
            this.getNext().setValue(help.getValue());
            return true;
        } else if (this.getValue() == value) { //Wenn die Liste mehrere Elemente hat und das gesuchte direkt das erste ist: Keine Vertauschung nötig, nur true zurückgeben
            return true;
        }
        return this.getNext().containsAdaptive(value); //Wenn nicht enthalten: Führe das Verfahren mit dem nächsten Element fort
    }

    //public, weil man außerhalb der Klasse auf die Methode zugreifen können und die Metode ausführen soll
    //non-static, da Methode zur Bearbeitung der Reihenfolge in einer Liste und Suche eines Elements Zugriff auf dieses benötigt
    public boolean containsTopPriority(int value) {
        if (!contains(value)) { //Wenn das Element gar nicht erst in der Liste enthalten ist, muss nicht weiter geprüft werden
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
        if(this == null) {
            return "null";
        }
        if (this.isLast()) {
            return this.getValue() + ". ";
        }
        return this.getValue() + " - " + this.getNext().toString();
    }




}
