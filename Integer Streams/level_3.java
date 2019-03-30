import java.util.Scanner;
import java.util.stream.*;

class Main {
    static long countRepeats(int[] array) {
        int n = array.length;
        int[] sub = IntStream.rangeClosed(0, n - 2)
            .map(i -> array[i + 1] - array[i])
            .toArray();
        long sum = IntStream.rangeClosed(0, n - 3)
            .filter(i -> sub[i] != 0 && sub[i + 1] == 0)
            .count();
        if (sub[0] == 0) {
            sum++;
        }
        return sum;
    }
}
