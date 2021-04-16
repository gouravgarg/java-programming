package org.gourav.puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GFG {

    public static void main(String[] args) {
        List<String> list  = new ArrayList<>();
      //  List<String> list  = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("b");
        list = list.subList(0,2);
        System.out.println(list);
        Stream<String> stream =list.stream();
        list.add("F");
        stream.forEach(System.out::println);

        System.out.println(Stream.of(-3,-2,-1,0,1,2,3).max(Math::max).get());
    }
} 