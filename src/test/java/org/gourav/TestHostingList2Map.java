package org.gourav;

import org.gourav.domain.Hosting;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * List 2 Map by java Steam API
 */
public class TestHostingList2Map {

    @Test
    public void testList2Map(){
        List<Hosting> list = new ArrayList<>();
        list.add(new Hosting(1, "liquidweb.com", 80000));
        list.add(new Hosting(2, "linode.com", 90000));
        list.add(new Hosting(3, "digitalocean.com", 120000));
        list.add(new Hosting(4, "aws.amazon.com", 200000));
        list.add(new Hosting(5, "abc.com", 1));

        final Map<Object, Object> map = list.stream().collect(Collectors.toMap(x -> x.id(), x-> x ));
        map.keySet().stream().forEach(x-> System.out.println(x + " " + map.get(x)));

        final Map<Object, Object>  map2 = list.stream().collect(Collectors.toMap(Hosting::id,Hosting::name));
        map2.keySet().stream().forEach(x-> System.out.println(x + " " + map2.get(x)));

        final Map<Object, Object> map3 = list.stream().collect(Collectors.toMap(x -> x.id(), Function.identity()));
        map3.keySet().stream().forEach(x-> System.out.println(x + " " + map3.get(x)));


    }

    /**
     * Duplicate Key to Unique Key
     * Sorting
     * Multiple maps - Value as List
     */
    @Test
    public void testList2MapGroupBy(){
        List<Hosting> list = new ArrayList<>();
        list.add(new Hosting(1, "liquidweb.com", 80000));
        list.add(new Hosting(2, "linode.com", 90000));
        list.add(new Hosting(3, "digitalocean.com", 120000));
        list.add(new Hosting(4, "aws.amazon.com", 200000));
        list.add(new Hosting(5, "abc.com", 1));

        list.add(new Hosting(6, "digitalocean.com", 0));

        Map<String, Hosting> result1 = list.stream().collect(Collectors.toMap(Hosting::name,Function.identity()));

        Map<String, Long> result = list.stream().collect(Collectors.toMap(Hosting::name,Hosting::hosting,(oldValue, newValue)->oldValue));
        System.out.println(result);

        result = list.stream()
                .sorted(Comparator.comparingLong(Hosting::hosting))
                .collect(Collectors.toMap(Hosting::name,Hosting::hosting,(oldValue, newValue)->oldValue, LinkedHashMap::new));
        System.out.println(result);

        result = list.stream()
                .sorted(Comparator.comparingLong(Hosting::hosting).reversed())
                .collect(Collectors.toMap(Hosting::name,Hosting::hosting,(oldValue, newValue)->oldValue, LinkedHashMap::new));
        System.out.println(result);

        Map<String, List<Hosting>> mapsList = list.stream()
                .collect(
                        Collectors.groupingBy
                                (Hosting::name,
                                    Collectors.mapping
                                            (Function.identity(),
                                        Collectors.toList()
                )));
        System.out.println(mapsList);
    }




}
