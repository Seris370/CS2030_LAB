import java.util.*;
import java.io.*;

class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader scroll = new BufferedReader(new FileReader(args[0]));
    String str;
    while ((str = scroll.readLine()) != null) {
      System.out.println(str);
    }
  }
}
