public class Route implements Comparable<Route> {
    private City cityA;
    private City cityB;
    private double distance;

    public Route (City cityA, City cityB, double distance) {
        if (cityA.equals(cityB)) {
            throw new RuntimeException("Route cannot connect a city to itself");
        }
        this.cityA = cityA;
        this.cityB = cityB;
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public boolean equals(Route other) {
        return (cityA.equals(other.cityA) && cityB.equals(other.cityB))
            || (cityB.equals(other.cityA) && cityA.equals(other.cityB));
    }

    @Override
    public int compareTo(Route other) {
        if (cityA.compareTo(other.cityA) != 0) {
            return cityA.compareTo(other.cityA);
        }
        return cityB.compareTo(other.cityB);
    }

    @Override
    public String toString() {
        return cityA + " and " + cityB + " is " + String.format("%.1f", distance) + "km apart";
    }
}
