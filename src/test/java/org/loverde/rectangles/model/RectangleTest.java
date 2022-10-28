package org.loverde.rectangles.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleTest {

    private Rectangle r1;

    @BeforeEach
    public void setUp() {
        r1 = new Rectangle(
            new Point(4, 2),
            new Point(17, 14)
        );
    }

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

    // See junit_diagram.png, rectangle #1.  The bottom right of the rectangle overlaps with r1.
    @Test
    public void getOverlappingRegion_bottomRightOfSecondRectangleOverlaps() {
        final Rectangle r2 = new Rectangle(
            new Point(1, 11),
            new Point(7, 17)
        );

        final Rectangle iRect = r1.getOverlappingRegion(r2);

        assertEquals(new Point(4, 11), iRect.getLowerLeft());
        assertEquals(new Point(4, 14), iRect.getUpperLeft());
        assertEquals(new Point(7, 14), iRect.getUpperRight());
        assertEquals(new Point(7, 11), iRect.getLowerRight());
    }

    // See junit_diagram.png, rectangle #2.  The bottom left of the rectangle overlaps with r1.
    @Test
    public void getOverlappingRegion_bottomLeftOfSecondRectangleOverlaps() {
        final Rectangle r2 = new Rectangle(
            new Point(13, 11),
            new Point(19, 17)
        );

        final Rectangle iRect = r1.getOverlappingRegion(r2);

        assertEquals(new Point(13, 11), iRect.getLowerLeft());
        assertEquals(new Point(13, 14), iRect.getUpperLeft());
        assertEquals(new Point(17, 14), iRect.getUpperRight());
        assertEquals(new Point(17, 11), iRect.getLowerRight());
    }

    // See junit_diagram.png, rectangle #3.  The entire right side of the rectangle overlaps with r1.
    @Test
    public void getOverlappingRegion_rightSideOfSecondRectangleOverlaps() {
        final Rectangle r2 = new Rectangle(
            new Point(1, 3),
            new Point(7, 8)
        );

        final Rectangle iRect = r1.getOverlappingRegion(r2);

        assertEquals(new Point(4, 3), iRect.getLowerLeft());
        assertEquals(new Point(4, 8), iRect.getUpperLeft());
        assertEquals(new Point(7, 8), iRect.getUpperRight());
        assertEquals(new Point(7, 3), iRect.getLowerRight());
    }

    // See junit_diagram.png, rectangle #4.  The entire left side of the rectangle overlaps with r1.
    @Test
    public void getOverlappingRegion_leftSideOfSecondRectangleOverlaps() {
        final Rectangle r2 = new Rectangle(
            new Point(13, 3),
            new Point(19, 8)
        );

        final Rectangle iRect = r1.getOverlappingRegion(r2);

        assertEquals(new Point(13, 3), iRect.getLowerLeft());
        assertEquals(new Point(13, 8), iRect.getUpperLeft());
        assertEquals(new Point(17, 8), iRect.getUpperRight());
        assertEquals(new Point(17, 3), iRect.getLowerRight());
    }

    // See junit_diagram.png, rectangle #5.  This time, there are four points of intersection.
    @Test
    public void getOverlappingRegion_fromFourPointsOfIntersection1() {
        final Rectangle r2 = new Rectangle(
            new Point(9, 1),
            new Point(11, 15)
        );

        final Rectangle iRect = r1.getOverlappingRegion(r2);

        assertEquals(new Point(9, 2), iRect.getLowerLeft());
        assertEquals(new Point(9, 14), iRect.getUpperLeft());
        assertEquals(new Point(11, 14), iRect.getUpperRight());
        assertEquals(new Point(11, 2), iRect.getLowerRight());
    }

    // See junit_diagram.png, rectangle #6.  This time, there are four points of intersection.
    @Test
    public void getOverlappingRegion_fromFourPointsOfIntersection2() {
        final Rectangle r2 = new Rectangle(
            new Point(4, 9),
            new Point(17, 10)
        );

        final Rectangle iRect = r1.getOverlappingRegion(r2);

        assertEquals(new Point(4, 9), iRect.getLowerLeft());
        assertEquals(new Point(4, 10), iRect.getUpperLeft());
        assertEquals(new Point(17, 10), iRect.getUpperRight());
        assertEquals(new Point(17, 9), iRect.getLowerRight());
    }
}
