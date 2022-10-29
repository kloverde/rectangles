package org.loverde.rectangles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RectangleTest_containment {

    // These variables define the rectangles seen in containment.png, named according to the rectangle set and color.
    final Rectangle
        r1Black = new Rectangle(new Point(2, 17), new Point(10, 22)),
        r1Red   = new Rectangle(new Point(3, 18), new Point(9, 21)),
        r2Black = new Rectangle(new Point(12, 17), new Point(18, 22)),
        r2Red   = new Rectangle(
                    new Point(r2Black.getLowerLeft().getX(), r2Black.getLowerLeft().getY()),
                    new Point(r2Black.getUpperRight().getX(), r2Black.getUpperRight().getY())
                  ),
        r3Black = new Rectangle(new Point(20, 17), new Point(24, 22)),
        r3Red   = new Rectangle(new Point(20, 18), new Point(23, 21)),
        r4Black = new Rectangle(new Point(2, 11), new Point(10, 16)),
        r4Red   = new Rectangle(new Point(3, 12), new Point(9, 16)),
        r5Black = new Rectangle(new Point(12, 11), new Point(18, 16)),
        r5Red   = new Rectangle(new Point(13, 12), new Point(18, 15)),
        r6Black = new Rectangle(new Point(20, 11), new Point(24, 16)),
        r6Red   = new Rectangle(new Point(21, 11), new Point(23, 15)),
        r7Black = new Rectangle(new Point(2, 4), new Point(6, 10)),
        r7Red   = new Rectangle(new Point(7, 5), new Point(10, 9)),
        r8Black = new Rectangle(new Point(12, 4), new Point(16, 10)),
        r8Red   = new Rectangle(new Point(15, 6), new Point(18, 8));

    //
    // CONTAINS
    //

    @Test
    public void contains_nullRectangle() {
        final Rectangle r = new Rectangle(
            new Point(2, 17),
            new Point(10, 22)
        );

        final Exception ex = assertThrows(IllegalArgumentException.class, () -> r.contains(null));

        assertEquals("rectangle r2 cannot be null", ex.getMessage());
    }

    @Test
    public void contains_1_true() {
        assertTrue(r1Black.contains(r1Red));
        assertFalse(r1Red.contains(r1Black));
    }

    @Test
    public void contains_2_false_allEdgesShared() {
        assertFalse(r2Black.hasContainmentWith(r2Red));
        assertFalse(r2Red.contains(r2Black));
    }

    @Test
    public void contains_3_false_leftEdgeShared() {
        assertFalse(r3Black.contains(r3Red));
        assertFalse(r3Red.contains(r3Black));
    }

    @Test
    public void contains_4_false_topEdgeShared() {
        assertFalse(r4Black.contains(r4Red));
        assertFalse(r4Red.contains(r4Black));
    }

    @Test
    public void contains_5_false_rightEdgeShared() {
        assertFalse(r5Black.contains(r5Red));
        assertFalse(r5Red.contains(r5Black));
    }

    @Test
    public void contains_6_false_bottomEdgeShared() {
        assertFalse(r6Black.contains(r6Red));
        assertFalse(r6Red.contains(r6Black));
    }

    @Test
    public void contains_7_false_completelySeparated() {
        assertFalse(r7Black.contains(r7Red));
        assertFalse(r7Red.contains(r7Black));
    }

    @Test
    public void contains_8_false_hasIntersection() {
        assertFalse(r8Black.contains(r8Red));
        assertFalse(r8Red.contains(r8Black));
    }

    //
    // IS CONTAINED BY
    //

    @Test
    public void isContainedBy_nullRectangle() {
        final Rectangle r = new Rectangle(
            new Point(2, 17),
            new Point(10, 22)
        );

        final Exception ex = assertThrows(IllegalArgumentException.class, () -> r.isContainedBy(null));

        assertEquals("rectangle r2 cannot be null", ex.getMessage());
    }

    @Test
    public void isContainedBy_1_true() {
        assertFalse(r1Black.isContainedBy(r1Red));
        assertTrue(r1Red.isContainedBy(r1Black));
    }

    @Test
    public void isContainedBy_2_false_allEdgesShared() {
        assertFalse(r2Black.isContainedBy(r2Red));
        assertFalse(r2Red.isContainedBy(r2Black));
    }

    @Test
    public void isContainedBy_3_false_leftEdgeShared() {
        assertFalse(r3Black.isContainedBy(r3Red));
        assertFalse(r3Red.isContainedBy(r3Black));
    }

    @Test
    public void isContainedBy_4_false_topEdgeShared() {
        assertFalse(r4Black.isContainedBy(r4Red));
        assertFalse(r4Red.isContainedBy(r4Black));
    }

    @Test
    public void isContainedBy_5_false_rightEdgeShared() {
        assertFalse(r5Black.isContainedBy(r5Red));
        assertFalse(r5Red.isContainedBy(r5Black));
    }

    @Test
    public void isContainedBy_6_false_bottomEdgeShared() {
        assertFalse(r6Black.isContainedBy(r6Red));
        assertFalse(r6Red.isContainedBy(r6Black));
    }

    @Test
    public void isContainedBy_7_false_completelySeparated() {
        assertFalse(r7Black.isContainedBy(r7Red));
        assertFalse(r7Red.isContainedBy(r7Black));
    }

    @Test
    public void isContainedBy_8_false_hasIntersection() {
        assertFalse(r8Black.isContainedBy(r8Red));
        assertFalse(r8Red.isContainedBy(r8Black));
    }

    //
    // HAS CONTAINMENT WITH
    //

    @Test
    public void hasContainmentWith_nullRectangle() {
        final Rectangle r = new Rectangle(
            new Point(2, 17),
            new Point(10, 22)
        );

        final Exception ex = assertThrows(IllegalArgumentException.class, () -> r.hasContainmentWith(null));

        assertEquals("rectangle r2 cannot be null", ex.getMessage());
    }

    @Test
    public void hasContainmentWith_1_true() {
        assertTrue(r1Black.hasContainmentWith(r1Red));
        assertTrue(r1Red.hasContainmentWith(r1Black));
    }

    @Test
    public void hasContainmentWith_2_false_allEdgesShared() {
        assertFalse(r1Black.hasContainmentWith(r2Red));
    }

    @Test
    public void hasContainmentWith_3_false_leftEdgeShared() {
        assertFalse(r3Black.hasContainmentWith(r3Red));
        assertFalse(r3Red.hasContainmentWith(r3Black));
    }

    @Test
    public void hasContainmentWith_4_false_topEdgeShared() {
        assertFalse(r4Black.hasContainmentWith(r4Red));
        assertFalse(r4Red.hasContainmentWith(r4Black));
    }

    @Test
    public void hasContainmentWith_5_false_rightEdgeShared() {
        assertFalse(r5Black.hasContainmentWith(r5Red));
        assertFalse(r5Red.hasContainmentWith(r5Black));
    }

    @Test
    public void hasContainmentWith_6_false_bottomEdgeShared() {
        assertFalse(r6Black.hasContainmentWith(r6Red));
        assertFalse(r6Red.hasContainmentWith(r6Black));
    }

    @Test
    public void hasContainmentWith_7_false_completelySeparated() {
        assertFalse(r7Black.hasContainmentWith(r7Red));
        assertFalse(r7Red.hasContainmentWith(r7Black));
    }

    @Test
    public void hasContainmentWith_8_false_hasIntersection() {
        assertFalse(r8Black.hasContainmentWith(r8Red));
        assertFalse(r8Red.hasContainmentWith(r8Black));
    }
}
