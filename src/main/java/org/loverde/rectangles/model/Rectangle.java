package org.loverde.rectangles.model;

public class Rectangle {

    private final double x1;
    private final double x2;
    private final double y1;
    private final double y2;
    private final double width;
    private final double height;

    /**
     * Create a rectangle based on a coordinate system where the lower left corner of the screen is the origin (0,0).
     * A rectangle may not have a zero width or a zero height.
     *
     * @param origin The coordinate of the rectangle's lower-left corner
     * @param width  Width of the rectangle.  Must be greater than 0.
     * @param height Height of the rectangle.  Must be greater than 0.
     *
     * @throws IllegalArgumentException If x or y is negative; if width or height are negative or zero
     */
    public Rectangle(
        final Coordinate origin,
        final double width,
        final double height
    ) {
        if(origin == null) {
            throw new IllegalArgumentException("origin cannot be null");
        }

        if (width <= 0) {
            throw new IllegalArgumentException("width must be greater than 0");
        }

        if (height <= 0) {
            throw new IllegalArgumentException("height must be greater than 0");
        }

        x1 = origin.getX();
        x2 = origin.getX() + width;

        y1 = origin.getY();
        y2 = origin.getY() + height;

        this.width = width;
        this.height = height;
    }

    /**
     * @return The X coordinate of the left side
     */
    public double getX1() {
        return x1;
    }

    /**
     * @return The X coordinate of the right side
     */
    public double getX2() {
        return x2;
    }

    /**
     * @return The Y coordinate of the bottom side
     */
    public double getY1() {
        return y1;
    }

    /**
     * @return The Y coordinate of the top side
     */
    public double getY2() {
        return y2;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    /**
     * @return The complete set of coordinates of the rectangle, in the following order:
     *         bottom left; top left; top right; bottom right
     */
    public Coordinate[] getCoordinates() {
        return new Coordinate[]{
            new Coordinate(x1, y1),
            new Coordinate(x1, y2),
            new Coordinate(x2, y2),
            new Coordinate(x2, y1)
        };
    }
}
