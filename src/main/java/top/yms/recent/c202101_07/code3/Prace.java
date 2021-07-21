package top.yms.recent.c202101_07.code3;

@FunctionalInterface
public interface Prace<T,R> {

    R doOprate(T t);
}
