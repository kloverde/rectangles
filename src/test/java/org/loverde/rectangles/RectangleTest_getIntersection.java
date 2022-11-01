package org.loverde.rectangles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class RectangleTest_getIntersection {

    private Rectangle r1;

    @BeforeEach
    public void setUp() {
        r1 = new Rectangle(
            new Point(4, 2),
            new Point(17, 14)
        );
    }

    @Test
    public void getIntersection_nullRectangle() {
        final Exception e = assertThrows(IllegalArgumentException.class, () -> r1.getIntersection(null));
        assertEquals("getIntersection:  rectangle cannot be null", e.getMessage());
    }

    // See intersection.png, rectangle #1.  The bottom right of the rectangle overlaps with r1.
    @Test
    public void getIntersection_bottomRightOfSecondRectangleOverlaps() {
        final Rectangle r2 = new Rectangle(
            new Point(1, 11),
            new Point(7, 17)
        );

        final Rectangle iRect = r1.getIntersection(r2);

        assertEquals(new Point(4, 11), iRect.getLowerLeft());
        assertEquals(new Point(4, 14), iRect.getUpperLeft());
        assertEquals(new Point(7, 14), iRect.getUpperRight());
        assertEquals(new Point(7, 11), iRect.getLowerRight());
    }

    // See intersection.png, rectangle #2.  The bottom left of the rectangle overlaps with r1.
    @Test
    public void getIntersection_bottomLeftOfSecondRectangleOverlaps() {
        final Rectangle r2 = new Rectangle(
            new Point(13, 11),
            new Point(19, 17)
        );

        final Rectangle iRect = r1.getIntersection(r2);

        assertEquals(new Point(13, 11), iRect.getLowerLeft());
        assertEquals(new Point(13, 14), iRect.getUpperLeft());
        assertEquals(new Point(17, 14), iRect.getUpperRight());
        assertEquals(new Point(17, 11), iRect.getLowerRight());
    }

    // See intersection.png, rectangle #3.  The entire right side of the rectangle overlaps with r1.
    @Test
    public void getIntersection_rightSideOfSecondRectangleOverlaps() {
        final Rectangle r2 = new Rectangle(
            new Point(1, 3),
            new Point(7, 8)
        );

        final Rectangle iRect = r1.getIntersection(r2);

        assertEquals(new Point(4, 3), iRect.getLowerLeft());
        assertEquals(new Point(4, 8), iRect.getUpperLeft());
        assertEquals(new Point(7, 8), iRect.getUpperRight());
        assertEquals(new Point(7, 3), iRect.getLowerRight());
    }

    // See intersection.png, rectangle #4.  The entire left side of the rectangle overlaps with r1.
    @Test
    public void getIntersection_leftSideOfSecondRectangleOverlaps() {
        final Rectangle r2 = new Rectangle(
            new Point(13, 3),
            new Point(19, 8)
        );

        final Rectangle iRect = r1.getIntersection(r2);

        assertEquals(new Point(13, 3), iRect.getLowerLeft());
        assertEquals(new Point(13, 8), iRect.getUpperLeft());
        assertEquals(new Point(17, 8), iRect.getUpperRight());
        assertEquals(new Point(17, 3), iRect.getLowerRight());
    }

    // See intersection.png, rectangle #5.  This time, there are four points of intersection.
    @Test
    public void getIntersection_fromFourPointsOfIntersection1() {
        final Rectangle r2 = new Rectangle(
            new Point(9, 1),
            new Point(11, 15)
        );

        final Rectangle iRect = r1.getIntersection(r2);

        assertEquals(new Point(9, 2), iRect.getLowerLeft());
        assertEquals(new Point(9, 14), iRect.getUpperLeft());
        assertEquals(new Point(11, 14), iRect.getUpperRight());
        assertEquals(new Point(11, 2), iRect.getLowerRight());
    }

    // See intersection.png, rectangle #6.  This time, there are four points of intersection.
    @Test
    public void getIntersection_fromFourPointsOfIntersection2() {
        final Rectangle r2 = new Rectangle(
            new Point(4, 9),
            new Point(17, 10)
        );

        final Rectangle iRect = r1.getIntersection(r2);

        assertEquals(new Point(4, 9), iRect.getLowerLeft());
        assertEquals(new Point(4, 10), iRect.getUpperLeft());
        assertEquals(new Point(17, 10), iRect.getUpperRight());
        assertEquals(new Point(17, 9), iRect.getLowerRight());
    }

    // See intersection.png, rectangle #7.  There are no overlapping regions.
    @Test
    public void getIntersection_noIntersection() {
        final Rectangle r2 = new Rectangle(
            new Point(5,1),
            new Point(8, 2)
        );

        assertNull(r1.getIntersection(r2));
    }

    // See intersection.png, rectangle #8.  There are no overlapping regions.
    @Test
    public void getIntersection_noIntersection2() {
        final Rectangle r2 = new Rectangle(
            new Point(5,0),
            new Point(8, 1)
        );

        assertNull(r1.getIntersection(r2));
    }

    // See intersection.png, rectangle #9
    @Test
    public void getIntersection_9_bottomHalf() {
        final Rectangle r1 = new Rectangle(new Point(8, 18), new Point(10, 20));   // black
        final Rectangle r2 = new Rectangle(new Point(8, 17), new Point(10, 19));  // orange

        final Rectangle expectedIntersection = new Rectangle(new Point(8, 18), new Point(10, 19));

        assertEquals(expectedIntersection, r1.getIntersection(r2));
        assertEquals(expectedIntersection, r2.getIntersection(r1));
    }

    // See intersection.png, rectangle #10
    @Test
    public void getIntersection_10_topHalf() {
        final Rectangle r1 = new Rectangle(new Point(11, 18), new Point(13, 20));  // orange
        final Rectangle r2 = new Rectangle(new Point(11, 17), new Point(13, 19));   // black

        final Rectangle expectedIntersection = new Rectangle(new Point(11, 18), new Point(13, 19));

        assertEquals(expectedIntersection, r1.getIntersection(r2));
        assertEquals(expectedIntersection, r2.getIntersection(r1));
    }

    // See intersection.png, rectangle #11
    @Test
    public void getIntersection_11_upperRightCorner() {
        final Rectangle r1 = new Rectangle(new Point(1, 3), new Point(7, 8));  // red
        final Rectangle r2 = new Rectangle(new Point(0, 2), new Point(2, 4));   // blue

        final Rectangle expectedIntersection = new Rectangle(new Point(1, 3), new Point(2, 4));

        assertEquals(expectedIntersection, r1.getIntersection(r2));
        assertEquals(expectedIntersection, r2.getIntersection(r1));
    }

    // See intersection.png, rectangle #12
    @Test
    public void getIntersection_12_upperLeftCorner() {
        final Rectangle r1 = new Rectangle(new Point(13, 3), new Point(19, 8));  // red
        final Rectangle r2 = new Rectangle(new Point(18, 2), new Point(20, 4));   // blue

        final Rectangle expectedIntersection = new Rectangle(new Point(18, 3), new Point(19, 4));

        assertEquals(expectedIntersection, r1.getIntersection(r2));
        assertEquals(expectedIntersection, r2.getIntersection(r1));
    }

    // If two identical rectangles are perfectly overlaid, they are regarded as overlapping
    @Test
    public void getIntersection_perfectlyOverlapped() {
        assertEquals(r1, r1.getIntersection(r1));
    }
}
