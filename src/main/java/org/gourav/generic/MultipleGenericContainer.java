package org.gourav.generic;

public class MultipleGenericContainer<T,S> {

    T firstPosition;
    S secondPosition;

    public MultipleGenericContainer(T firstPosition, S secondPosition) {
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;
    }

    public S getSecondPosition() {
        return secondPosition;
    }

    public T getFirstPosition() {
        return firstPosition;
    }

}
