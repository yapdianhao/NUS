import java.util.ArrayList;

class Dog extends Animal {
    int count = 1;
    int fullness = 0;

    public Dog(String name, int appetite, String extra) {
        super(name, appetite, extra);
    }

    @Override
    public String hello() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(extra);
        }
        count++;
        return name + " says " + sb.toString();
    }

    @Override
    public void eat(ArrayList<Food> foods) {
        ArrayList<Food> eatenFoods = new ArrayList<Food>();
        for (Food food : foods) {
            if (fullness < appetite) {
                if (food.type.equals("chocolate")) {
                    continue;
                } else {
                    fullness++;
                    eatenFoods.add(food);
                    System.out.println(name + " eats " + food.brand + " " + food.type);
                }
            } else {
                break;
            }
        }
        for (Food food : eatenFoods) {
            foods.remove(food);
        }
    }

    @Override
    public String toString() {
        return name + " was created";
    }
}
