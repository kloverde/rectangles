package org.loverde.rectangles.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleTest {

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
}
