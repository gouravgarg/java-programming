package org.gourav;

public class WeirdLoop {
    private static final int i = 99;
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) { // line n1
            System.out.print(i);
            i++; // line n2
            break;
        }
        System.out.print(i);

        String s = "abc";
        s = s + 1 + 2;
        s = s + (2 + 2);
        s.concat("1").concat("2");
        s.concat("" + 2 + 2);
        System.out.print(s);
    }    
}