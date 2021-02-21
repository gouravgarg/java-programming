package org.gourav.generic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestMethodGeneric {


    @Test
    void testComparingPredicateString(){
        List<String> bookList = new ArrayList<>();
        bookList.add("Java 8 Recipes");
        bookList.add("Java EE 7 Recipes");
        bookList.add("Introducing Java EE 7");
        bookList.add("JavaFX 8:  Introduction By Example");
        MethodGeneric.comparingString(bookList,(n)->n.contains("Java EE"));
    }

    @Test
    void testComparingString(){
        List<String> bookList = new ArrayList<>();
        bookList.add("Java 8 Recipes");
        bookList.add("Java EE 7 Recipes");
        bookList.add("Introducing Java EE 7");
        bookList.add("JavaFX 8:  Introduction By Example");
        MethodGeneric.comparingString(bookList,"Java EE");
    }

    @Test
    void print(){
        MethodGeneric wildcard =new MethodGeneric();
        wildcard.print(1,"A",1.2F);
    }
}
