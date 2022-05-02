class Spavanac {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int hr = io.getInt();
        int min = io.getInt();
        if (hr >= 1) {
            if (min >= 45) {
                min -= 45;
            } else {
                min += 60 - 45;
                hr -= 1;
            }
        } else if (hr == 0) {
            if (min >= 45) {
                min -= 45;
            } else {
                min += 60 - 45;
                hr = 23;
            }
        io.println(hr + " " + min);
        }
        io.close();
    }
}