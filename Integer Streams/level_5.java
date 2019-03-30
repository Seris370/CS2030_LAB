import java.util.Scanner;
import java.util.stream.*;
import java.util.OptionalDouble;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        IntStream s = primeFactors(x);
        System.out.print("Prime factors of " + x + " are:");
        s.forEach(i -> System.out.print(" " + i));
        System.out.println();
    }

    static IntStream factors(int x) {
        IntStream s = IntStream.rangeClosed(1, x)
            .filter(i -> x % i == 0);
        return s;
    }

    static IntStream primeFactors(int x) {
        IntStream s = factors(x)
            .filter(i -> factors(i).count() == 2);
        return s;
    }
}
