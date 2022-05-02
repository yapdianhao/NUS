class Chocolate extends Food {

    public Chocolate(String type, String brand) {
         super(type, brand);
    }

    @Override
    public String toString() {
        return brand + " " + type + " was added";
    }
}
