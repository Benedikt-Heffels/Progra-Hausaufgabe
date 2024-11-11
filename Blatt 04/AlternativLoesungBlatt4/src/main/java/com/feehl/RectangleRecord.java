package com.feehl;

public record RectangleRecord(int x, int y, int width, int height) {

    public RectangleRecord copy(){
        return new RectangleRecord(this.x, this.y, this.width, this.height);
    }

    /**
     * Checks whether the given RectangleRecords are all Squares
     * @param RectangleRecords VARARG array of RectangleRecords to check
     * @return true if all RectangleRecords are Squares, otherwise false
     */
    public static boolean areSquares(RectangleRecord... RectangleRecords){
        for(RectangleRecord RectangleRecord : RectangleRecords){
            if(RectangleRecord.width() != RectangleRecord.height()){
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
     * returns the largest possible RectangleRecord that lies in the intersection of every RectangleRecord
     * @param RectangleRecords VARARG array of RectangleRecords to intersect
     * @return returns the intersection RectangleRecord, returns null if the intersection area is empty or there are no given RectangleRecords as parameters
     */
    public static RectangleRecord intersection(RectangleRecord... RectangleRecords){
        if(RectangleRecords.length == 0){
            return null;
        }
        RectangleRecord intersection = RectangleRecords[0];
        for(RectangleRecord RectangleRecord : RectangleRecords){
            if(intersectTwo(intersection, RectangleRecord) == null) return null;
            intersection = intersectTwo(intersection, RectangleRecord);
        }
        return intersection;
    }

    private static RectangleRecord intersectTwo(RectangleRecord r1, RectangleRecord r2){

        int x = Utils.max(r1.x(), r2.x());
        int y = Utils.min(r1.y(), r2.y());
        int dlCornerY = Utils.max(r1.y() - r1.height(), r2.y() - r2.height());
        int height = Utils.max(dlCornerY, y) - Utils.min(dlCornerY, y);
        int dlCornerX = Utils.min(r1.x() + r1.width(), r2.x() + r2.width());
        int width = Utils.max(dlCornerX, x) - Utils.min(dlCornerX, x);

        if(!RectangleRecord.containsPoint(r1, x, y) || !RectangleRecord.containsPoint(r2, x, y)){
            return null;
        }

        return new RectangleRecord(
                x,
                y,
                width,
                height
        );
    }


    //returns whether the RectangleRecord r contains the Coordinate Point (x|y)
    private static boolean containsPoint(RectangleRecord r, int x, int y){
        if((r.x() <= x) && (r.x() + r.width() >= x) && (r.y() >= y) && (r.y() - r.width() <= y)){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + x + "|" + y + "),(" + x + "|" + (y-height) + "),(" + (x+width) + "|" + (y-height) + "),(" + (x+width) + "|" + y + ")";
    }




}
