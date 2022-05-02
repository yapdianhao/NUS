public class Point {
    private double x;
    private double y;
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this(0, 0);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Finds the mispoint between that point and the point given.
     * @param q The other point
     * @return Point This returns the midpoint
     */
    public Point midPoint(Point q) {
        return new Point((x + q.x) / 2, (y + q.y) / 2);
    }

    /**
     * Finds the angle from the current point to another point.
     * @param q The other point
     * @return The angle in radians
     */
    public double angleTo(Point q) {
        return Math.atan2(q.y - y, q.x - x);
    }

    /**
     * Finds the distance from the current point to another point.
     * @param q The other point
     * @return The distance
     */
    public double getDistance(Point q) {
        return Math.sqrt((x - q.x) * (x - q.x) + (y - q.y) * (y - q.y));
    }

    /**
     * Moves the current point by a distance at an angle.
     * @param angle The angle to move the point
     * @param d The distance to move the point
     * @return The new point at the new position
     */
    public Point moveAtAngleAndDistance(double angle, double d) {
        return new Point(x + d * Math.cos(angle), y + d * Math.sin(angle));
    }
}


