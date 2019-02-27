import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int id = 0;
    ArrayList<Item> menu = new ArrayList<>();

    while (sc.next().equals("add")) {
      Item item = new Item(id++, sc.next(), sc.next(), sc.nextInt());
      menu.add(item);
    }

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
