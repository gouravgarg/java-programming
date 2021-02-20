package org.gourav.generic;

import org.junit.jupiter.api.Test;

public class TestMultipleGenericContainer {

    @Test
    void testMultipleGenericContainer(){
        int expectedFirst=1;

        String expectedSecond="Hello";

        MultipleGenericContainer<Integer,String> multipleGenericContainer =new MultipleGenericContainer(expectedFirst,expectedSecond);

        org.junit.jupiter.api.Assertions.assertEquals(expectedFirst,multipleGenericContainer.getFirstPosition());
        org.junit.jupiter.api.Assertions.assertEquals(expectedSecond,multipleGenericContainer.getSecondPosition());

    }
}
