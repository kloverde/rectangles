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
     *         <em>null</em> if there is no intersection.
     *
     * @throws IllegalArgumentException If <em>r</em> is null
     */
    public Rectangle getIntersection(final Rectangle r) {
        if(r == null) throw new IllegalArgumentException("getIntersection:  rectangle cannot be null");

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

        Rectangle intersection = null;

        try {
            intersection = new Rectangle(iRectLowerLeft, iRectUpperRight);
        } catch(IllegalArgumentException e) {
            // Attempt to create an invalid rectangle means there's no intersection.  Allow null to be returned.
        }

        return intersection;
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
        final Rectangle overlap = getIntersection(potentialIntersector);

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
     * Tests whether this rectangle contains another.  Containment is defined as complete encapsulation, with no shared edges.
     *
     * @param r The rectangle to test containment with
     *
     * @return <em>true</em> if this rectangle contains <em>r</em>; <em>false</em> if not
     *
     * @throws IllegalArgumentException If <em>r</em> is null
     *
     * @see #isContainedBy(Rectangle)
     * @see #hasContainmentWith(Rectangle)
     */
    public boolean contains(final Rectangle r) {
        return whoContainsWho(this, r) == this;
    }

    /**
     * Tests whether this rectangle is contained by another.  Containment is defined as complete encapsulation, with no
     * shared edges.
     *
     * @param r The rectangle to test containment with
     *
     * @return <em>true</em> if this rectangle is contained by <em>r</em>; <em>false</em> if not
     *
     * @throws IllegalArgumentException If <em>r</em> is null
     *
     * @see #contains(Rectangle)
     * @see #hasContainmentWith(Rectangle)
     */
    public boolean isContainedBy(final Rectangle r) {
        return whoContainsWho(this, r) == r;
    }

    /**
     * <p>Tests whether containment exists between this rectangle and another.  Containment is defined as complete
     * encapsulation, with no shared edges.</p>
     *
     * <p>This method doesn't tell you which rectangle is the container.  Other API methods are available to tell
     * you this (see below).</p>
     *
     * @param r The rectangle to test containment with
     *
     * @return <em>true</em> if this rectangle contains <em>r</em>; <em>false</em> if not
     *
     * @throws IllegalArgumentException If <em>r</em> is null
     *
     * @see #contains(Rectangle)
     * @see #isContainedBy(Rectangle)
     */
    public boolean hasContainmentWith(final Rectangle r) {
        return whoContainsWho(this, r) != null;
    }

    /**
     * Determines whether <em>r1</em> and <em>r2</em> have containment, and tell you which one is the container.
     * Containment is defined as complete encapsulation, with no shared edges.
     *
     * @param r1 Rectangle to test containment with <em>r2</em>
     * @param r2 Rectangle to test containment with <em>r1</em>
     *
     * @return <em>r1</em> if r1 contains r2; <em>r2</em> if r2 contains r1; <em>null</em> if there is no containment
     *
     * @throws IllegalArgumentException If <em>r1</em> or <em>r2</em> is null
     */
    private static Rectangle whoContainsWho(final Rectangle r1, final Rectangle r2) {
        if(r1 == null) throw new IllegalArgumentException("rectangle r1 cannot be null");
        if(r2 == null) throw new IllegalArgumentException("rectangle r2 cannot be null");

        // It's not enough to check that a left origin (bottom left) is less than another.  To know
        // that it's contained, one has to be less than another AND it has to be bounded by the
        // right side.  This is represented by the longer comparisons below, wrapped in parentheses.

        final boolean r1ContainsR2 =
            (r1.getLeftX()  < r2.getLeftX()   && r2.getLeftX() < r1.getRightX()) &&
            r1.getRightX()  > r2.getRightX()  &&
            r1.getBottomY() < r2.getBottomY() &&
            r1.getTopY()    > r2.getTopY();

        final boolean r2ContainsR1 =
            (r2.getLeftX()  < r1.getLeftX()   && r1.getLeftX() < r2.getRightX())  &&
            r2.getRightX()  > r1.getRightX()  &&
            r2.getBottomY() < r1.getBottomY() &&
            r2.getTopY()    > r1.getTopY();

        if(r1ContainsR2) return r1;
        if(r2ContainsR1) return r2;

        return null;
    }

    public boolean isAdjacentTo(final Rectangle r) {
        if(equals(r)) return false;  // Identical, perfectly overlaid rectangles can exit early

        // To determine whether an edge is shared, first let's visualize which edges we'd be comparing:
        //   - A top edge of r1 can only touch a bottom edge of r2  - y value match means they're on the same plane
        //   - A bottom edge of r1 can only touch a top edge of r2  - y value match means they're on the same plane
        //   - A left edge of r1 can only touch a right edge of r2  - x value match means they're on the same plane
        //   - A right edge of r1 can only touch a left edge of r2  - x value match means they're on the same plane
        //
        // Once you know that a set of edges are on the same plane, check for whether any part of them coincide:
        //
        // For vertical edges with the same x value, check their y values to determine if there's overlap.
        // For horizontal edges with the same y value, check their x values to determine if there's overlap.
        //
        // At least one endpoint from the r1 edge must be in the range of r2Start <= r1Endpoint <= r2End
        // However, this comparison only works if you know the segment you're range-checking is the shorter one, or
        // if it's the same length - so you need to determine which segment to bound.

        if(getTopY() == r.getBottomY() || getBottomY() == r.getTopY()) {
            // Horizontal edges have same y:  check x for overlap

            final Rectangle widerOne = getWidth() >= r.getWidth() ? this : r;
            final double wideLeftX = widerOne.getLowerLeft().getX();
            final double wideRightX = widerOne.getLowerRight().getX();

            final Rectangle shorterOne = widerOne == this ? r : this;
            final double shortLeftX = shorterOne.getLowerLeft().getX();
            final double shortRightX = shorterOne.getLowerRight().getX();

            return (wideLeftX <= shortLeftX && shortLeftX <= wideRightX) || (wideLeftX <= shortRightX && shortRightX <= wideRightX);
        } else if(getLeftX() == r.getRightX() || getRightX() == r.getLeftX()) {
            // Vertical edges have same x:  check y for overlap

            final Rectangle tallerOne = getHeight() >= r.getHeight() ? this : r;
            final double tallBottomY = tallerOne.getLowerLeft().getY();
            final double tallTopY = tallerOne.getUpperLeft().getY();

            final Rectangle shorterOne = tallerOne == this ? r : this;
            final double shortBottomY = shorterOne.getLowerLeft().getY();
            final double shortTopY = shorterOne.getUpperLeft().getY();

            return (tallBottomY <= shortBottomY && shortBottomY <= tallTopY) || (tallBottomY <= shortTopY && shortTopY <= tallTopY);
        }

        return false;
    }

    /** @return The width of the rectangle */
    public double getWidth() {
        return getLowerRight().getX() - getLowerLeft().getX();
    }

    /** The height of the rectangle */
    public double getHeight() {
        return getUpperLeft().getY() - getLowerRight().getY();
    }

    /** @return The left <em>x</em> boundary */
    public double getLeftX() {
        return lowerLeft.getX();
    }

    /** @return The right <em>x</em> boundary */
    public double getRightX() {
        return getLowerRight().getX();
    }

    /** @return The lower <em>y</em> boundary */
    public double getBottomY() {
        return getLowerLeft().getY();
    }

    /** @return The upper <em>y</em> boundary */
    public double getTopY() {
        return getUpperLeft().getY();
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
