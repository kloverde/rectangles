package org.loverde.rectangles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class RectangleTest {

    final Rectangle r = new Rectangle(new Point(5, 20), new Point(10, 30));

    @Test
    public void constructor_fail_nullLowerLeft() {
        final Exception ex = assertThrows(IllegalArgumentException.class, () -> new Rectangle(
            null,
            new Point(17, 14)
        ));

        assertEquals("lowerLeft cannot be null", ex.getMessage());
    }

    @Test
    public void constructor_fail_nullUpperRight() {
        final Exception ex = assertThrows(IllegalArgumentException.class, () -> new Rectangle(
            new Point(4, 2),
            null
        ));

        assertEquals("upperRight cannot be null", ex.getMessage());
    }

    @Test
    public void constructor_fail_upperRightIsLeftOfOrigin() {
        final Exception ex = assertThrows(IllegalArgumentException.class, () -> new Rectangle(
            new Point(4, 2),
            new Point(3.999, 10)
        ));

        assertEquals("upperRight.x must be greater than lowerLeft.x", ex.getMessage());
    }

    @Test
    public void constructor_fail_upperRightIsBelowOrigin() {
        final Exception ex = assertThrows(IllegalArgumentException.class, () -> new Rectangle(
            new Point(4, 2),
            new Point(10, 1.999)
        ));

        assertEquals("upperRight.y must be greater than lowerLeft.y", ex.getMessage());
    }

    @Test
    public void constructor_fail_zeroArea1() {
        final Exception ex = assertThrows(IllegalArgumentException.class, () -> new Rectangle(
            new Point(4, 2),
            new Point(4, 10)
        ));

        assertEquals("upperRight.x must be greater than lowerLeft.x", ex.getMessage());
    }

    @Test
    public void constructor_fail_zeroArea2() {
        final Exception ex = assertThrows(IllegalArgumentException.class, () -> new Rectangle(
            new Point(4, 2),
            new Point(4, 10)
        ));

        assertEquals("upperRight.x must be greater than lowerLeft.x", ex.getMessage());
    }

    @Test
    public void constructor_success() {
        final Point lowerLeft = new Point(10,15);
        final Point upperLeft = new Point(10, 30);
        final Point upperRight = new Point(20, 30);
        final Point lowerRight = new Point(20, 15);

        final Rectangle r = new Rectangle(lowerLeft, upperRight);

        assertEquals(lowerLeft, r.getLowerLeft());
        assertNotSame(lowerLeft, r.getLowerLeft());

        assertEquals(upperLeft, r.getUpperLeft());
        assertNotSame(upperLeft, r.getUpperLeft());

        assertEquals(upperRight, r.getUpperRight());
        assertNotSame(upperRight, r.getUpperRight());

        assertEquals(lowerRight, r.getLowerRight());
        assertNotSame(lowerRight, r.getLowerRight());
    }

    @Test
    public void getWidth() {
        assertEquals(5, r.getWidth());
    }

    @Test
    public void getHeight() {
        assertEquals(10, r.getHeight());
    }

    @Test
    public void getLeftX() {
        assertEquals(5, r.getLeftX());
    }

    @Test
    public void getRightX() {
        assertEquals(10, r.getRightX());
    }

    @Test
    public void getBottomY() {
        assertEquals(20, r.getBottomY());
    }

    @Test
    public void getTopY() {
        assertEquals(30, r.getTopY());
    }
}
