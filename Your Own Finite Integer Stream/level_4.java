package cs2030.mystream;

import java.util.*;
import java.util.function.*;
import cs2030.mystream.MyIntStreamImpl;

public interface MyIntStream {
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

    public MyIntStream limit(int maxSize);

    public MyIntStream distinct();

    public MyIntStream filter(IntPredicate predicate);

    public MyIntStream map(IntUnaryOperator mapper);

    public void forEach(IntConsumer action);

    public int reduce(int identity, IntBinaryOperator op);

    public OptionalInt reduce(IntBinaryOperator op);
}

package cs2030.mystream;

import java.util.*;
import java.util.function.*;
import cs2030.mystream.MyIntStream;

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

    public void forEach(IntConsumer action) {
        for (int i : this.list) {
            action.accept(i);
        }
    }

    public MyIntStreamImpl limit(int maxSize) {
        if (this.list.size() <= maxSize) {
            return this;
        } else {
            ArrayList<Integer> l = new ArrayList<>();
            for (int i = 0; i < maxSize; i++) {
                l.add(this.list.get(i));
            }
            return new MyIntStreamImpl(l);
        }
    }

    public MyIntStreamImpl distinct() {
        ArrayList<Integer> l = new ArrayList<>();
        for (int i : this.list) {
            if (!l.contains(i)) {
                l.add(i);
            }
        }
        return new MyIntStreamImpl(l);
    }

    public MyIntStreamImpl filter(IntPredicate predicate) {
        ArrayList<Integer> l = new ArrayList<>();
        for (int i : this.list) {
            if (predicate.test(i)) {
                l.add(i);
            }
        }
        return new MyIntStreamImpl(l);
    }

    public MyIntStreamImpl map(IntUnaryOperator mapper) {
        ArrayList<Integer> l = new ArrayList<>();
        for (int i : this.list) {
            l.add(mapper.applyAsInt(i));
        }
        return new MyIntStreamImpl(l);
    }

    public int reduce(int identity, IntBinaryOperator op) {
        if (this.list.size() == 0) {
            return identity;
        } else {
            int result = identity;
            for (int i : this.list) {
                result = op.applyAsInt(result, i);
            }
            return result;
        }
    }

    public OptionalInt reduce(IntBinaryOperator op) {
        if (this.list.size() < 2) {
            return OptionalInt.empty();
        } else {
            int result = this.list.get(0);
            for (int i = 1; i < this.list.size(); i++) {
                result = op.applyAsInt(result, this.list.get(i));
            }
            return OptionalInt.of(result);
        }
    }
}
