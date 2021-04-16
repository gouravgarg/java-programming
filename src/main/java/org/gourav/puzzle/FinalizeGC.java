package org.gourav.puzzle;

public class FinalizeGC {

    public static void main(String[] args) {

        String s = "a";
        s= null;

        FinalizeGC finalizeGC =new FinalizeGC();
        finalizeGC =null;

        System.gc();
        System.out.println("EXIT");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Finalize");
    }
}
