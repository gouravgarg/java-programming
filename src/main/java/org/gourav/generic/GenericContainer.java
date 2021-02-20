package org.gourav.generic;

public class GenericContainer<T> {

    T t;

    public GenericContainer(T t) {
        this.t = t;
    }

    public GenericContainer() {
    }

    public T getT(){
        return t;
    }

    public void setT(T  t){
        this.t=t;
    }
}

