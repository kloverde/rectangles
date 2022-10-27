package org.loverde.rectangles.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RectangleTest {

    @Test
    public void constructor_fail_widthIsNegative() {
        final Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Rectangle(
                new Coordinate(0,0),
                -Double.MIN_VALUE,
                20
            );
        });

        assertEquals("width must be greater than 0", ex.getMessage());
    }

    @Test
    public void constructor_fail_widthIsZero() {
        final Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Rectangle(
                new Coordinate(0,0),
                0,
                20
            );
        });

        assertEquals("width must be greater than 0", ex.getMessage());
    }

    @Test
    public void constructor_fail_heightIsNegative() {
        final Rectangle rect = new Rectangle(
            new Coordinate(0,0),
            10,
            20
        );

        assertEquals(0, rect.getX1());
    }

    @Test
    public void constructor_success_minValuesForEverything() {
        final Rectangle rect = new Rectangle(
            new Coordinate(0,0),
            Double.MIN_VALUE,
            Double.MIN_VALUE
        );

        assertEquals(0, rect.getX1());
        assertEquals(Double.MIN_VALUE, rect.getX2(), 0.0);
        assertEquals(0, rect.getY1());
        assertEquals(Double.MIN_VALUE, rect.getY2(), 0.0);
    }
}
