import java.util.ArrayList;

public abstract class Animal {
    String name;
    int appetite; 
    String extra;

    public Animal(String name, int appetite, String extra) {
        this.name = name;
        this.appetite = appetite;
        this.extra = extra;
    }

    public abstract String hello();

    public abstract void eat(ArrayList<Food> foods);

    public abstract String toString();
}
