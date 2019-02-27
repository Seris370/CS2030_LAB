import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int id = 0;

        while(sc.next().equals("add")) {
            String type = sc.next();
            String desc = sc.next();
            int price = sc.nextInt();
            System.out.printf("#%d %s: %s (%d)\n", id, type, desc, price);
            id++;
        }
    }
}
