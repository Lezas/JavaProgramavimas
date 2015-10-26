package gijupvz;
public class GijuPvz {

    public static void main(String[] args) throws InterruptedException {
        double x = 0.0;
        double y;
        //= Math.sin(x)*Math.pow(2, x)+x;
        for(; x<Math.PI; x+=0.1){
            y = Math.sin(x)*Math.pow(2, x)+x;
            System.out.println("x: "+x+" y:"+y);
            Thread.sleep(500);
        }
    }  
}
