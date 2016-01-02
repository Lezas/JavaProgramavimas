package papildomauzduotis1;
import java.io.Serializable;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Paulius Kupetis IIf-13
 */

class Studentas{
    
}
public class PapildomaUzduotis1 {

    public static void Error(Exception e) {
        System.out.println("Something Wrong:" + e.getMessage());
    }
    
    public static void main(String[] args) {
        try{
            List<String> eilutes = new ArrayList();
            String pav = "stud.csv";
            File failas = new File(pav);
            Scanner fl = new Scanner(failas);
            while(fl.hasNext()){
                String eilute = fl.nextLine();

                    eilutes.add(eilute);
                   
            }
            fl.close();
            PrintWriter writer = new PrintWriter("stud.csv", "UTF-8");
            
            for(String p : eilutes) {
                String[] mas = p.split(";");
                //System.out.println(mas[3]);
                int numeris = Integer.parseInt(mas[3])%10000;
                int first = (numeris-numeris%1000)/1000;
                System.out.print(mas.length);
                
                numeris = numeris%1000;
                int second=(numeris-numeris%100)/100;
                
                numeris = numeris%100;
                int third = (numeris-numeris%10)/10;
        
                int fourth = numeris%10;
                
                int min = Math.min(Math.min(Math.min(first,second),third),fourth);
                int max = Math.max(Math.max(Math.max(first,second),third),fourth);

                writer.print(mas[0]+";"+mas[1]+";"+mas[2]+";"+mas[3]+";"+first+";");
                if(second == first){
                    second++;
                    writer.print(second+";");
                    second--;
                } else{
                    writer.print(second+";");
                }
                if(second == first || second == third || third==first){
                    max++;
                    writer.print(max+";");
                } else {
                    writer.print(third+";");
                }
                if(second == first || second == third || third==first || second == fourth || fourth == first || fourth == third){
                    min--;
                    writer.print(min+";");
                } else {
                    writer.print(fourth+";");
                }
                for(int i = 4;i<mas.length;i++){
                    writer.print(mas[i]);
                }
                writer.println();  
            }
            
            writer.close();
        } catch (Exception e){
            Error(e);
        }
    }
    
}
