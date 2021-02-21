package org.gourav.generic;

import java.util.List;
import java.util.function.Predicate;

public class MethodGeneric {

    public <T,E, V> T print(T t, E e,V v ){
        System.out.println(t);
        System.out.println(e);
        System.out.println(v);
        return t;

    }

    public static void comparingString(List<String> list, Predicate<String> predicate){
        list.stream().filter(n -> predicate.test(n)).forEach(System.out::println);
    }

    public static void comparingString(List<String> list, String predicate){
        list.stream().filter(n -> n.contains(predicate)).forEach(System.out::println);
    }
}
