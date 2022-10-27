package org.loverde.rectangles.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RectangleTest {

    @Test
    public void constructor_fail_widthIsNegative() {
        final Exception ex = assertThrows(IllegalArgumentException.class, () -> new Rectangle(
            new Coordinate(0,0),
            -Double.MIN_VALUE,
            20
        ));

        assertEquals("width must be greater than 0", ex.getMessage());
    }

    @Test
    public void constructor_fail_widthIsZero() {
        final Exception ex = assertThrows(IllegalArgumentException.class, () -> new Rectangle(
            new Coordinate(0,0),
            0,
            20
        ));

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
    public void testGetters_allFieldsHaveMinValues() {
        final Rectangle rect = new Rectangle(
            new Coordinate(0,0),
            Double.MIN_VALUE,
            Double.MIN_VALUE
        );

        assertEquals(0, rect.getX1());
        assertEquals(Double.MIN_VALUE, rect.getX2(), 0.0);
        assertEquals(0, rect.getY1());
        assertEquals(Double.MIN_VALUE, rect.getY2(), 0.0);
        assertEquals(Double.MIN_VALUE, rect.getWidth());
        assertEquals(Double.MIN_VALUE, rect.getHeight());
    }

    // Yes, the getters were already tested in minValuesForEverything, but everything had the same values
    @Test
    public void testGetters_allFieldsHaveDifferentValues() {
        final Rectangle rect = new Rectangle(
            new Coordinate(10,15),
            20,
            25
        );

        assertEquals(10, rect.getX1());
        assertEquals(30, rect.getX2());
        assertEquals(15, rect.getY1());
        assertEquals(40, rect.getY2());
    }

    @Test
    public void getCoordinates() {
        final Rectangle rect = new Rectangle(
            new Coordinate(10,15),
            20,
            25
        );

        assertArrayEquals(new Coordinate[]{
            new Coordinate(10, 15),
            new Coordinate(10, 40),
            new Coordinate(30, 40),
            new Coordinate(30, 15),
        }, rect.getCoordinates());
    }
}
