public class Circle {
    private Point center;
    private double epsilon = 0.000001;

    public Circle(Point center) {
        this.center = center;
    }

    public Circle() {
        this(new Point(0, 0));
    }

    /**
     * Checks if the given point is inside the circle.
     * @param q The other point
     * @return The boolean expression of whether q is inside the circle
     */
    public boolean containsPoint(Point q) {
        return center.getDistance(q) <= 1 + epsilon;
    }

    /**
     * Finds the center of the circle such that the 2 points given 
     * are on the circumference of that unit circle,
     * moreover, the current point, the other point given, and the center
     * will be clockwise.
     * @param p The first point
     * @param q The second point
     * @return The center
     */
    public static Point getCenterOfCircle(Point p, Point q) {
        Point midpoint = p.midPoint(q);
        double distanceToQ = p.getDistance(q);
        double angle = p.angleTo(q) - Math.PI / 2;
        double d = Math.sqrt(1 - distanceToQ / 2 * distanceToQ / 2);
        //move midpoint at angle by d
        return midpoint.moveAtAngleAndDistance(angle, d);
    }
}
