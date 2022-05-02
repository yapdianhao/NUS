import java.util.ArrayList;

class Cat extends Animal {
    boolean isLazy = false;
    int fullness = 0;

    public Cat(String name, int appetite, String extra) {
        super(name, appetite, extra);
    }

    public String hello() {
        if (!isLazy) {
            isLazy = true;
            return name + "(" + extra + ") says meow and gets lazy";
        } else {
            isLazy = false;
            return name + "(" + extra + ") is lazy";
        }
    }

    public void eat(ArrayList<Food> foods) {
        ArrayList<Food> eatenFoods = new ArrayList<Food>();
        for (Food food : foods) {
            if (fullness < appetite) {
                if (food.type.equals("cheese")) {
                    continue;
                } else {
                    fullness ++;
                    eatenFoods.add(food);
                    System.out.println(name + "(" + extra + ") eats " + food.brand + " " + food.type);
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
        return name + "(" + extra + ") was created";
    }
}
