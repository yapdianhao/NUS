public abstract class Food {
    String brand;
    String type;

    public Food(String type, String brand) {
        this.brand = brand;
        this.type = type;
    } 

    public abstract String toString();
}
