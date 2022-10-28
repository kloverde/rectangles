package org.loverde.rectangles;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleTest_getIntersectionsWith {

    private final Rectangle r1 = new Rectangle(
        new Point(4, 2),
        new Point(17, 14)
    );

    @Test
    public void getIntersectionsWith_nullRectangle() {
        final Exception e = assertThrows(IllegalArgumentException.class, () -> r1.getIntersectionsWith(null));
        assertEquals("getIntersectionsWith:  rectangle cannot be null", e.getMessage());
    }

    // See intersections.png #1
    @Test
    public void getIntersectionsWith_1() {
        final Rectangle r2 = new Rectangle(
            new Point(1, 11),
            new Point(7, 17)
        );

        final Set<Point> expectedIntersections = new HashSet<>();
        expectedIntersections.add(new Point(4, 11));
        expectedIntersections.add(new Point(7, 14));

        final Set<Point> actualIntersections = r1.getIntersectionsWith(r2);

        assertEquals(expectedIntersections, actualIntersections);
    }

    // See intersections.png #2
    @Test
    public void getIntersectionsWith_2() {
        final Rectangle r2 = new Rectangle(
            new Point(13, 11),
            new Point(19, 17)
        );

        final Set<Point> expectedIntersections = new HashSet<>();
        expectedIntersections.add(new Point(13, 14));
        expectedIntersections.add(new Point(17, 11));

        final Set<Point> actualIntersections = r1.getIntersectionsWith(r2);

        assertEquals(expectedIntersections, actualIntersections);
    }

    // See intersections.png #3
    @Test
    public void getIntersectionsWith_3() {
        final Rectangle r2 = new Rectangle(
            new Point(1, 3),
            new Point(7, 8)
        );

        final Set<Point> expectedIntersections = new HashSet<>();
        expectedIntersections.add(new Point(4, 3));
        expectedIntersections.add(new Point(4, 8));

        final Set<Point> actualIntersections = r1.getIntersectionsWith(r2);

        assertEquals(expectedIntersections, actualIntersections);
    }

    // See intersections.png #4
    @Test
    public void getIntersectionsWith_4() {
        final Rectangle r2 = new Rectangle(
            new Point(13, 3),
            new Point(19, 8)
        );

        final Set<Point> expectedIntersections = new HashSet<>();
        expectedIntersections.add(new Point(17, 3));
        expectedIntersections.add(new Point(17, 8));

        final Set<Point> actualIntersections = r1.getIntersectionsWith(r2);

        assertEquals(expectedIntersections, actualIntersections);
    }

    // See intersections.png #5
    @Test
    public void getIntersectionsWith_5() {
        final Rectangle r2 = new Rectangle(
            new Point(9, 1),
            new Point(11, 15)
        );

        final Set<Point> expectedIntersections = new HashSet<>();
        expectedIntersections.add(new Point(9, 2));
        expectedIntersections.add(new Point(11, 2));
        expectedIntersections.add(new Point(9, 14));
        expectedIntersections.add(new Point(11, 14));

        final Set<Point> actualIntersections = r1.getIntersectionsWith(r2);

        assertEquals(expectedIntersections, actualIntersections);
    }

    // See intersections.png #6
    @Test
    public void getIntersectionsWith_6() {
        final Rectangle r2 = new Rectangle(
            new Point(2, 9),
            new Point(18, 10)
        );

        final Set<Point> expectedIntersections = new HashSet<>();
        expectedIntersections.add(new Point(4, 9));
        expectedIntersections.add(new Point(4, 10));
        expectedIntersections.add(new Point(17, 9));
        expectedIntersections.add(new Point(11, 10));

        final Set<Point> actualIntersections = r1.getIntersectionsWith(r2);

        assertEquals(expectedIntersections, actualIntersections);
    }

    // See intersections.png #7.
    // The requirements don't specify whether a shared edge is considered an intersection, but I've determined
    // that it's not.  This is because the number of intersecting points would be infinite.  Even if you're
    // talking about x = 1.01 to x = 1.001, there's an infinite number of points in between these values.
    @Test
    public void getIntersectionsWith_7() {
        final Rectangle r2 = new Rectangle(
            new Point(5, 1),
            new Point(8, 2)
        );

        final Set<Point> actualIntersections = r1.getIntersectionsWith(r2);

        assertTrue(actualIntersections.isEmpty());
    }

    // See intersections.png #8
    @Test
    public void getIntersectionsWith_8() {
        final Rectangle r2 = new Rectangle(
            new Point(5, 0),
            new Point(8, 1)
        );

        final Set<Point> actualIntersections = r1.getIntersectionsWith(r2);

        assertTrue(actualIntersections.isEmpty());
    }
}
