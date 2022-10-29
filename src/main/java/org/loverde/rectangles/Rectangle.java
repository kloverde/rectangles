package org.loverde.rectangles;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

public class Rectangle {

    private final Point lowerLeft;
    private final Point upperRight;

    /**
     * Create a rectangle based on a coordinate system where the lower left corner of the screen is the origin (0,0).
     * A rectangle may not have a zero width or a zero height.
     */
    public Rectangle(final Point lowerLeft, final Point upperRight) {
        if(lowerLeft == null) {
            throw new IllegalArgumentException("lowerLeft cannot be null");
        }

        if(upperRight == null) {
            throw new IllegalArgumentException("upperRight cannot be null");
        }

        if(lowerLeft.getX() >= upperRight.getX()) {
            throw new IllegalArgumentException("upperRight.x must be greater than lowerLeft.x");
        }

        if(lowerLeft.getY() >= upperRight.getY()) {
            throw new IllegalArgumentException("upperRight.y must be greater than lowerLeft.y");
        }

        this.lowerLeft = new Point(lowerLeft.getX(), lowerLeft.getY());
        this.upperRight = new Point(upperRight.getX(), upperRight.getY());
    }

    /**
     * @return The lower left vertex
     */
    public Point getLowerLeft() {
        return new Point(lowerLeft.getX(), lowerLeft.getY());
    }

    public Point getUpperLeft() {
        return new Point(lowerLeft.getX(), upperRight.getY());
    }

    /**
     * @return The upper right vertex
     */
    public Point getUpperRight() {
        return new Point(upperRight.getX(), upperRight.getY());
    }

    /**
     * @return The lower right vertex
     */
    public Point getLowerRight() {
        return new Point(upperRight.getX(), lowerLeft.getY());
    }

    /**
     * Gets the rectangle representing the region of overlap between this rectangle and rectangle <em>r</em>.  This is
     * NOT a list of the points of intersection, though either 2 or 4 of these vertices ARE the points of intersection.
     * To get just the points of intersection, use {@link #getIntersectingPoints}.
     *
     * @param r The rectangle to calculate the overlap with
     *
     * @return The rectangle representing the region of overlap between this rectangle and rectangle <em>r</em>, or
     *         <em>null</em> if there is no shared region.
     *
     * @throws IllegalArgumentException If <em>r</em> is null
     */
    public Rectangle getOverlappingRegionWith(final Rectangle r) {
        if(r == null) throw new IllegalArgumentException("getOverlappingRegionWith:  rectangle cannot be null");

        if(equals(r)) return this;  // Perfectly aligned, identical rectangles can exit early

        // To visualize what this algorithm is doing, look at rectangles 1 and 2 in intersections.png.
        //
        // GETTING THE X VALUE OF THE ORIGIN (BOTTOM LEFT VERTEX):
        //
        //   * Let's say the intersecting rectangle is #1.  How do we get the X value of intersection point i1?
        //     Look at the x value of #1's origin.  It's outside the black rectangle, so that can't be it.  It's the
        //     black rectangle's left edge.  The larger value is the x-intersection.
        //
        //   * Let's say the intersecting rectangle is #2.  How do we get the X value of intersection point i2?
        //     Look at the x value of the #2's origin.  Compare this to the origin of the black rectangle.  Once again,
        //     the larger value is the x-intersection - but this time, the red rectangle gave us the value instead of
        //     the black rectangle.  It doesn't matter which one it comes from - the highest value always wins.
        //
        //   Conclusion:  use max() of the x values
        //
        // GETTING THE Y VALUE OF THE ORIGIN (BOTTOM LEFT VERTEX):
        //
        //   * Let's say the intersecting rectangle is #1.  How do we get the Y value of intersection point i1?
        //     Look at the y value of #1's origin.  Compare this to the origin of the black rectangle.  The highest
        //     value wins.  Notice a pattern?  You've probably realized by now that you don't need to look at another
        //     example to know that this rule will hold.
        //
        //   Conclusion:  use max() of the y values
        //
        // GETTING THE TOP-RIGHT VERTEX:
        //
        //   Similar thinking will get you most of the way there.  This time, you're comparing the top-right vertices
        //   of the two rectangles.  However, since we're comparing a top vertex instead of a bottom vertex, we need
        //   to use min() instead of max(), otherwise we won't cross into the black rectangle.
        //
        //   Conclusion:  use min() of the y values
        final Point iRectLowerLeft = new Point(
            Math.max(lowerLeft.getX(), r.lowerLeft.getX()),
            Math.max(lowerLeft.getY(), r.lowerLeft.getY())
        );

        final Point iRectUpperRight = new Point(
            Math.min(upperRight.getX(), r.upperRight.getX()),
            Math.min(upperRight.getY(), r.upperRight.getY())
        );

        Rectangle overlap = null;

        try {
            overlap = new Rectangle(iRectLowerLeft, iRectUpperRight);
        } catch(IllegalArgumentException e) {
            // If we've attempted to create an invalid rectangle, that's because there's no
            // intersection.  Allow null to be returned.
        }

        return overlap;
    }

    /**
     * Gets all points of intersection between this rectangle and another.
     *
     * @param potentialIntersector The rectangle to get intersections with
     *
     * @return An unmodifiable set containing 0, 2 or 4 points of intersection.  This method never returns null.
     *
     * @throws IllegalArgumentException If <em>potentialIntersector</em> is null
     */
    public Set<Point> getIntersectingPoints(final Rectangle potentialIntersector) {
        if(potentialIntersector == null) throw new IllegalArgumentException("getIntersectingPoints:  rectangle cannot be null");

        final Set<Point> intersections = new HashSet<>();
        final Rectangle overlap = getOverlappingRegionWith(potentialIntersector);

        if(overlap != null) {
            // Okay, we have the overlap region, but which vertices are intersecting 'this'?  It could be 2 or all 4.

            if(isPointOnAnEdgeOf(overlap.getLowerLeft(), this) && isPointOnAnEdgeOf(overlap.getLowerLeft(), potentialIntersector)) {
                intersections.add(overlap.getLowerLeft());
            }

            if(isPointOnAnEdgeOf(overlap.getUpperLeft(), this) && isPointOnAnEdgeOf(overlap.getUpperLeft(), potentialIntersector)) {
                intersections.add(overlap.getUpperLeft());
            }

            if(isPointOnAnEdgeOf(overlap.getUpperRight(), this) && isPointOnAnEdgeOf(overlap.getUpperRight(), potentialIntersector)) {
                intersections.add(overlap.getUpperRight());
            }

            if(isPointOnAnEdgeOf(overlap.getLowerRight(), this) && isPointOnAnEdgeOf(overlap.getLowerRight(), potentialIntersector)) {
                intersections.add(overlap.getLowerRight());
            }
        }

        return Collections.unmodifiableSet(intersections);
    }

    /**
     * Determines whether this rectangle and <em>r2</em> have containment.  Containment is defined as either rectangle
     * completely encapsulating the other, with no shared edges.
     *
     * @param r2 The rectangle to test containment with
     *
     * @return True if containment exists, false if not
     *
     * @throws IllegalArgumentException If <em>r2</em> is null
     */
    public boolean hasContainmentWith(final Rectangle r2) {
        if(r2 == null) throw new IllegalArgumentException("hasContainmentWith:  rectangle cannot be null");

        final double thisLeftX = getLowerLeft().getX();
        final double thisRightX = getLowerRight().getX();
        final double thisBottomY = getLowerLeft().getY();
        final double thisTopY = getUpperRight().getY();

        final double r2LeftX = r2.getLowerLeft().getX();
        final double r2RightX = r2.getLowerRight().getX();
        final double r2BottomY = r2.getLowerLeft().getY();
        final double r2TopY = r2.getUpperRight().getY();

        // It's not enough to check that a left origin (bottom left) is less than another.  To know
        // that it's contained, one has to be less than another AND it has to be bounded by the
        // right side.  This is represented by the longer comparisons below, wrapped (needlessly)
        // in parentheses.

        final boolean thisContainsR2 =
            (thisLeftX  < r2LeftX   && r2LeftX < thisRightX) &&
            thisRightX  > r2RightX  &&
            thisBottomY < r2BottomY &&
            thisTopY    > r2TopY;

        final boolean r2ContainsThis =
            (r2LeftX  < thisLeftX   && thisLeftX < r2RightX)  &&
            r2RightX  > thisRightX  &&
            r2BottomY < thisBottomY &&
            r2TopY    > thisTopY;

        return thisContainsR2 || r2ContainsThis;
    }

    private static boolean isPointOnAnEdgeOf(final Point p, final Rectangle r) {
        return p.getX() == r.getLowerLeft().getX() || p.getX() == r.getLowerRight().getX() ||
               p.getY() == r.getUpperLeft().getY() || p.getY() == r.getLowerLeft().getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Rectangle rectangle = (Rectangle) o;

        return getLowerLeft().equals(rectangle.getLowerLeft()) && getUpperRight().equals(rectangle.getUpperRight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLowerLeft(), getUpperRight());
    }
}
