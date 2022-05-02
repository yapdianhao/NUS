import java.io.*;
import java.util.Comparator;
import java.util.Collections;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader scroll = new BufferedReader(new FileReader(args[0]));
        String line;
        ArrayList<Food> foods = new ArrayList<Food>();
        ArrayList<Animal> animals = new ArrayList<Animal>();
        while ((line = scroll.readLine()) != null) {
            try {
                String[] words = line.split(" ");
                if (words[0].equals("new")) {
                    String animal = words[1];
                    if ((animal.equals("cat") || animal.equals("dog")) && words.length < 5) {
                        throw new IllegalInstructionException("Too few arguments");
                    } else if (!animal.equals("cat") && !animal.equals("dog")) {
                        throw new IllegalInstructionException(animal + " is not a valid species");
                    }
                    String name = words[2];
                    String number = words[3];
                    int appetite = Integer.parseInt(number);
                    String extra = words[4];
                    if (animal.equals("cat")) {
                        Animal cat = new Cat(name, appetite, extra);
                        animals.add(cat);
                        Collections.sort(animals, new SortByName());
                        System.out.println(cat.toString());
                    } else if (animal.equals("dog")) {
                        Animal dog = new Dog(name, appetite, extra);
                        animals.add(dog);
                        Collections.sort(animals, new SortByName());
                        System.out.println(dog.toString());
                    }
                } else if (words[0].equals("add")){
                    String type = words[1];
                    if ((type.equals("tuna") || type.equals("cheese") || type.equals("chocolate")) && words.length < 3) {
                        throw new IllegalInstructionException("Too few arguments");
                    } else if (!type.equals("tuna") && !type.equals("cheese") && !type.equals("chocolate")) {
                        throw new IllegalInstructionException(type + " is not a valid food type");
                    }
                    String brand = words[2];
                    if (type.equals("tuna")) {
                        Food tuna = new Tuna(type, brand);
                        foods.add(tuna);
                        System.out.println(tuna.toString());
                    } else if (type.equals("cheese")) {
                        Food cheese = new Cheese(type, brand);
                        foods.add(cheese);
                        System.out.println(cheese.toString());
                    } else if (type.equals("chocolate")){
                        if (words.length < 4) {
                            throw new IllegalInstructionException("Too few arguments");
                        } else {
                            brand += " " + words[3];
                            Food chocolate = new Chocolate(type, brand);
                            foods.add(chocolate);
                            System.out.println(chocolate.toString());
                        }
                    } 
                } else if (words[0].equals("eat")) {
                    for (Animal animal : animals) {
                        animal.eat(foods);
                    }
                } else if (words[0].equals("hello")) {
                    for (Animal animal : animals) {
                        System.out.println(animal.hello());
                    }
                } else {
                    throw new IllegalInstructionException(words[0] + " is not a valid instruction");
                }
            } catch (IllegalInstructionException e) {
                System.out.println(e.toString());
            }
        }
    }
}
