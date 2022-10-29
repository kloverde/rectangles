package org.loverde.rectangles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RectangleTest_isAdjacentTo {

    @Test
    public void isAdjacentTo_1_true() {
        final Rectangle r1 = new Rectangle(
            new Point(0, 19),
            new Point(3, 23)
        );

        final Rectangle r2 = new Rectangle(
            new Point(3, 20),
            new Point(4, 22)
        );

        assertTrue(r1.isAdjacentTo(r2));
        assertTrue(r2.isAdjacentTo(r1));
    }

    @Test
    public void isAdjacentTo_2_true() {
        final Rectangle r1 = new Rectangle(
            new Point(5, 20),
            new Point(9, 23)
        );

        final Rectangle r2 = new Rectangle(
            new Point(6, 19),
            new Point(8, 20)
        );

        assertTrue(r1.isAdjacentTo(r2));
        assertTrue(r2.isAdjacentTo(r1));
    }

    @Test
    public void isAdjacentTo_3_true() {
        final Rectangle r1 = new Rectangle(
            new Point(10, 20),
            new Point(11, 22)
        );

        final Rectangle r2 = new Rectangle(
            new Point(11, 19),
            new Point(14, 23)
        );

        assertTrue(r1.isAdjacentTo(r2));
        assertTrue(r2.isAdjacentTo(r1));
    }

    @Test
    public void isAdjacentTo_4_true() {
        final Rectangle r1 = new Rectangle(
            new Point(15, 19),
            new Point(19, 22)
        );

        final Rectangle r2 = new Rectangle(
            new Point(16, 22),
            new Point(18, 23)
        );

        assertTrue(r1.isAdjacentTo(r2));
        assertTrue(r2.isAdjacentTo(r1));
    }

    @Test
    public void isAdjacentTo_5_true() {
        final Rectangle r1 = new Rectangle(
            new Point(20, 19),
            new Point(22, 23)
        );

        final Rectangle r2 = new Rectangle(
            new Point(16, 22),
            new Point(18, 23)
        );

        assertTrue(r1.isAdjacentTo(r2));
        assertTrue(r2.isAdjacentTo(r1));
    }

    @Test
    public void isAdjacentTo_6_true() {
        final Rectangle r1 = new Rectangle(
            new Point(25, 19),
            new Point(28, 21)
        );

        final Rectangle r2 = new Rectangle(
            new Point(25, 21),
            new Point(28, 23)
        );

        assertTrue(r1.isAdjacentTo(r2));
        assertTrue(r2.isAdjacentTo(r1));
    }

    @Test
    public void isAdjacentTo_7_true() {
        final Rectangle r1 = new Rectangle(
            new Point(0, 14),
            new Point(2, 17)
        );

        final Rectangle r2 = new Rectangle(
            new Point(2, 15),
            new Point(4, 18)
        );

        assertTrue(r1.isAdjacentTo(r2));
        assertTrue(r2.isAdjacentTo(r1));
    }

    @Test
    public void isAdjacentTo_8_true() {
        final Rectangle r1 = new Rectangle(
            new Point(5, 15),
            new Point(7, 18)
        );

        final Rectangle r2 = new Rectangle(
            new Point(7, 14),
            new Point(9, 17)
        );

        assertTrue(r1.isAdjacentTo(r2));
        assertTrue(r2.isAdjacentTo(r1));
    }

    @Test
    public void isAdjacentTo_9_true() {
        final Rectangle r1 = new Rectangle(
            new Point(10, 14),
            new Point(13, 16)
        );

        final Rectangle r2 = new Rectangle(
            new Point(11, 16),
            new Point(14, 18)
        );

        assertTrue(r1.isAdjacentTo(r2));
        assertTrue(r2.isAdjacentTo(r1));
    }

    @Test
    public void isAdjacentTo_10_true() {
        final Rectangle r1 = new Rectangle(
            new Point(16, 16),
            new Point(18, 18)
        );

        final Rectangle r2 = new Rectangle(
            new Point(16, 14),
            new Point(19, 16)
        );

        assertTrue(r1.isAdjacentTo(r2));
        assertTrue(r2.isAdjacentTo(r1));
    }

    @Test
    public void isAdjacentTo_11_true() {
        final Rectangle r1 = new Rectangle(
            new Point(20, 14),
            new Point(24, 18)
        );

        assertTrue(r1.isAdjacentTo(r1));
    }

    @Test
    public void isAdjacentTo_12_false() {
        final Rectangle r1 = new Rectangle(
            new Point(0, 9),
            new Point(2, 13)
        );

        final Rectangle r2 = new Rectangle(
            new Point(3, 10),
            new Point(4, 12)
        );

        assertFalse(r1.isAdjacentTo(r2));
        assertFalse(r2.isAdjacentTo(r1));
    }

    @Test
    public void isAdjacentTo_13_false() {
        final Rectangle r1 = new Rectangle(
            new Point(5, 9),
            new Point(7, 13)
        );

        final Rectangle r2 = new Rectangle(
            new Point(3, 10),
            new Point(6, 10)
        );

        assertFalse(r1.isAdjacentTo(r2));
        assertFalse(r2.isAdjacentTo(r1));
    }

    @Test
    public void isAdjacentTo_14_false() {
        final Rectangle r1 = new Rectangle(
            new Point(10, 9),
            new Point(14, 13)
        );

        final Rectangle r2 = new Rectangle(
            new Point(11, 10),
            new Point(13, 12)
        );

        assertFalse(r1.isAdjacentTo(r2));
        assertFalse(r2.isAdjacentTo(r1));
    }

    @Test
    public void isAdjacentTo_15_false() {
        final Rectangle r1 = new Rectangle(
            new Point(15, 11),
            new Point(17, 13)
        );

        final Rectangle r2 = new Rectangle(
            new Point(17, 9),
            new Point(19, 11)
        );

        assertFalse(r1.isAdjacentTo(r2));
        assertFalse(r2.isAdjacentTo(r1));
    }
}
