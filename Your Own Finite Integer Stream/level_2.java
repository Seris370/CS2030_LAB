import java.util.*;

interface MyIntStream {
    static MyIntStream of(int... values){
        return MyIntStreamImpl.of(values);
    };

    static MyIntStream range(int start, int end){
        return MyIntStreamImpl.range(start, end);
    };

    static MyIntStream rangeClosed(int start, int end){
        return MyIntStreamImpl.rangeClosed(start, end);
    };

    public long count();

    public int sum();

    public OptionalDouble average();

    public OptionalInt max();

    public OptionalInt min();
}

import java.util.*;
import java.util.function.*;

public class MyIntStreamImpl implements MyIntStream {
    ArrayList<Integer> list = new ArrayList<>();

    public MyIntStreamImpl(ArrayList<Integer> list) {
        this.list = list;
    }

    public String toString() {
        String s = "[";
        for (int i = 0; i < this.list.size(); i++) {
            s += this.list.get(i);
            if (i < this.list.size() - 1) {
                s += ", ";
            }
        }
        s += "]";
        return s;
    }

    public static MyIntStreamImpl of(int ... values) {
        ArrayList<Integer> l = new ArrayList<>();
        for (int i : values) {
            l.add(i);
        }
        return new MyIntStreamImpl(l);
    }

    public static MyIntStream range(int start, int end) {
        ArrayList<Integer> l = new ArrayList<>();
        for (int i = start; i < end; i++) {
            l.add(i);
        }
        return new MyIntStreamImpl(l);
    }

    public static MyIntStream rangeClosed(int start, int end) {
        ArrayList<Integer> l = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            l.add(i);
        }
        return new MyIntStreamImpl(l);
    }

    public long count() {
        long c = 0;
        for (Integer i : this.list) {
            c++;
        }
        return c;
    }

    public int sum() {
        int s = 0;
        for (int i : this.list) {
            s += i;
        }
        return s;
    }

    public OptionalDouble average() {
        if (this.list.size() == 0) {
            return OptionalDouble.empty();
        } else {
            double mean = (double)this.sum() / (double) this.count();
            return OptionalDouble.of(mean);
        }
    }

    public OptionalInt max() {
        if (this.list.size() == 0) {
            return OptionalInt.empty();
        } else {
            int m = this.list.get(0);
            for (int i : this.list) {
                if (i > m) {
                    m = i;
                }
            }
            return OptionalInt.of(m);
        }
    }

    public OptionalInt min() {
        if (this.list.size() == 0) {
            return OptionalInt.empty();
        } else {
            int m = this.list.get(0);
            for (int i : this.list) {
                if (i < m) {
                    m = i;
                }
            }
            return OptionalInt.of(m);
        }
    }

    public MyIntStreamImpl forEach(IntConsumer action) {
        ArrayList<Integer> l = new ArrayList<>(this.list);
        for (int i : l) {
            action.accept(i);
        }
        return new MyIntStreamImpl(l);
    }
}
