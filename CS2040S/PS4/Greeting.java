import java.util.Arrays;
import java.util.Hashtable;

class Greeting {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int points = io.getInt();
        Point[] pointArr = new Point[points];
        Hashtable<Point, Integer> h = new Hashtable<Point, Integer>();
        for (int i = 0; i < points; i++) {
            Point point = new Point(io.getInt(), io.getInt());
            pointArr[i] = point;
            h.put(point, 0);
        }
        //System.out.println(h);
        for (int i = 0; i < points; i++) {
            for (int j = i + 1; j < points; j++) {
                //double diffx = Math.pow(one.x - two.x, 2);
                //double diffy = Math.pow(one.y - two.y, 2)
                if (distance(pointArr[i], pointArr[j])) {
                    Point temp = pointArr[i];
                    int inc = h.get(temp);
                    inc += 1;
                    h.put(temp, inc);
                }
            }
        }
        int count = 0;
        for (int i : h.values()) {
            count += i;
        }
        //io.println(h);
        io.println(count);
        io.close();
    }

    static boolean distance(Point one, Point two) {
        double diffx = Math.pow(one.x - two.x, 2);
        double diffy = Math.pow(one.y - two.y, 2);
        return Math.sqrt(diffx + diffy) == 2018;
    }


    static class Point {

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "(" + this.x + ", " + this.y + ")";
        }
    }
}