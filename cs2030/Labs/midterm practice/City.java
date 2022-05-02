public class City implements Comparable<City> {
    private String name;
    private int connectivity = 0;
    
    public City(String name) {
        this.name = name;
    }

    /**
     * Adds a route to this city.
     */
    public void addRoute() {
        connectivity++;
    }

    public int getConnectivity() {
        return connectivity;
    }

    public boolean equals(City other) {
        return name.equals(other.name);
    }

    public boolean equals(String name) {
        return this.name.equals(name);
    }

    @Override
    public int compareTo(City other) {
        return name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
