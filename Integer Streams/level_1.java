import java.util.Scanner;
import java.util.stream.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        boolean flag = isPerfect(num);
        System.out.println(flag);
    }

    static boolean isPerfect(int n) {
        int sum = IntStream.rangeClosed(1, n - 1)
            .filter(x -> n % x == 0)
            .sum();
        return sum == n;
    }
}
