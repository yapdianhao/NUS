import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Manager {
    private List<City> cities = new ArrayList<>();
    private List<Route> routes = new ArrayList<>();

    public Manager() {
    
    }

    public int getNumberOfCities() {
        return cities.size();
    }

    public int getNumberOfRoutes() {
        return routes.size();
    }

    public void addRoute(String nameA, String nameB, double distance) {
        if (nameA.equals(nameB)) {
            return;
        }
        City cityA = null, cityB = null;
        for (City city : cities) { //check if city appears before
            if (city.equals(nameA)) {
                cityA = city;
            }
            if(city.equals(nameB)) {
                cityB = city;
            }
        }
        if (cityA == null) {
            cityA = new City(nameA);
            cities.add(cityA);
        }
        if (cityB == null) {
            cityB = new City(nameB);
            cities.add(cityB);
        }
        Route newRoute = new Route(cityA, cityB, distance);
        for (Route route : routes) {
            if (route.equals(newRoute)) {
                return;
            }
        }
        cityA.addRoute();
        cityB.addRoute();
        routes.add(newRoute);
    }

    public void printMostConnectedCity() {
        Collections.sort(cities);
        int connectivity = 0;
        for (City city : cities) {
            if(city.getConnectivity() > connectivity) {
                connectivity = city.getConnectivity();
            }
        }
        System.out.println("Most accessible cities (sorted):");
        for (City city : cities) {
            if(city.getConnectivity() == connectivity) {
                System.out.println(city);
            }
        }
    }

    public void printLongestRoute() {
        Collections.sort(routes);
        double distance = 0;
        for (Route route : routes) {
            if(route.getDistance() > distance) {
                distance = route.getDistance();
            }
        }
        System.out.println("Longest routes (sorted):");
        for (Route route : routes) {
            if(route.getDistance() == distance) {
                System.out.println(route);
            }
        }
    }

    public void printAllCities() {
        System.out.println("List of cities:");
        for (City city : cities) {
            System.out.println(city);
        }
    }

    public void printAllRoutes() {
        System.out.println("List of routes:");
        for (Route route : routes) {
            System.out.println(route);
        }
    }
} 
