package org.loverde.rectangles.model;

import java.util.ArrayList;
import java.util.List;

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
     * Gets A rectangle representing the region of overlap between this rectangle and rectangle <em>r</em>.  This is
     * NOT a list of the points of intersection, though either 2 or 4 of these vertices ARE the points of intersection.
     * To get just the points of intersection, use {@link #getIntersectionsWith}.
     *
     * @param r The rectangle to calculate the overlap with
     *
     * @return A rectangle representing the region of overlap between this rectangle and rectangle <em>r</em>
     */
    public Rectangle getOverlappingRegion(final Rectangle r) {
        // To visualize what this algorithm is doing, look at rectangles 1 and 2 in junit_diagram.png.
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

        return new Rectangle(iRectLowerLeft, iRectUpperRight);
    }

    public Point[] getIntersectionsWith(final Rectangle r2) {
        final Rectangle iRect = getOverlappingRegion(r2);

        // Okay, we have the overlap region, but which vertices are intersecting?  It could be 2 or all 4.
        // Intersections will have either an X value or a Y value in common.  If they have both in common,
        // then they have touching vertices, and thus are not intersecting.  So, it's either one or the
        // other.

        final double r1x1 = getLowerLeft().getX();
        final double r1y1 = getLowerLeft().getY();
        final double r1x2 = getLowerRight().getX();
        final double r1y2 = getLowerRight().getY();

        final double r2x1 = r2.getLowerLeft().getX();
        final double r2y1 = r2.getLowerLeft().getY();
        final double r2x2 = r2.getUpperRight().getX();
        final double r2y2 = r2.getUpperRight().getY();

        final List<Point> intersections = new ArrayList<>();
/*
        if(

        ) {

        }
*/
        return null;
    }

    /**
     * Determines whether this rectangle and <em>r2</em> have containment.  Containment is defined as either rectangle
     * completely encapsulating the other, with no shared edges.
     *
     * @param r2 The rectangle to test containment with
     *
     * @return True if containment exists, false if not
     */
    public boolean hasContainmentWith(final Rectangle r2) {
        final double thisLeftX = getLowerLeft().getX();
        final double thisRightX = getLowerRight().getX();
        final double thisBottomY = getLowerLeft().getY();
        final double thisTopY = getUpperRight().getY();

        final double r2LeftX = r2.getLowerLeft().getX();
        final double r2RightX = r2.getLowerRight().getX();
        final double r2BottomY = r2.getLowerLeft().getY();
        final double r2TopY = r2.getUpperRight().getY();

        final boolean thisContainsR2 =
            thisLeftX   < r2LeftX   &&
            thisRightX  > r2RightX  &&
            thisBottomY < r2BottomY &&
            thisTopY    > r2TopY;

        final boolean r2ContainsThis =
            r2LeftX   < thisLeftX   &&
            r2RightX  > thisRightX  &&
            r2BottomY < thisBottomY &&
            r2TopY    > thisTopY;

        return thisContainsR2 || r2ContainsThis;
    }
}
