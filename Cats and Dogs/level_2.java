import java.util.*;
import java.io.*;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(new FileReader(args[0]));
    String str;
    while (sc.hasNext()) {
      if (sc.next().equals("new")) {
        if (sc.next().equals("cat")) {
          Cat cat = new Cat(sc.next(), sc.nextInt(), sc.next());
          cat.printCreation();
        } else {
          Dog dog = new Dog(sc.next(), sc.nextInt(), sc.next());
          dog.printCreation();
        }
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

  abstract public void printCreation();
}

class Cat extends Animal {
  private String colour;

  // constructor
  public Cat(String name, int appetite, String colour) {
    super(name, appetite);
    this.colour = colour;
  }

  public void printCreation() {
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

  public void printCreation() {
    System.out.println(name + " was created");
  }
}
