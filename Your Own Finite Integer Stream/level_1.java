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
}

import java.util.*;

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
}
