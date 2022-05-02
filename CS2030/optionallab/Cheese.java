class Cheese extends Food {

    public Cheese(String type, String brand) {
        super(type, brand);
    }

    @Override
    public String toString() {
        return brand + " " + type + " was added";
    }
}
