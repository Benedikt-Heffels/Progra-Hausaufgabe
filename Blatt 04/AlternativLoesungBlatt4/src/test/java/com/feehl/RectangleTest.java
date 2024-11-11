package com.feehl;

import org.junit.jupiter.api.Test;
import  java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    void areSquares() {
        Rectangle[] rs = new Rectangle[100];
        for(int i = 0; i < 100; i++){
            int x = IntStream.range(0, 10000).findFirst().getAsInt();
            int y = IntStream.range(0, 10000).findFirst().getAsInt();
            int sidelength = i;
            rs[i] = new Rectangle(x, y, sidelength);
        }
        assert(Rectangle.areSquares(rs));
    }

    @Test
    void intersectionTestExample() {
        Rectangle r1 = new Rectangle(1,4,2,3);
        Rectangle r2 = new Rectangle(2,5,3,3);
        assertEquals(new Rectangle(2,4,1,2).toString(), Rectangle.intersection(r1, r2).toString());
    }

    @Test
    void intersectionTestExample2() {
        Rectangle r1 = new Rectangle(0,0,1);
        Rectangle r2 = new Rectangle(2,2,1);
        assertEquals(null, Rectangle.intersection(r1, r2));
    }

    @Test
    void intersectionTestExample3() {
        assertEquals(null, Rectangle.intersection());
    }

    @Test
    void testToStringExample() {
        Rectangle r1 = new Rectangle(3,5,3,4);
        assertEquals("(3|5),(3|1),(6|1),(6|5)", r1.toString());
    }

    @Test
    void intersectionTestComparison1() {
        for(int i = 0; i < 1000; i++){

            //Generate Rectangles
            Rectangle[] rs = new Rectangle[2];
            for(int j = 0; j < 2; j++){
                int x = IntStream.range(0, 10000).findFirst().getAsInt();
                int y = IntStream.range(0, 10000).findFirst().getAsInt();
                int width = IntStream.range(0, 10000).findFirst().getAsInt();
                int height = IntStream.range(0, 10000).findFirst().getAsInt();
                rs[j] = new Rectangle(x, y, width, height);
            }
            Rectangle r1 = Rectangle.intersectionBen(rs[0], rs[1]);
            Rectangle reference = Rectangle.intersection(rs[0], rs[1]);
            if(reference == null){
                assertEquals(null, r1);
            }
            else {
                assertEquals(reference.toString(), r1.toString());
            }
        }
    }
}