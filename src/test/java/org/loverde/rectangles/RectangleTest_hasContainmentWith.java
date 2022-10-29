package org.loverde.rectangles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RectangleTest_hasContainmentWith {

    @Test
    public void hasContainmentWith_nullRectangle() {
        final Rectangle r = new Rectangle(
            new Point(2, 17),
            new Point(10, 22)
        );

        final Exception ex = assertThrows(IllegalArgumentException.class, () -> r.hasContainmentWith(null));

        assertEquals("hasContainmentWith:  rectangle cannot be null", ex.getMessage());
    }

    // See inset_rectangles.png set 1.  This has containment.
    @Test
    public void hasContainmentWith_true() {
        final Rectangle r1 = new Rectangle(
            new Point(2, 17),
            new Point(10, 22)
        );

        final Rectangle r2 = new Rectangle(
            new Point(3, 18),
            new Point(9, 21)
        );

        assertTrue(r1.hasContainmentWith(r2));
        assertTrue(r2.hasContainmentWith(r1));
    }

    // See inset_rectangles.png set 2.  No containment.
    @Test
    public void hasContainmentWith_false_sharedEdgeEverywhere() {
        final Rectangle r1 = new Rectangle(
            new Point(12, 17),
            new Point(18, 22)
        );

        assertFalse(r1.hasContainmentWith(r1));
    }

    // See inset_rectangles.png set 3.  No containment.
    @Test
    public void hasContainmentWith_false_sharedEdgeLeft() {
        final Rectangle r1 = new Rectangle(
            new Point(12, 17),
            new Point(18, 22)
        );

        final Rectangle r2 = new Rectangle(
            new Point(20, 17),
            new Point(24, 22)
        );

        assertFalse(r1.hasContainmentWith(r2));
        assertFalse(r2.hasContainmentWith(r1));
    }

    // See inset_rectangles.png set 4.  No containment.
    @Test
    public void hasContainmentWith_false_sharedEdgeTop() {
        final Rectangle r1 = new Rectangle(
            new Point(2, 11),
            new Point(10, 16)
        );

        final Rectangle r2 = new Rectangle(
            new Point(3, 12),
            new Point(9, 16)
        );

        assertFalse(r1.hasContainmentWith(r2));
        assertFalse(r2.hasContainmentWith(r1));
    }

    // See inset_rectangles.png set 5.  No containment.
    @Test
    public void hasContainmentWith_false_sharedEdgeRight() {
        final Rectangle r1 = new Rectangle(
            new Point(12, 11),
            new Point(18, 16)
        );

        final Rectangle r2 = new Rectangle(
            new Point(13, 12),
            new Point(18, 15)
        );

        assertFalse(r1.hasContainmentWith(r2));
        assertFalse(r2.hasContainmentWith(r1));
    }

    // See inset_rectangles.png set 6.  No containment.
    @Test
    public void hasContainmentWith_false_sharedEdgeBottom() {
        final Rectangle r1 = new Rectangle(
            new Point(20, 11),
            new Point(24, 16)
        );

        final Rectangle r2 = new Rectangle(
            new Point(21, 11),
            new Point(23, 15)
        );

        assertFalse(r1.hasContainmentWith(r2));
        assertFalse(r2.hasContainmentWith(r1));
    }

    // See inset_rectangles.png set 7.  No containment.
    @Test
    public void hasContainmentWith_false_completelySeparated() {
        final Rectangle r1 = new Rectangle(
            new Point(2, 4),
            new Point(6, 10)
        );

        final Rectangle r2 = new Rectangle(
            new Point(7, 5),
            new Point(10, 9)
        );

        assertFalse(r1.hasContainmentWith(r2));
        assertFalse(r2.hasContainmentWith(r1));
    }

    // See inset_rectangles.png set 8.  No containment.
    @Test
    public void hasContainmentWith_false_hasIntersection() {
        final Rectangle r1 = new Rectangle(
            new Point(12, 4),
            new Point(16, 10)
        );

        final Rectangle r2 = new Rectangle(
            new Point(15, 6),
            new Point(18, 8)
        );

        assertFalse(r1.hasContainmentWith(r2));
        assertFalse(r2.hasContainmentWith(r1));
    }
}
