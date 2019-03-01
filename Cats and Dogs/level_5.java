import java.util.*;
import java.io.*;

class IllegalInstructionException extends Exception {
  public IllegalInstructionException(String message){
     super(message);
  }
}

class Main {
  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(new FileReader(args[0]));
    String str;
    LinkedList<Food> foods = new LinkedList<>();
    LinkedList<Animal> animals = new LinkedList<>();
    while (sc.hasNext()) {
      try{
      String[] words = sc.nextLine().split(" ");
      String instruction = words[0];
      if (instruction.equals("new")) {
        Animal animal;
        String animalType = words[1];
        if (animalType.equals("cat")) {
          if (words.length < 4) {
            throw new IllegalInstructionException("cs2030.catsanddogs.IllegalInstructionException: Too few arguments");
          }
          animal = new Cat(words[2], Integer.parseInt(words[3]), words[4]);
        } else if (animalType.equals("dog")){
          if (words.length < 4) {
            throw new IllegalInstructionException("cs2030.catsanddogs.IllegalInstructionException: Too few arguments");
          }
          animal = new Dog(words[2], Integer.parseInt(words[3]), words[4]);
        } else {
          throw new IllegalInstructionException("cs2030.catsanddogs.IllegalInstructionException: " + animalType + " is not a valid species");
        }
        animals.add(animal);
        animal.printNew();
      } else if (instruction.equals("add")) {
        String foodType = words[1];
        Food food;
        if (foodType.equals("cheese")) {
          if (words.length < 3) {
            throw new IllegalInstructionException("cs2030.catsanddogs.IllegalInstructionException: Too few arguments");
          }
          food = new Cheese(words[2]);
        } else if (foodType.equals("tuna")) {
          if (words.length < 3) {
            throw new IllegalInstructionException("cs2030.catsanddogs.IllegalInstructionException: Too few arguments");
          }
          food = new Tuna(words[2]);
        } else if (foodType.equals("chocolate")){
          if (words.length < 4) {
            throw new IllegalInstructionException("cs2030.catsanddogs.IllegalInstructionException: Too few arguments");
          }
          food = new Chocolate(words[2] + " " + words[3]);
        } else {
          throw new IllegalInstructionException("cs2030.catsanddogs.IllegalInstructionException: " + foodType + " is not a valid food type");
        }
        foods.add(food);
        food.printAdd();
      } else if (instruction.equals("eat")) {
        Collections.sort(animals, new AnimalSort());
        for (Animal animal : animals) {
          animal.eat(foods);
        }
      } else if (instruction.equals("hello")){
        Collections.sort(animals, new AnimalSort());
        for (Animal animal : animals) {
          animal.hello();
        }
      } else {
        throw new IllegalInstructionException("cs2030.catsanddogs.IllegalInstructionException: " + instruction + " is not a valid instruction");
      }
    }
    catch (IllegalInstructionException ex) {
      System.err.println(ex.getMessage());
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
