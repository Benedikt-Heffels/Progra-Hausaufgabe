package com.feehl;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    //random number generator
    private SecureRandom rand = new SecureRandom();

    //Helper
    private int rnd(){
        return rand.nextInt(-10, 10);
    }

    private int rndPositive(){
        return rand.nextInt(0,10);
    }

    //copy test
    @Test
    void copyTest(){
        Rectangle r = new Rectangle(rnd(), rnd(), rndPositive(), rndPositive());
        assertEquals(r, Rectangle.copy(r));
    }

    //Square Test
    @Test
    void areSquares() {
        Rectangle[] rs = new Rectangle[100];
        for(int i = 0; i < 100; i++){
            int x = rand.nextInt(100);
            int y = rand.nextInt(100);
            int sidelength = i;
            rs[i] = new Rectangle(x, y, sidelength);
        }
        assert(Rectangle.areSquares(rs));
    }


    //Intersection
    @Test
    void intersectionTestExample() {
        Rectangle r1 = new Rectangle(1,4,2,3);
        Rectangle r2 = new Rectangle(2,5,3,3);
        assertEquals(new Rectangle(2,4,1,2), Rectangle.intersection(r1, r2));
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
                int x = rnd();
                int y = rnd();
                int width = rndPositive();
                int height = rndPositive();
                rs[j] = new Rectangle(x, y, width, height);
            }

            Rectangle reference = Rectangle.intersectionOskar(rs[0], rs[1]);
            Rectangle r1 = Rectangle.intersection(rs[0], rs[1]);

            assertEquals(reference, r1, "Rectangles where: " + rs[0] + " and " + rs[1] + "\n(" + rs[0].toString_() + " and " + rs[1].toString_() + ")\n");

        }
    }

    @Test
    void intersectionTestExample4() {
        Rectangle r1 = new Rectangle(8,-1,6,9);
        Rectangle r2 = new Rectangle(6,-8,3,0);
        assertEquals(Rectangle.intersectionOskar(r1,r2), Rectangle.intersection(r1, r2), "Rectangles were: " + r1 + " and " + r2 + "\n(" + r1.toString_() + " and " + r2.toString_() + ")\n");
    }


}