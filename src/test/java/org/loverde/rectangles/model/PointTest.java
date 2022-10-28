package org.loverde.rectangles.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class PointTest {

    @Test
    public void constructor_fail_xIsNegative() {
        final Exception ex = assertThrows(IllegalArgumentException.class, () -> new Point(-Double.MIN_VALUE, 0));

        assertEquals("x must be greater than or equal to 0", ex.getMessage());
    }

    @Test
    public void constructor_fail_yIsNegative() {
        final Exception ex = assertThrows(IllegalArgumentException.class, () -> new Point(10, -Double.MIN_VALUE));

        assertEquals("y must be greater than or equal to 0", ex.getMessage());
    }

    @Test
    public void constructor_success_xZeroYZero() {
        final Point p = new Point(0, 0);

        assertEquals(0, p.getX());
        assertEquals(0, p.getY());
    }

    @Test
    public void constructor_success_positiveValues() {
        final double x = 10.263;
        final double y = 16.7321;

        final Point p = new Point(x, y);

        assertEquals(x, p.getX());
        assertEquals(y, p.getY());
    }
}
