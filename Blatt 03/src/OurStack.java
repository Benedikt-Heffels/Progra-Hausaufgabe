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
    if (stack == null) {
      SimpleIO.output("Bitte erst Laenge des Stacks definieren.");
    }
    else if(currentSize < stack.length){ //currentSize und stack.length sind beide +1 verschoben zur realen Position. <, da mindestens ein Platz noch frei sein muss.
      stack[currentSize] = SimpleIO.getString("Geben Sie ein zu speicherndes Element ein: ");
      //Da der String-Stack immer von links nach rechts aufgefuellt wird und currentSize ein Element weiter liegt als das letzte Element, laesst sich so die push-Methode implementieren
      currentSize = currentSize + 1;
    }
    else {
      SimpleIO.output("Stack ist voll.");
    }
  }

  public void pop() {
    if (currentSize > 0) { //Stack ist nicht leer
      stack[currentSize - 1] = ""; //Wird auf leeren String zurueckgesetzt
      currentSize = currentSize - 1;
    }
    //Befindet sich kein Element im Stack, dann soll nichts passiert
  }

  public void clear() { //Der gesamte Stack soll gelöscht werden, die Laenge des Arrays bleibt dabei jedoch gleich.
    int laengeStack = stack.length;
    stack = new String[laengeStack];
    currentSize = 0; //Danach soll currentSize = 0 gelten
  }

  public void setSize(int size) {
    if(size == currentSize) return; //Wenn size == currentSize, muss am Stack nichts veraendert werden. Dann kann das Programm abbrechen.
    if (size > currentSize) { //Wenn der neue Stack groesser ist als der alte: Alle Elemente koennen uebernommen werden
      if (currentSize != 0) {
        String[] help = stack; //Kopie des Stacks, in der die Inhalte zwischengespeichert werden
        stack = new String[size]; //Setzen der neuen Groesse
        for (int i = 0; i < help.length; i++) { //Einsetzen in den neuen Stack
          stack[i] = help[i];
        }
        //currentSize behaelt den Wert, den es zuvor hatte, da die Anzahl der Elemente im Stack nicht veraendert wurde
      }
      else { //Falls aktuelle Laenge (currentSize) == 0, so muss der alte Stack nicht kopiert werden
        stack = new String[size];
        //currentSize bleibt in diesem Falle 0, wie es auch bereits zuvor war.
      }
    }
    else { //Wenn der alte Stack groesser war als der neue: Nur so viele Elemente, wie in den Stack hineinpassen, werden kopiert
      String[] help = new String[size]; //Es wird ein Hilfsstack erzeugt (zur Zwischenspeicherung der Inhalte)
      for (int i = 0; i < size; i++) { //Kopieren, um zwischenzuspeichern. Begrenzung fuehrt dazu, dass ELemente, die zu viel sind, verloren gehen (und so geloescht werden).
        help[i] = stack[i];
      }
      stack = new String[size]; //Setzen der neuen Groesse
      currentSize = size; //Redefinition der Stack-Groesse - hier noetig, da Stack geschrumpft
      for (int i = 0; i < help.length; i++) { //Einsetzen des neuen Stacks
        stack[i] = help[i];
      }
    }
  }

  public void print() {
    String druck = "Stack: "; //Ausgabe in String erstellen
    for (int i = 0; i < currentSize; i++) { //Fuer die print-Ausgabe wird der gesamte Stack per for-Schleife durchlaufen
      if (i > 0) { //Wenn mehrere Elemente vorhanden sind, soll zwischen diesen bei der Ausgabe ein Komma gesetzt werden.
        druck = druck.concat(", ");
      }
      druck = druck.concat(stack[i]);
    }
    SimpleIO.output(druck);
  }
}
