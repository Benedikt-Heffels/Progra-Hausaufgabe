/**
 * Programm, welches einen einfachen Stack implementiert
 */
public class OurStack {
  int currentSize = 0;
  String[] stack;

  public static void main(String[] args) {

    OurStack s = new OurStack();
    String operation;

    do {
      operation = SimpleIO.getString(
        "Bitte geben Sie eine Operation (PUSH,POP,CLEAR,SETSIZE,PRINT,STOP) ein:");
      switch(operation) {
        case "PUSH" -> s.push();
        case "POP" -> s.pop();
        case "CLEAR" -> s.clear();
        case "SETSIZE" -> {
          int size = 0;
          do
            size = SimpleIO.getInt(
              "Bitte geben Sie die (nicht negative) Groesse ein:");
          while(size <  0);
          s.setSize(size);
        }
        case "PRINT", "STOP" -> s.print();
        default -> SimpleIO.output("Fehlerhafte Eingabe!");
      }
    } while(!operation.equals("STOP"));
  }

  public void push() {
    if(currentSize < stack.length){ //currentSize und stack.length sind beide +1 verschoben zur realen Position. <, da min. ein Platz noch freis ein muss.
      stack[currentSize] = SimpleIO.getString("Geben Sie ein zu speicherndes Element ein: ");
      //Da Stack immer von links nach rechts aufgefüllt wird und currenSize ein Element weiter liegt als das letzte Elemenet, lässt sich do die push-Methode implementieren
      currentSize = currentSize + 1;
    }
    else {
      SimpleIO.output("Stack ist voll.");
    }
  }

  public void pop() {
    if (currentSize > 0) { //Stack nicht leer
      stack[currentSize - 1] = ""; //Wird auf leeren String zurückgesetzt
      currentSize = currentSize - 1;
    }
    //Befindet sich kein Element im Stack, dann soll nichts passiert
  }

  public void clear() {
    int laengeStack = stack.length;
    stack = new String[laengeStack-1];
  }

  public void setSize(int size) {
    if (size >= currentSize) { //Wenn der neue Stack größer ist als der alte: Alle Elemente können übernommen werden
      if (currentSize != 0) {
        String[] help = new String[currentSize]; //Es wird ein Hilfsstack erzeugt
        for (int i = 0; i < currentSize; i++) { //Kopieren, um Inhalt bei redefinition des Hauptsstacks nicht zu verlieren
          help[i] = stack[i];
        }
        stack = new String[size]; //Setzen der neuen Größe
        for (int i = 0; i < help.length; i++) { //Einsetzen des neuen Stacks
          stack[i] = help[i];
        }
        //currentSize behält den Wert, den es zuvor hatte, da die Anzahl der Elmenete im Stack nicht verändert werden
      }
      else { //Falls aktuelle Länge (currentSize) == 0, so muss der alte Stack nicht kopiert werden
        stack = new String[size];
        //currentSize bleibt in diesem Falle 0, wie es auch bereits zuvor war.
      }
    }
    else { //Wenn der alte Stack größer war als der neue: Nur so viele Elemente, wie in den Stack rein passen, werden kopiert
      String[] help = new String[size]; //Es wird ein Hilfsstack erzeugt
      for (int i = 0; i < size; i++) { //Kopieren, um zwischenzuspeichern. Begrenzung führt dazu, dass zu viele Elemente verloren gehen.
        help[i] = stack[i];
      }
      stack = new String[size]; //Setzen der neuen Größe
      currentSize = size; //Redefinition der Stack-Größe - hier nötig, da Stack geschrumpft
      for (int i = 0; i < help.length; i++) { //Einsetzen des neuen Stacks
        stack[i] = help[i];
      }
    }
  }

  public void print() {
    String druck = "Stack: "; //Ausgabe in String erstellen
    for (int i = 0; i < currentSize; i++) { //Für die print-Ausgabe wird der gesamte Stack per for-Schleife durchlaufen
      if (i > 0) {
        druck = druck.concat(", ");
      }
      druck = druck.concat(stack[i]);
    }
    SimpleIO.output(druck);
  }
}
