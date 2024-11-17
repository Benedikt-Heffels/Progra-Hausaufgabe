/**
 * Class for creating and managing Rectangles (s. Aufgabe)
 */
public class Rectangle {

    private int x,y,width,height;

    /**
     * Constructor creating new Rectangle and setting coordinates of upper left corner as well as width and height
     * @param xInput x-coordinate of upper left corner of the Rectangle
     * @param yInput y-coordinate of upper left corner of the Rectangle
     * @param widthInput not-negative width of the Rectangle
     * @param heightInput not-negative height of the Rectangle
     */
    public Rectangle(int xInput, int yInput, int widthInput, int heightInput) {
        if(widthInput < 0 || heightInput < 0) {
            Utils.error("Error: Höhe und Breite duerfen nicht negativ sein!");
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
     * @param sidelengthInput not-negative side length of the Square (Rectangle)
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
     * @param width not-negative value to set the width of the Rectangle to
     */
    public void setWidth(int width) {
        if(width < 0){
            Utils.error("Error: Breite darf nicht negativ sein!");
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
     * @param height not-negative value to set the height of the Rectangle to
     */
    public void setHeight(int height) {
        if(height < 0){
            Utils.error("Error: Höhe darf nicht negativ sein!");
            return;
        }
        this.height = height;
    }

    /**
     * Static method that checks whether the given rectangles are all Squares
     * @param rectangles var arg array for any number of Rectangles to check
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
     * Returns the area of the Rectangle it is called on
      * @return area in unit of coordinate system squared
     */
    public int area(){
        return width*height;
    }

    /**
     * Static method that returns the largest possible Rectangle that lies in the intersection of every rectangle
     * @param rectangles VARARG array for any number of Rectangles to intersect
     * @return returns the intersection Rectangle, returns null if the intersection area is empty or there are no given Rectangles as parameters
     */
    public static Rectangle intersection(Rectangle... rectangles){
        if(rectangles.length == 0){
            return null;
        }
        Rectangle intersection = rectangles[0];
        for(Rectangle rectangle : rectangles){
            intersection = intersectTwo(intersection, rectangle);
            if(intersection == null) return null;
        }
        return intersection;
    }

    /**
     * Private method that checks whether the Rectangle r contains the Coordinate Point (x|y)
     * @param r Rectangle
     * @param x x-Coordinate of the point
     * @param y y-Coordinate of the point
     * @return true, if point lies in rectangle, else false
     */
    private static boolean containsPoint(Rectangle r, int x, int y){
        if((r.getX() <= x) && (r.getX() + r.getWidth() >= x) && (r.getY() >= y) && (r.getY() - r.getHeight() <= y)){
            return true;
        }
        return false;
    }

    /**
     * Static method that returns the intersection of two Rectangles
     * @param r1 Rectangle 1
     * @param r2 Rectangle 2
     * @return returns the intersection Rectangle, returns null if the intersection area is empty or there are no given Rectangles as parameters
     */
    private static Rectangle intersectTwo(Rectangle r1, Rectangle r2) {

        int x = Utils.max(r1.getX(), r2.getX());
        int y = Utils.min(r1.getY(), r2.getY());
        int dlCornerY = Utils.max(r1.getY() - r1.getHeight(), r2.getY() - r2.getHeight());
        int height = Utils.max(dlCornerY, y) - Utils.min(dlCornerY, y);
        int dlCornerX = Utils.min(r1.getX() + r1.getWidth(), r2.getX() + r2.getWidth());
        int width = Utils.max(dlCornerX, x) - Utils.min(dlCornerX, x);

        if (!Rectangle.containsPoint(r1, x, y) || !Rectangle.containsPoint(r2, x, y)) {
            return null;
        }

        return new Rectangle(x, y, width, height);
    }

    /**
     * Returns the coordinates of all corners of the Rectangle anticlockwise, beginning with the upper left corner
     * @return returns coordinates of all four corners in the pattern (x|y),...
     */
    public String toString(){
        return "(" + this.x + "|" + this.y + "),(" + this.x + "|" + (this.y-this.height) + "),(" + (this.x+this.width) + "|" + (this.y-this.height) + "),(" + (this.x+this.width) + "|" + this.y + ")";
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
    public static Rectangle intersectionR2(Rectangle r1, Rectangle r2) {
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
        for (int i = 0; i < rectangles.length - 1; i++) {
            ret = Rectangle.intersectionR2(ret, rectangles[i+1]);

            if (ret == null) return null;
        }

        return ret;
    }

}
