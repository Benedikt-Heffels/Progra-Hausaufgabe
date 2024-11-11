public class Rectangle_Tester {
    public static void main(String[] args) {
        //Testet die Rectangle-Methoden
        Rectangle r = new Rectangle(5, 5, 10, 5);
        Rectangle r2 = new Rectangle(10, 5, 10, 5);
        Rectangle r3 = new Rectangle(10, 5, 5, 10);
        Rectangle r4 = new Rectangle(10, 5, 2, 2);
        //Rectangle r5 = new Rectangle(5, 10, 20, 30);
        Rectangle square1 = new Rectangle(5,0,10,10);
        Rectangle square2 = new Rectangle(5,0,20);

        Rectangle intersected = Rectangle.intersection(r,r2,r3,r4);
        System.out.println("Ergebnis: " + intersected);
    }
}