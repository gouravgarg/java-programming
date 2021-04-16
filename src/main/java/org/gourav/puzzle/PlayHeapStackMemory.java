package org.gourav.puzzle;

public class TestDomain {

    private int a = 3;

    private Boolean ready = false;

    public void setA(int a) {
        this.a = a;
    }

    public int getA() {
        return a;
    }

    @Override
    public String toString() {
        return "TestDomain{" +
                "a=" + a +
                '}';
    }

    public void updateIntValue(){//Y
       // System.out.println(new java.util.Date() +" "+Thread.currentThread().getName() + "=" + a + " "+ready );
        ready = !ready;
        a=6;

       // System.out.println(new java.util.Date() +" "+Thread.currentThread().getName() + "=" + a + " "+ready );
    }

    public void validate(){//X
    while(!ready){
        //while(a!=3){
          // System.out.println(new java.util.Date() +" "+Thread.currentThread().getName() + "=" + a);
////            try {
////                Thread.sleep(10);
////            } catch (InterruptedException interruptedException) {
////                interruptedException.printStackTrace();
////            }
//            if(a==6){
//                System.out.println(new java.util.Date() +" "+Thread.currentThread().getName() + " Inside=" + a);
//                break;
//                }
        }
        System.out.println(new java.util.Date() +" "+Thread.currentThread().getName() + " Outside=" + a);
    }

}
