import java.util.*;
import java.io.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(new FileReader(args[0]));
    String str;
    LinkedList<Food> foods = new LinkedList<>();
    LinkedList<Animal> animals = new LinkedList<>();
    while (sc.hasNext()) {
      String instruction = sc.next();
      if (instruction.equals("new")) {
        Animal animal;
        if (sc.next().equals("cat")) {
          animal = new Cat(sc.next(), sc.nextInt(), sc.next());
        } else {
          animal = new Dog(sc.next(), sc.nextInt(), sc.next());
        }
        animals.add(animal);
        animal.printNew();
      } else if (instruction.equals("add")) {
        String foodType = sc.next();
        Food food;
        if (foodType.equals("cheese")) {
          food = new Cheese(sc.next());
        } else if (foodType.equals("tuna")) {
          food = new Tuna(sc.next());
        } else {
          food = new Chocolate(sc.next() + " " + sc.next());
        }
        foods.add(food);
        food.printAdd();
      } else if (instruction.equals("eat")) {
        Collections.sort(animals, new AnimalSort());
        for (Animal animal : animals) {
          animal.eat(foods);
        }
      } else {
        Collections.sort(animals, new AnimalSort());
        for (Animal animal : animals) {
          animal.hello();
        }
      }
    }
  }
}

abstract class Animal {
  protected String name;
  protected int appetite;
  protected String label; // name(colour) for cat and name for dog
  protected String type1;
  protected String type2;

  // constructor
  public Animal(String name, int appetite) {
    this.name = name;
    this.appetite = appetite;
  }

  abstract public void printNew();
  abstract public void hello();

  public void eat(LinkedList<Food> foods) {
    int i = 0;
    while (i < foods.size()) {
      Food food = foods.get(i);
      if (appetite <= 0) {
        break;
      }
      if (food.type.equals(type1) || food.type.equals(type2)) {
        System.out.println(label + " eats " + food.brand + " " + food.type);
        this.appetite--;
        foods.remove(food);
        i--;
      }
      i++;
    }
  }
}

class AnimalSort implements Comparator<Animal> {
  public int compare(Animal a, Animal b) {
    return a.name.compareTo(b.name);
  }
}

class Cat extends Animal {
  private String colour;
  private boolean lazy = false;

  // constructor
  public Cat(String name, int appetite, String colour) {
    super(name, appetite);
    this.colour = colour;
    super.label = name + "(" + colour + ")";
    super.type1 = "tuna";
    super.type2 = "chocolate";
  }

  public void printNew() {
    System.out.println(name + "(" + colour + ") was created");
  }

  public void hello() {
    if (lazy) {
      System.out.println(label + " is lazy");
      lazy = false;
    } else {
      System.out.println(label + " says meow and gets lazy");
      lazy = !lazy;
    }
  }
}

class Dog extends Animal {
  private String sound;
  private String str;

  // constructor
  public Dog(String name, int appetite, String sound) {
    super(name, appetite);
    this.sound = sound;
    super.label = name;
    super.type1 = "tuna";
    super.type2 = "cheese";
    this.str = label + " says " + sound;
  }

  public void printNew() {
    System.out.println(name + " was created");
  }

  public void hello() {
    System.out.println(str);
    str += sound;
  }
}

abstract class Food {
  protected String brand;
  protected String type;

  // constructor
  public Food(String brand) {
    this.brand = brand;
  }

  abstract public void printAdd();
}

class Cheese extends Food {
  public Cheese(String brand) {
    super(brand);
    type = "cheese";
  }

  public void printAdd() {
    System.out.println(super.brand + " cheese was added");
  }
}

class Tuna extends Food {
  public Tuna(String brand) {
    super(brand);
    type = "tuna";
  }

  public void printAdd() {
    System.out.println(super.brand + " tuna was added");
  }
}

class Chocolate extends Food {
  public Chocolate(String brand) {
    super(brand);
    type = "chocolate";
  }

  public void printAdd() {
    System.out.println(super.brand + " chocolate was added");
  }
}
