package Version_Ben;/*
@ToDo:
- Version_Ben.Rectangle copy method - DONE
- Intersection of two
- Records-Klasse
 */

public class Rectangle {
    private int x;
    private int y;
    private int width;
    private int height;

    /**
     * Konstruktor für ein Rechteck
     * @param xInput x-Koordinate der oberen linken Ecke des Rechtecks als int
     * @param yInput x-Koordinate der oberen linken Ecke des Rechtecks als int
     * @param widthInput nicht-negative Breite des Rechtecks als int
     * @param heightInput nicht-negative Höhe des Rechtecks als int
     */
    public Rectangle (int xInput , int yInput , int widthInput , int heightInput) {
        if (heightInput < 0 || widthInput < 0) { //Fehler, falls Höhe und/oder Breite negativ sind
            Utils.error("Höhe und Breite dürfen nicht negativ sein!");
            return;
        }
        this.x = xInput;
        this.y = yInput;
        this.width = widthInput;
        this.height = heightInput;
    }

    /**
     * Konstruktor für ein Quadrat
     * @param xInput x-Koordinate der oberen linken Ecke des Quadrats als int
     * @param yInput y-Koordinate der oberen linken Ecke des Quadrats als int
     * @param sidelengthInput nicht-negative Seitenlänge des Quadrats als int
     */
    public Rectangle (int xInput , int yInput , int sidelengthInput) {
        new Rectangle(xInput, yInput, sidelengthInput, sidelengthInput); //Zugriff auf Konstruktor für Standard-Version_Ben.Rectangle: Vereinfachte Deklaration
    }


    /**
     * Statische Methode, um ein Version_Ben.Rectangle zu kopieren
     * @param toCopy das zu kopierende Version_Ben.Rectangle
     * @return das kopierte Version_Ben.Rectangle
     */
    public static Rectangle copy (Rectangle toCopy) {
        return new Rectangle(toCopy.getX(), toCopy.getY(), toCopy.getWidth(), toCopy.getHeight());
    }


    //----- Selektoren --------

    /**
     * Gibt die x-Koorinate der oberen linken Ecke des Rechtecks zurück
     * @return x-Koordinate der oberen linken Ecke des Rechtecks als int
     */
    public int getX(){
        return this.x;
    }

    /**
     * Gibt die y-Koorinate der oberen linken Ecke des Rechtecks zurück
     * @return y-Koordinate der oberen linken Ecke des Rechtecks als int
     */
    public int getY(){
        return this.y;
    }

    /**
     * Gibt die nicht-negative Breite des Rechtecks zurück
     * @return Breite des Rechtecks als int
     */
    public int getWidth(){
        return this.width;
    }

    /**
     * Gibt die nicht-negative Höhe des Rechtecks zurück
     * @return Höhe des Rechtecks als int
     */
    public int getHeight(){
        return this.height;
    }

    /**
     * Ändert die x-Koordinate der oberen linken Ecke des Rechtecks
     * @param xInput neue x-Koordinate für die obere linke Ecke des Rechtecks als int
     */
    public void setX(int xInput){
        this.x = xInput;
    }

    /**
     * Ändert die y-Koordinate der oberen linken Ecke des Rechtecks
     * @param yInput neue y-Koordinate für die obere linke Ecke des Rechtecks als int
     */
    public void setY(int yInput){
        this.y = yInput;
    }

    /**
     * Ändert die Breite des Rechtecks
     * @param widthInput neue nicht-negative Breite des Rechtecks als int
     */
    public void setWidth(int widthInput){
        if (widthInput < 0) {
            Utils.error("Die Breite des Reechtecks darf nicht negativ sein");
            return;
        }
        this.width = widthInput;
    }

    /**
     * Ändert die Höhe des Rechtecks
     * @param heightInput neue nicht-negative Höhe des Rechteks als int
     */
    public void setHeight(int heightInput){
        if (heightInput < 0) {
            Utils.error("Die Höhe des Rechtecks darf nicht negativ sein");
            return;
        }
        this.height = heightInput;
    }


    //-------- Methoden ---------

    /**
     * Überprüft, ob alle Rechtecke eines var arg auch Quadrate sind
     * @param rectangles var arg bestehend aus beliebig vielen Rechtecken
     * @return true, falls alle Rechtecke aus rectangles Quadrate sind; false, falls mindestens ein Rechteck kein Quadrat ist
     */
    public static boolean areSquares (Rectangle ... rectangles){
        for(Rectangle rectangletocheck : rectangles){
            if (rectangletocheck.getWidth() != rectangletocheck.getHeight()){
                return false;
            }
        }
        return true;
    }

    /**
     * Berechnet den Flächeninhalt eines Rechtecks
     * @return Flächeninhalt des Rechtecks als int
     */
    public int area () {
        return this.width * this.height;
    }

    /**
     * Private Methode, die den Schnitt zweier Rechtecke bestimmt.
     * @param rectangle1 Rechteck 1
     * @param rectangle2 Rechteck 2
     * @return Rechteck, falls ein Rechteck existiert, das beide schneidet. Sonst null.
     */
    private static Rectangle intersectionOfTwo(Rectangle rectangle1, Rectangle rectangle2) {
        //Ein neues Rechteck besteht aus x,y, Breite, Höhe
        //int minX = Version_Ben.Utils.min(rectangle1.getX(), rectangle2.getX()); //Benötigt für Vergleichsrechnung am Ende
        int maxX = Utils.max(rectangle1.getX(), rectangle2.getX());
        if (!(maxX <= rectangle1.getX() + rectangle1.getWidth() && maxX <= rectangle2.getX() + rectangle2.getWidth())) { //x-Koordinate ist immer der linkeste Punkt
            return null; //Dann gibt es keine x-Koordinate für die obere linke Ecke, die in beiden Rechtecken liegt.
        }
        //int minY = Version_Ben.Utils.min(rectangle1.getY(), rectangle2.getY()); //Benötigt für Vergleichsrechnung am Ende
        int maxY = Utils.max(rectangle1.getY(), rectangle2.getY());
        if (!(maxY <= rectangle1.getY() + rectangle1.getHeight() && maxY <= rectangle2.getY() + rectangle2.getHeight())) {
            return null; //Dann gibt es keine y-Koordinate für die obere linke Ecke, die in beiden Rechtecken liegt.
        }
        int minXWidth = Utils.min((rectangle1.getX() + rectangle1.getWidth()), (rectangle2.getX() + rectangle2.getWidth()));
        //minXWidth ist die maximal entfernteste Position auf der x-Achse. Die maximale Breite erhält man dann aus der Subtraktion minXWidth-minX.
        if ((maxX > minXWidth)){
            return null;
        }
        int minYHeight = Utils.min((rectangle1.getY() + rectangle1.getHeight()), (rectangle2.getY() + rectangle2.getHeight()));
        if ((maxY > minYHeight)){
            return null;
        }
        return new Rectangle(maxX, maxY, minXWidth-maxX, minYHeight-maxY);
    }

    /**
     * Bestimmt ein Rechteck, welches eine Liste von übergebenen Rechtecken schneidet.
     * @param rectangles var args von Rechtecken
     * @return Rechteck, falls es ein Rechteck gibt, dass alle anderen Rechtecke schneidet. Sonst null.
     */
    public static Rectangle intersection( Rectangle... rectangles ){
        //Die Methode intersection(Version_Ben.Rectangle... rectangles) gibt das größte Rechteck zurück, das vollständig in allen als Argument übergebenen Rechtecken enthalten ist.
        // Wenn rectangles leer ist, soll null zurückgegeben werden.
        // Falls der Schnitt der Rechtecke leer ist, soll ebenfalls null zurückgegeben werden
        if (rectangles == null || rectangles.length == 0) {
            return null;
        }
        Rectangle schnittRectangle = Rectangle.copy(rectangles[0]);
        for (Rectangle rectangle : rectangles) {
            System.out.println(schnittRectangle);
             schnittRectangle = intersectionOfTwo(schnittRectangle, rectangle);
             if (schnittRectangle == null) {
                 return null;
             }
        }
        return schnittRectangle;
    }

    /**
     * Erzeugt eine textuelle Repräsentation des Rechtecks im Schema (x|y)-Koordinate beginnend bei der oberen, linken Ecke gegen den Uhrzeigersinn
     * @return textuelle Repräsentation des Rechtecks (als String)
     */
    public String toString(){
        //String-Form: obere linke Ecke - untere linke Ecke - untere rechte Ecke - obere rechte Ecke
        String ausgabe = "";
        //obere linke Ecke
        ausgabe += "(" + this.x + "|" + this.y + "), ";
        // untere linke Ecke
        ausgabe += "(" + this.x + "|" + (this.y - this.height) + "), "; //Minus, das (x|y) odere linke Ecke bezeichnet -> untere linke Ecke dementsprechend y-height
        // untere rechte Ecke
        ausgabe += "(" + (this.x + this.width) + "|" + (this.y - this.height) + "), ";
        //obere rechte Ecke
        ausgabe += "(" + (this.x + this.width) + "|" + this.y + ")";
        return ausgabe;
    }

}
