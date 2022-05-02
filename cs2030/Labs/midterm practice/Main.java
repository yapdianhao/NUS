import java.util.Scanner;

class Main {
    public static void main(String[] args){
        Manager manager = new Manager();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String nameA = scanner.next();
            String nameB = scanner.next();
            double distance = scanner.nextDouble();
            manager.addRoute(nameA, nameB, distance);
        }
        System.out.println("Number of cities: " + manager.getNumberOfCities());
        System.out.println("Number of routes: " + manager.getNumberOfRoutes());
        System.out.println();
        
        manager.printAllCities();
        System.out.println();
        
        manager.printAllRoutes();
        System.out.println();

        manager.printMostConnectedCity();
        System.out.println();

        manager.printLongestRoute();
    }
}
