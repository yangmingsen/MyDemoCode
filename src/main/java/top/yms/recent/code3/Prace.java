package top.yms.recent.code3;

@FunctionalInterface
public interface Prace<T,R> {

    R doOprate(T t);
}
