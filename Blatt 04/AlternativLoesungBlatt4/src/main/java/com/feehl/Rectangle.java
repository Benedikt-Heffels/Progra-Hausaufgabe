package com.feehl;

/**
 * Class for creating and managing Rectangles (s. Aufgabe)
 */
public class Rectangle {

    private int x,y,width,height;

    /**
     * Constructor creating new Rectangle and setting coordinates of upper left corner as well as width and height
     * @param xInput x-coordinate of upper left corner of the Rectangle
     * @param yInput y-coordinate of upper left corner of the Rectangle
     * @param widthInput width of the Rectangle
     * @param heightInput height of the Rectangle
     */
    public Rectangle(int xInput, int yInput, int widthInput, int heightInput) {
        if(widthInput < 0 || heightInput < 0) {
            Utils.error("Error: height und width duerfen nicht negativ sein!");
            return;
        }
        this.x = xInput;
        this.y = yInput;
        this.width = widthInput;
        this.height = heightInput;
    }

    /**
     * Constructor creating a new Square (Rectangle) by setting coordinates of upper left corner as well as the side length
     * @param xInput x-coordinate of upper left corner of the Rectangle
     * @param yInput y-coordinate of upper left corner of the Rectangle
     * @param sidelengthInput side length of the Square (Rectangle)
     */
    public Rectangle(int xInput, int yInput, int sidelengthInput) {
        this(xInput,yInput,sidelengthInput,sidelengthInput);
    }

    /**
     * Copies an existing rectangle as a new instance, by creating a new rectangle with the same attribute values
     * @param toCopy Rectangle create a copy of
     * @return The copied Rectangle
     */
    public static Rectangle copy(Rectangle toCopy) {
        return new Rectangle(toCopy.x,toCopy.y,toCopy.width,toCopy.height);
    }

    /**
     * Getter for the x-coordinate of the upper left corner of the Rectangle
     * @return x-coordinate of the Rectangle
     */
    public int getX() {
        return x;
    }

    /**
     * Setter for the x-coordinate of the upper left corner of the Rectangle
     * @param x value to set the x-coordinate of the upper left corner of the Rectangle to
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Getter for the y-coordinate of the upper left corner of the Rectangle
     * @return y-coordinate of the Rectangle
     */
    public int getY() {
        return y;
    }

    /**
     * Setter for the y-coordinate of the upper left corner of the Rectangle
     * @param y value to set the y-coordinate of the upper left corner of the Rectangle to
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Getter for the width of the Rectangle
     * @return width of the Rectangle
     */
    public int getWidth() {
        return width;
    }

    /**
     * Setter for the width of the Rectangle
     * @param width value to set the width of the Rectangle to
     */
    public void setWidth(int width) {
        if(width < 0){
            Utils.error("Error: height und width duerfen nicht negativ sein!");
            return;
        }
        this.width = width;
    }

    /**
     * Getter for the height of the Rectangle
     * @return height of the Rectangle
     */
    public int getHeight() {
        return height;
    }

    /**
     * Setter for the height of the Rectangle
     * @param height value to set the height of the Rectangle to
     */
    public void setHeight(int height) {
        if(height < 0){
            Utils.error("Error: height und width duerfen nicht negativ sein!");
            return;
        }
        this.height = height;
    }

    /**
     * Checks whether the given rectangles are all Squares
     * @param rectangles VARARG array of Rectangles to check
     * @return true if all Rectangles are Squares, otherwise false
     */
    public static boolean areSquares(Rectangle... rectangles){
        for(Rectangle rectangle : rectangles){
            if(rectangle.getWidth() != rectangle.getHeight()){
               return false;
            }
        }
        return true;
    }

    /**
     * returns the area of the Rectangle it is called on
      * @return area in unit of coordinate system squared
     */
    public int area(){
        return width*height;
    }

    /**
     * returns the largest possible Rectangle that lies in the intersection of every rectangle
     * @param rectangles VARARG array of Rectangles to intersect
     * @return returns the intersection Rectangle, returns null if the intersection area is empty or there are no given Rectangles as parameters
     */
    public static Rectangle intersection(Rectangle... rectangles){
        if(rectangles.length == 0){
            return null;
        }
        Rectangle intersection = rectangles[0];
        for(Rectangle rectangle : rectangles){
            if(intersectTwo(intersection, rectangle) == null) return null;
            intersection = intersectTwo(intersection, rectangle);
        }
        if(intersection.area() == 0){
            return null;
        }
        return intersection;
    }

    //returns whether the Rectangle r contains the Coordinate Point (x|y)
    private static boolean containsPoint(Rectangle r, int x, int y){
        if((r.getX() <= x) && (r.getX() + r.getWidth() >= x) && (r.getY() >= y) && (r.getY() - r.getWidth() <= y)){
            return true;
        }
        return false;
    }

    private static Rectangle intersectTwo(Rectangle r1, Rectangle r2){

        int x = Utils.max(r1.getX(), r2.getX());
        int y = Utils.min(r1.getY(), r2.getY());
        int dlCornerY = Utils.max(r1.getY() - r1.getHeight(), r2.getY() - r2.getHeight());
        int height = Utils.max(dlCornerY, y) - Utils.min(dlCornerY, y);
        int dlCornerX = Utils.min(r1.getX() + r1.getWidth(), r2.getX() + r2.getWidth());
        int width = Utils.max(dlCornerX, x) - Utils.min(dlCornerX, x);

        if(!Rectangle.containsPoint(r1, x, y) || !Rectangle.containsPoint(r2, x, y)){
            return null;
        }

        return new Rectangle(
                x,
                y,
                width,
                height
        );
    }


    //Oskar
    //Oskar
    //Oskar
    //Oskar
    //Oskar
    /**
     * Hilfsmethode, die den Schnitt zweier Rechteck berechnet.
     * @implNote Diese Methode hat package-Sichtbarkeit, damit automatische Tests funktionieren
     * @param r1 das erste Rechteck
     * @param r2 das zweite Rechteck
     * @return null falls eins der Rechtecke null ist,
     * oder der schnitt der Rechtecke leer ist, sonst der Schnitt $
     */
    private static Rectangle intersectionR2(Rectangle r1, Rectangle r2) {
        if (r1 == null || r2 == null) return null;

        int x_topleft = Utils.max(r1.x, r2.x);
        int y_topleft = Utils.min(r1.y, r2.y); //awt max

        int x_bottomright = Utils.min(r1.x + r1.width, r2.x + r2.width);
        int y_bottomright = Utils.max(r1.y - r1.height, r2.y - r2.height); //awt min

        int width = x_bottomright - x_topleft;
        int heigth = y_topleft - y_bottomright;

        if (width < 0 || heigth < 0) return null;

        return new Rectangle(x_topleft, y_topleft, width, heigth);
    }

    public static Rectangle intersectionOskar(Rectangle... rectangles) {
        if (rectangles.length == 0) return null;

        Rectangle ret = rectangles[0];
        for (int i = 1; i < rectangles.length - 1; i++) {
            if (ret == null) return null;
            ret = Rectangle.intersectionR2(rectangles[i], rectangles[i+1]);
        }

        return ret;
    }


    //Ben
    //Ben
    //Ben
    //Ben
    //Ben
    /**
     * Private Methode, die den Schnitt zweier Rechtecke bestimmt.
     * @param rectangle1 Rechteck 1
     * @param rectangle2 Rechteck 2
     * @return Rechteck, falls ein Rechteck existiert, das beide schneidet. Sonst null.
     */
    private static Rectangle intersectionOfTwo(Rectangle rectangle1, Rectangle rectangle2) {
        //Ein neues Rechteck besteht aus x,y, Breite, Höhe
        int minX = Utils.min(rectangle1.getX(), rectangle2.getX());
        if (!(minX <= rectangle1.getX() + rectangle1.getWidth() && minX <= rectangle2.getX() + rectangle2.getWidth())) {
            return null; //Dann gibt es keine x-Koordinate für die obere linke Ecke, die in beiden Rechtecken liegt.
        }
        int minY = Utils.min(rectangle1.getY(), rectangle2.getY());
        if (!(minY <= rectangle1.getY() + rectangle1.getHeight() && minY <= rectangle2.getY() + rectangle2.getHeight())) {
            return null; //Dann gibt es keine y-Koordinate für die obere linke Ecke, die in beiden Rechtecken liegt.
        }
        int minXWidth = Utils.min((rectangle1.getX() + rectangle1.getWidth()), (rectangle2.getX() + rectangle2.getWidth()));
        //minXWidth ist die maximal entfernteste Position auf der x-Achse. Die maximale Breite erhält man dann aus der Subtraktion minXWidth-minX.
        if (! (minX > minXWidth)){
            return null;
        }
        int minYHeight = Utils.min((rectangle1.getY() + rectangle1.getHeight()), (rectangle2.getY() + rectangle2.getHeight()));
        if (! (minY > minYHeight)){
            return null;
        }
        return new Rectangle(minX, minY, minXWidth-minX, minYHeight-minYHeight);
    }

    /**
     * Bestimmt ein Rechteck, welches eine Liste von übergebenen Rechtecken schneidet.
     * @param rectangles var args von Rechtecken
     * @return Rechteck, falls es ein Rechteck gibt, dass alle anderen Rechtecke schneidet. Sonst null.
     */
    public static Rectangle intersectionBen( Rectangle... rectangles ){
        //Die Methode intersection(Rectangle... rectangles) gibt das größte Rechteck zurück, das vollständig in allen als Argument übergebenen Rechtecken enthalten ist.
        // Wenn rectangles leer ist, soll null zurückgegeben werden.
        // Falls der Schnitt der Rechtecke leer ist, soll ebenfalls null zurückgegeben werden
        if (rectangles == null || rectangles.length == 0) {
            return null;
        }
        Rectangle schnittRectangle = Rectangle.copy(rectangles[0]);
        for (Rectangle rectangle : rectangles) {
            schnittRectangle = intersectionOfTwo(schnittRectangle, rectangle);
            if (schnittRectangle == null) {
                return null;
            }
        }
        return schnittRectangle;
    }


    //
    //
    //
    //
    //


    /**
     * Returns the coordinates of all corners of the Rectangle anticlockwise, beginning with the upper left corner
     * @return returns coordinates of all four corners in the pattern (x|y),...
     */
    public String toString(){
        return "(" + x + "|" + y + "),(" + x + "|" + (y-height) + "),(" + (x+width) + "|" + (y-height) + "),(" + (x+width) + "|" + y + ")";
    }


}
