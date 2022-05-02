class Password {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int passwords = io.getInt();
        double[] arr = new double[passwords];
        for (int i = 0; i < passwords; i++) {
            String s = io.getWord();
            double p = io.getDouble();
            arr[i] = p;
        }
        double res = 0;
        for (int i = 0; i < passwords; i++) {
            res += arr[i] * (i + 1);
        }
        io.println(res);
        io.close();
    }
}