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
