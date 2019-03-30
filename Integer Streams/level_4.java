import java.util.Scanner;
import java.util.stream.*;
import java.util.OptionalDouble;

class Main {
    static OptionalDouble variance(int[] array) {
        if (array.length < 2) {
            return OptionalDouble.empty();
        } else {
            int n = array.length;
            IntStream s = IntStream.of(array);
            IntStream s2 = IntStream.of(array);
            double mean = s.average().getAsDouble();
            double e2 = s2.map(x -> x * x)
                .average()
                .getAsDouble();
            return OptionalDouble.of((e2 - mean * mean) * n / (n - 1));
        }
    }
}
