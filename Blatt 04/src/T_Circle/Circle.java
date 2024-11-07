package T_Circle;

/**
 * Beschreibung der Klasse Circle
 */
public class Circle {

    // Hier Attribute der Klasse einfügen
    private int x;
    private int y;
    private int radius;

    /**
     * Konstruktor fuer einen Kreis
     * @param x x-Koordinate
     * @param y y-Koordinate
     * @param radius radius des Kreises
     * @return den erstellten Kreis
     */
    public Circle(int x, int y, int radius) {
        if (radius < 0){
            T_Circle.Utils.error("radius darf nicht negativ sein;");
            return;
        }
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    /**
     * Beschreibung der Methode Circle(int radius) (Konstruktor)
     * @param radius
     * @return
     */
    public Circle(int radius) {
        // Hier Implementierung einfügen

        this.x = 0;
        this.y = 0;
        this.radius = radius;

        //Alternativ: this(0,0,radius) <- ruft oberen Konstruktor selbst auf
    }

    /**
     * Beschreibung der Methode clone()
     * @return
     */
    public Circle clone() {
        return new Circle(this.x, this.y, this.radius);
    }

    /**
     * Beschreibung der Methode getRadius()
     * @return
     */
    public int getRadius() {
        // Hier Implementierung einfügen
        return this.radius;
    }

    /**
     * Beschreibung der Methode setRadius(int radius)
     * @param radius
     */
    public void setRadius(int radius) {
        // Hier Implementierung einfügen
        if (radius < 0){
            T_Circle.Utils.error("radius darf nicht negativ sein;");
            return;
        }
        this.radius = radius;
    }

    /**
     * Beschreibung der Methode getX()
     * @return
     */
    public int getX() {
        // Hier Implementierung einfügen
        return this.x;
    }

    /**
     * Beschreibung der Methode setX(int x)
     * @param x
     */
    public void setX(int x) {
        // Hier Implementierung einfügen
        this.x = x;
    }

    /**
     * Beschreibung der Methode getY()
     * @return
     */
    public int getY() {
        // Hier Implementierung einfügen
        return this.y;
    }

    /**
     * Beschreibung der Methode setY(int y)
     * @param y
     */
    public void setY(int y) {
        // Hier Implementierung einfügen
        this.y = y;
    }

    /*
     * Grobe Beschreibung der Methode (private, muss nicht ausführlich dokumentiert werden)
     * Zum kontrollieren, ob ein Circle einen anderen enthält
     */
    private boolean containsOne(Circle that) {
        // Hier Implementierung einfügen
        if (T_Circle.Utils.dist(this.x, this.y, that.getX(), that.getY()) + that.getRadius() <= this.radius) {
            //Distanz zwischen den Punkten plus radius des that-Kreises muss kleiner gleich dem radius des this-Kreises sein, sonst ist that nicht im this-Kreis
            return true;
        }
        else return false;
    }

    /**
     * Beschreibung der Methode contains(Circle... others)
     * @param others
     * @return
     */
    public boolean contains(Circle... others) {
        // Hier Implementierung einfügen
        for (Circle circle : others) { //for each-Schleife
            if (!this.containsOne(circle)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Beschreibung der Methode circumscriber(int x, int y, Circle... circles)
     * @param x
     * @param y
     * @param circles
     * @return
     */
    public static Circle circumscriber(int x, int y, Circle... circles) {
        // Hier Implementierung einfügen
        Circle candidate = new Circle(x, y, 0);
        while (!candidate.contains(circles)) {
            candidate.setRadius(candidate.getRadius() + 1);
        }
        return candidate;
    }

    /**
     * Beschreibung der Methode toString()
     * @return
     */
    public String toString() {
        // Hier Implementierung einfügen
        return "(" + this.x + "|" + this.y + "), " + this.radius; //Rückgabetyp: (x|y), radius
    }

    /**
     * Beschreibung der Methode performAction()
     * @param action
     */
    public void performAction(CircleAction action) {
        // Hier Implementierung einfügen
        switch(action){
            case LEFT -> this.x -= 10;
            case RIGHT -> this.x += 10;
            case UP -> this.y -= 10; //Koordinatenachse invertiert
            case DOWN -> this.y += 10;
            case SMALLER -> {
                if (this.radius >= 10) this.radius -= 10; //radius darf nicht negativ sein und soll um 10 verändert werden
            }
            case BIGGER -> this.radius += 10;
            default -> {}
        }
    }
    
}
