package org.gourav.generic;

import org.gourav.generic.GenericContainer;
import org.junit.jupiter.api.Test;

public class TestGenericContainer {



    @Test
    void testGet(){
        GenericContainer<Integer> genericContainer =new GenericContainer<>();
        int expected = 1;
        genericContainer.setT(expected);
        org.junit.jupiter.api.Assertions.assertEquals(expected,genericContainer.getT());

    }

    @Test
    void testGetParameterConstructor(){
        String expected ="Hello";
        GenericContainer  genericContainer =new GenericContainer(expected);
        org.junit.jupiter.api.Assertions.assertEquals(expected,genericContainer.getT());

    }
}
