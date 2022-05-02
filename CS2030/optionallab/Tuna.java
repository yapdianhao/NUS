class Tuna extends Food {

    public Tuna(String type, String brand) {
        super(type, brand);
    }

    @Override
    public String toString() {
        return brand + " " + type + " was added";
    }
}
