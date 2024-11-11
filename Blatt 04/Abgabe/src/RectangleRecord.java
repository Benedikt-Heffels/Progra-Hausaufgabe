public record RectangleRecord(int x, int y, int width, int height) {

    /**
     * Constructor creating new Rectangle and setting coordinates of upper left corner as well as width and height
     * @param x x-coordinate of upper left corner of the Rectangle
     * @param y y-coordinate of upper left corner of the Rectangle
     * @param width not-negative width of the Rectangle
     * @param height not-negative height of the Rectangle
     */
    public RectangleRecord(int x, int y, int width, int height){
        if(width < 0 || height < 0) {
            Utils.error("Error: Höhe und Breite duerfen nicht negativ sein!");
            this.x = 0;
            this.y = 0;
            this.width = 0;
            this.height = 0;
        }
        else {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

    /**
     * Copies an existing rectangle as a new instance, by creating a new rectangle with the same attribute values
     * @param toCopy Rectangle create a copy of
     * @return The copied Rectangle
     */

    public static RectangleRecord copy(RectangleRecord toCopy){
        return new RectangleRecord(toCopy.x, toCopy.y, toCopy.width, toCopy.height);
    }

    /**
     * Static method that checks whether the given rectangles are all Squares
     * @param rectangleRecords var arg array for any number of Rectangles to check
     * @return true if all Rectangles are Squares, otherwise false
     */
    public static boolean areSquares(RectangleRecord... rectangleRecords){
        for(RectangleRecord rectangleRecord : rectangleRecords){
            if(rectangleRecord.height != rectangleRecord.width){
                return false;
            }
        }
        return true;
    }

    /**
     * returns the area of the RectangleRecord it is called on
     * @return area in unit of coordinate system squared
     */
    public int area(){
        return width*height;
    }

    /**
     * Static method that returns the largest possible RectangleRecord that lies in the intersection of every rectangle
     * @param rectangleRecords VARARG array for any number of RectangleRecords to intersect
     * @return returns the intersection RectangleRecord, returns null if the intersection area is empty or there are no given RectangleRecords as parameters
     */
    public static RectangleRecord intersection(RectangleRecord... rectangleRecords){
        if(rectangleRecords.length == 0){
            return null;
        }
        RectangleRecord intersection = rectangleRecords[0];
        for(RectangleRecord rectangleRecord : rectangleRecords){
            intersection = intersectTwo(intersection, rectangleRecord);
            if(intersection == null) return null;
        }
        return intersection;
    }

    /**
     * Private method that checks whether the RectangleRecord r contains the Coordinate Point (x|y)
     * @param r RectangleRecord
     * @param x x-Coordinate of the point
     * @param y y-Coordinate of the point
     * @return ture, if point lies in rectangle, else false
     */
    private static boolean containsPoint(RectangleRecord r, int x, int y){
        if((r.x <= x) && (r.x + r.width >= x) && (r.y >= y) && (r.y - r.height <= y)){
            return true;
        }
        return false;
    }

    /**
     * Static method that returns the intersection of two RectangleRecords
     * @param r1 RectangleRecord 1
     * @param r2 RectangleRecord 2
     * @return returns the intersection RectangleRecord, returns null if the intersection area is empty or there are no given RectangleRecords as parameters
     */
    private static RectangleRecord intersectTwo(RectangleRecord r1, RectangleRecord r2) {

        int x = Utils.max(r1.x, r2.x);
        int y = Utils.min(r1.y, r2.y);
        int dlCornerY = Utils.max(r1.y - r1.height, r2.y - r2.height);
        int height = Utils.max(dlCornerY, y) - Utils.min(dlCornerY, y);
        int dlCornerX = Utils.min(r1.x + r1.width, r2.x + r2.width);
        int width = Utils.max(dlCornerX, x) - Utils.min(dlCornerX, x);

        if (!RectangleRecord.containsPoint(r1, x, y) || !RectangleRecord.containsPoint(r2, x, y)) {
            return null;
        }

        return new RectangleRecord(x, y, width, height);
    }

//    @Override
//    /**
//     * Returns the coordinates of all corners of the Rectangle anticlockwise, beginning with the upper left corner
//     * @return returns coordinates of all four corners in the pattern (x|y),...
//     */
//    public String toString() {
//        return "(" + x + "|" + y + "),(" + x + "|" + (y-height) + "),(" + (x+width) + "|" + (y-height) + "),(" + (x+width) + "|" + y + ")";
//    }
}
