class different {

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in);

        while (kattio.hasMoreTokens()) {
            long first = kattio.getLong();
            long second = kattio.getLong();
            kattio.println(Math.abs(first - second));
            kattio.flush();
        }
    }
}