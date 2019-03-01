import java.util.*;
import java.io.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(new FileReader(args[0]));
    String str;
    while (sc.hasNext()) {
      String instruction = sc.next();
      if (instruction.equals("new")) {
        Animal animal;
        if (sc.next().equals("cat")) {
          animal = new Cat(sc.next(), sc.nextInt(), sc.next());
        } else {
          animal = new Dog(sc.next(), sc.nextInt(), sc.next());
        }
        animal.printNew();
      } else {
        String foodType = sc.next();
        Food food;
        if (foodType.equals("cheese")) {
          food = new Cheese(sc.next());
        } else if (foodType.equals("tuna")) {
          food = new Tuna(sc.next());
        } else {
          food = new Chocolate(sc.next() + " " + sc.next());
        }
        food.printAdd();
      }
    }
  }
}

abstract class Animal {
  protected String name;
  protected int appetite;

  // constructor
  public Animal(String name, int appetite) {
    this.name = name;
    this.appetite = appetite;
  }

  abstract public void printNew();
}

class Cat extends Animal {
  private String colour;

  // constructor
  public Cat(String name, int appetite, String colour) {
    super(name, appetite);
    this.colour = colour;
  }

  public void printNew() {
    System.out.println(name + "(" + colour + ") was created");
  }
}

class Dog extends Animal {
  private String sound;

  // constructor
  public Dog(String name, int appetite, String sound) {
    super(name, appetite);
    this.sound = sound;
  }

  public void printNew() {
    System.out.println(name + " was created");
  }
}

abstract class Food {
  protected String brand;

  // constructor
  public Food(String brand) {
    this.brand = brand;
  }

  abstract public void printAdd();
}

class Cheese extends Food {
  public Cheese(String brand) {
    super(brand);
  }

  public void printAdd() {
    System.out.println(super.brand + " cheese was added");
  }
}

class Tuna extends Food {
  public Tuna(String brand) {
    super(brand);
  }

  public void printAdd() {
    System.out.println(super.brand + " tuna was added");
  }
}

class Chocolate extends Food {
  public Chocolate(String brand) {
    super(brand);
  }

  public void printAdd() {
    System.out.println(super.brand + " chocolate was added");
  }
}
