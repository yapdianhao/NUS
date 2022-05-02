import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int counter = 0;
        List<Buyable> menu = new ArrayList<>();
        while (sc.next().equals("add")) {
            String type = sc.next();
            if (type.equals("Combo")) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                int c = sc.nextInt();
                Item burger = null;
                Item snack = null;
                Item drink = null;
                if (a < 0 || a >= menu.size() 
                        || b < 0 || b >= menu.size() 
                        || c < 0 || c >= menu.size()) {
                    System.out.println("Error: Invalid combo input " + a + " " + b + " " + c);
                    continue;
                }
                if (!(menu.get(a) instanceof Item 
                        && menu.get(b) instanceof Item 
                        && menu.get(c) instanceof Item)) {
                    System.out.println("Error: Invalid combo input " + a + " " + b + " " + c);
                    continue;
                }
                burger = (Item)menu.get(a);
                snack = (Item)menu.get(b);
                drink = (Item)menu.get(c);
                if (!burger.getType().equals("Burger") || !snack.getType().equals("Snack")
                    || !drink.getType().equals("Drink")) {
                    System.out.println("Error: Invalid combo input " + a + " " + b + " " + c);
                    continue;
                }
                Combo combo = new Combo(burger, snack, drink, counter);
                counter++;
                menu.add(combo);
            } else {
                String desc = sc.next();
                int price = sc.nextInt();
                Item item = new Item(type, desc, price, counter);
                counter++;
                menu.add(item);
            }
        }

        // output sorted items
        List<Item> sortedItems = new ArrayList<>();
        for (Buyable buyable : menu) {
            if (buyable instanceof Item) { 
                sortedItems.add((Item)buyable);
            }
        }
        Collections.sort(sortedItems);
        for (Item item : sortedItems) {
            System.out.println(item);
        }
        for (Buyable buyable : menu) {
            if (buyable instanceof Combo) { 
                System.out.println(buyable);
            }
        }
        // read order
        int total = 0;
        System.out.println("--- Order ---");
        while (sc.hasNext()) {
            int num = sc.nextInt();
            Buyable buyable = menu.get(num);
            total += buyable.getCost();
            System.out.println(buyable);
        }
        System.out.println("Total: " + total);
    }
}
