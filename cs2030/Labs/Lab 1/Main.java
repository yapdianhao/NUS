import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Point[] points = new Point[n];
        // find all the points
        for (int i = 0; i < n; i++) {
            Point p = new Point(sc.nextDouble(), sc.nextDouble());
            points[i] = p;
        }
        int ans = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (points[i].getDistance(points[j]) > 2) {
                    continue;
                }
                int counter = 0;
                Circle circle = new Circle(Circle.getCenterOfCircle(points[i], points[j]));
                // count the number of points inside the circle
                for (int k = 0; k < n; k++) {
                    if (circle.containsPoint(points[k])) {
                        counter++;
                    }
                }
                if (counter > ans) {
                    ans = counter;
                }
            }
        }
        System.out.println("Maximum Disc Coverage: " + ans);
    }
} 
