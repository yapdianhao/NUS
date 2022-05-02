import java.util.Comparator;

class SortByName implements Comparator<Animal> {

    public int compare(Animal a, Animal b) {
        return a.name.compareTo(b.name);
    }
}
