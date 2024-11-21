public class AdaptiveList {
    //AdaptiveList als einfach verkettete Liste
    //next = null: Letztes Element der Liste
    //Jede Liste hat mindestens ein Element - leere Liste wird nicht betrachtet
    //Rekursion und MAX 1 SCHLEIFE erlaubt
    //Keine Hilfsmethoden oder Bibliotheksfunktionen
    //Zugriffsmodifikatoren & static erklären - @TODO

    private int value;
    private AdaptiveList next;
    
    public AdaptiveList(int value, AdaptiveList next) {
        this.value = value;
        this.next = next;
    }

    //Selektoren sollen angewendet werden!
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

    public boolean isLast() {
        //@TODO: Sollte private sein?
        //return für einelementige Liste true, falls das aufrufende Objekt das letzte ELement ist, sonst false
        if (this.getNext() == null) { //Die AdaptiveList (die einelementige Liste) ist das letzte Element, wenn das nächste Element (getNext) null ist
            return true;
        }
        return false;
    }

    public AdaptiveList prepend(int value) {
        return new AdaptiveList(value, this); //gibt ein neues AdaptiveList Element zurück, dessen value der übergebene value ist. Next ist this als aufrufendes Element (erstes Element der alten Liste), sodass die neue AdaptiveList am Anfang der Liste steht
        /*Das reutrn-Statement gibt die ganze Liste zurück, mit dem neuen Element als erstem Element. Das geschiet über next = this. (testes)
        */
    }

    public AdaptiveList append(int value) {
        //Erweitert die Liste hinten um den Wert value
        if (getNext() == null) {
            AdaptiveList newNext = new AdaptiveList(value, null); //next ist null, da am Ende eingefügt. Ansonsten wird ein neues Listenelement mit dem übergebenen Wert erzeugt
            this.setNext(newNext); //Vom aktuellen aufrufenden ELement aus gesehen soll das nächste Element das neu erstellte sein
        }
        else {
            this.getNext().append(value);
        }
        return this; //Zum Schluss soll das erste Element (aktuelle Element) zurückgegeben werden
        /*
        Testing: Liste bleibt im Anschluss bestehen.
         */
    }

    public boolean contains(int value) {
        //Methode funktioniert
        //Methode soll zurückgeben, ob ein Wert value in einer Liste enthalten ist
        if (this.isLast() && this.getValue() != value) {
            return false; //Wenn es sich um das letzte Element handelt UND der Wert nicht gleich dem gesuchten Wert ist, gebe false zurück (sonst gäbe es Fehler bei den weiteren Prüfungen)
        }
        else if (this.getValue() == value) {
            return true; //Falls das aktuelle Blatt diesen Wert hat, gebe true zurück / Mehrere Elemente mit selbenm Wert soll nicht betrachtet werden
        }
        return this.getNext().contains(value); //Sonst wiederhole die Methode mit dem nächsten Blatt.
    }

    public boolean containsAdaptive(int value) {
        //Methode funktioniert für alle getesteten Fälle erfolgreich
        if (this.isLast()) {
            return false; //Wenn es sich um das letzte Element handelt, gebe false zurück (sonst gäbe es Fehler bei den weiteren Prüfungen)
        }
        else if (this.getNext().getNext().getValue() == value) { //Prüft den Wert des übernächsten Blattes
            AdaptiveList help = new AdaptiveList(value, this.getNext()); //Überführe den Wert in ein Hilfs-ELement
            this.getNext().setNext(this.getNext().getNext().getNext()); //Für das nächste ELement wird das folgende Element als das über-übernächste ELement definiert (Ein Element wird übersprungen)
            this.setNext(help); //Definiere nächstes Element als das Hilfs-Element
            return true; //Gebe zurück, das Element enthalten ist.
        }
        else if (this.getNext().getValue() == value) { //Unabgedeckter Wert: Nächstes Element ist bereits das gesuchte Element (nur im ersten Aufruf möglich
            //Vorgehen: Erzeugen eines Hilfselements des aktuellen Elements, für aktuelles Element gesuchten Wert setzen und Wert des nächsten Elements durch Wert des Hilfselements ersetzen
            AdaptiveList help = new AdaptiveList(this.getValue(), this.getNext());
            this.setValue(value);
            this.getNext().setValue(help.getValue());
            return true;
        } else if (this.getValue() == value) { //Sonderfall, nur beim ersten Aufruf möglich: Element ist direkt das gesuchte Element -> keine Vertauschung nötig
            return true;
        }
        return this.getNext().containsAdaptive(value); //Wenn nicht enthalten: Führe das Verfahren mit dem nächsten Element fort
    }

    public boolean containsTopPriority(int value) {
        if (!contains(value)) { //Wenn das Element gar nicht erst in der Liste enthalten ist, muss nicht weiter geprüft werden
            return false;
        }
        //@TODO: Element nach ganz vorne bewegen
        //Implementationsidee 1: Value überschreibt erstes Element, der Rest wird über eine Hilfsmethode angehängt
//        AdaptiveList help = this;
//        help = help.prepend(value);
//        System.out.println("help: " + help);
//        this.setValue(value);
//        System.out.println(this);
//        this.setNext(help.getNext());
//        System.out.println("Bitte funktioniere");
//        System.out.println(this);
//        System.out.println("this: " + this);

        //Implementationsidee 2: Rückgriff auf containsAdaptive: Per Schleife so lange ausführen, bis Element vorne

        while (this.getValue() != value) { //Funktionsfähig, @TODO: Schönere Implementation? Jedoch erlaubt
            this.containsAdaptive(value);
        }
        return true;
    }

    //Nicht in der HA gefordert - nur zur Vereinfachung

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
