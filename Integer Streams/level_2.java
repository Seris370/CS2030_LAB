import java.util.Scanner;
import java.util.stream.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        boolean flag = isSquare(num);
        System.out.println(flag);
    }

    static boolean isSquare(int n) {
        double sqr = Math.sqrt((double)n);
        return sqr * sqr == (double)n;
    }

    static boolean isPerfect(int n) {
        int sum = IntStream.rangeClosed(1, n - 1)
            .filter(x -> n % x == 0)
            .sum();
        return sum == n;
    }
}
