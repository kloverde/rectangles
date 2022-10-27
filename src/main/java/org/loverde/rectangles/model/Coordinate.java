package org.loverde.rectangles.model;

import java.util.Objects;

/**
 * A Coordinate is an immutable pair of (x, y) values.  It's based on a coordinate system
 * which places (0,0) at the lower-left corner of the screen, hence the values are
 * greater than or equal to 0.
 */
public class Coordinate {
    private final double x;
    private final double y;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Coordinate that = (Coordinate) o;

        return Double.compare(that.getX(), getX()) == 0 && Double.compare(that.getY(), getY()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
