class Oddities {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int cases = io.getInt();
        while (cases-- > 0) {
            int num = io.getInt();
            io.println(num % 2 == 0 ? num + " is even" : num + " is odd");
            io.flush();
        }
        io.close();
    }
}