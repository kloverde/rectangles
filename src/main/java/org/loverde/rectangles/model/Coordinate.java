package org.loverde.rectangles.model;

/**
 * A Coordinate is an immutable pair of (x, y) values.  It's based on a coordinate system
 * which places (0,0) at the lower-left corner of the screen, hence the values are
 * greater than or equal to 0.
 */
public class Coordinate {
    private double x;
    private double y;

    public Coordinate(final double x, final double y) {
        if(x < 0) {
            throw new IllegalArgumentException("x must be greater than or equal to 0");
        }

        if(y < 0) {
            throw new IllegalArgumentException("y must be greater than or equal to 0");
        }

        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
