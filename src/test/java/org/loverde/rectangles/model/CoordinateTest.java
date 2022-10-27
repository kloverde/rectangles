package org.loverde.rectangles.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class CoordinateTest {

    @Test
    public void constructor_fail_xIsNegative() {
        final Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Rectangle(
                new Coordinate(-Double.MIN_VALUE, 0),
                10,
                20
            );
        });

        assertEquals("x must be greater than or equal to 0", ex.getMessage());
    }

    @Test
    public void constructor_fail_yIsNegative() {
        final Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Coordinate(10, -Double.MIN_VALUE);
        });

        assertEquals("y must be greater than or equal to 0", ex.getMessage());
    }

    @Test
    public void constructor_success_xZeroYZero() {
        final Coordinate coord = new Coordinate(0, 0);

        assertEquals(0, coord.getX());
        assertEquals(0, coord.getY());
    }

    @Test
    public void constructor_success_positiveValues() {
        final double x = 10.263;
        final double y = 16.7321;

        final Coordinate coord = new Coordinate(x, y);

        assertEquals(x, coord.getX());
        assertEquals(y, coord.getY());
    }
}
