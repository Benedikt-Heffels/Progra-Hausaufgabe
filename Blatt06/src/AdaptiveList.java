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
        //return für einelementige Liste true, falls das aufrufende Objekt das letzte ELement ist, sonst false
        if (this.getNext() == null) { //Die AdaptiveList (die einelementige Liste) ist das letzte Element, wenn das nächste Element (getNext) null ist
            return true;
        }
        return false;
    }

    //@TODO: Aufruf über Liste mit mehreren Elementen?

    public AdaptiveList prepend(int value) {
        return new AdaptiveList(value, this); //gibt ein neues AdaptiveList Element zurück, dessen value der übergebene value ist. Next ist this als aufrufendes Element, sodass die neue AdaptiveList am Anfang der Liste steht
        //@TODO Ganze alte Liste im Anschluss zurückgeben - ist das nicht in next drin?
    }

    public AdaptiveList append(int value) {
        if (getNext() == null) {
            AdaptiveList newNext = new AdaptiveList(value, null); //next ist null, da am Ende eingefügt. Ansonsten wird ein neues Listenelement mit dem übergebenen Wert erzeugt
            this.setNext(newNext); //Vom aktuellen aufrufenden ELement aus gesehen soll das nächste Element das neu erstellte sein
        }
        else {
            this.getNext().append(value);
        }
        return this; //Zum Schluss soll das erste Element (aktuelle Element) zurückgegeben werden
        //@TODO Ganze alte Liste im Anschluss zurückgeben - ist das nicht in next drin?
    }


}
