import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int id = -1;
    ArrayList<Item> menu = new ArrayList<>(100);
    ArrayList<Combo> menu2 = new ArrayList<>(100);

    while (sc.next().equals("add")) {
      String type = sc.next();
      if (type.equals("Combo")) {
        Combo combo = new Combo(++id, sc.nextInt(), sc.nextInt(), sc.nextInt());
        if (combo.check(menu)) {
          menu2.add(id, combo);
          menu.add(id, new Item(20, "", "", 0));
        } else {
          id--;
        }
      } else {
        Item item = new Item(++id, type, sc.next(), sc.nextInt());
        menu.add(id, item);
        menu2.add(id, new Combo(20, 0, 0, 0));
      }
    }

    // print burgers, snacks and drinks using three loops
    for (Item item : menu) {
      if (item.type.equals("Burger")) {
        item.print();
      }
    }
    for (Item item : menu) {
      if (item.type.equals("Snack")) {
        item.print();
      }
    }
    for (Item item : menu) {
      if (item.type.equals("Drink")) {
        item.print();
      }
    }
    sc.nextLine();

    // print line
    System.out.println("--- Order ---");

    int sum = 0;

    // read the order
    while (sc.hasNext()) {
      int i = sc.nextInt();
      Item item = menu.get(i);
      if (item.id < 20) {
        item.print();
        sum += item.price;
      } else {
        Combo combo = menu2.get(i);
        int price = combo.price(menu);
        combo.print(menu, price);
        sum += price;
      }
    }
    System.out.println("Total: " + sum);
  }
}

class Item {
  public int id;
  public String type;
  public String desc;
  public int price;

  // constructor
  public Item(int id, String type, String desc, int price) {
    this.id = id;
    this.type = type;
    this.desc = desc;
    this.price = price;
  }

  public void print() {
    System.out.printf("#%d %s: %s (%d)\n", id, type, desc, price);
  }
}

class Combo {
  public int id1, id2, id3;
  public int id;

  // constructor
  public Combo(int id, int id1, int id2, int id3) {
    this.id = id;
    this.id1 = id1;
    this.id2 = id2;
    this.id3 = id3;
  }

  public int price(ArrayList<Item> menu) {
    return menu.get(id1).price + menu.get(id2).price + menu.get(id3).price - 50;
  }

  public void print(ArrayList<Item> menu, int price) {
    Item item1 = menu.get(id1);
    Item item2 = menu.get(id2);
    Item item3 = menu.get(id3);
    System.out.printf("#%d Combo (%d)\n", id, price);
    System.out.printf("   #%d %s: %s (%d)\n", id1, item1.type, item1.desc, item1.price);
    System.out.printf("   #%d %s: %s (%d)\n", id2, item2.type, item2.desc, item2.price);
    System.out.printf("   #%d %s: %s (%d)\n", id3, item3.type, item3.desc, item3.price);
  }

  public boolean check(ArrayList<Item> menu) {
    int index = menu.size() - 1;
    String error = String.format("Error: Invalid combo input %d %d %d", id1, id2, id3);
    if (id1 > index || id2 > index || id3 > index) {
      System.out.println(error);
      return false;
    } else {
      Item item1 = menu.get(id1);
      Item item2 = menu.get(id2);
      Item item3 = menu.get(id3);
      if (!item1.type.equals("Burger") || !item2.type.equals("Snack") || !item3.type.equals("Drink")) {
        System.out.println(error);
        return false;
      } else {
        return true;
      }
    }
  }
}
