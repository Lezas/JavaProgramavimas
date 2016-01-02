
package javaapplication1;

/**
 *  Paulius Kupetis IIF-13
 *  Mano studento nr: 20133163
        5 skaičius: 3
        6 skaičius : 1
        7 skaičius : 6
        8 skaičius : 3
 * 
 */
import java.util.Random;
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Random random = new Random();
        
        
        
        
        int START = 3;
        int END = 6;
        int matrica[][] = new int[31][63];
        float eilVidurkiai[] = new float[31];
        
        int sumOfEquals = 0;
        
        float suma = 0f;
        
        
        for (int i=0;i<matrica.length;i++){
            for(int o=0;o<matrica[0].length;o++){
                matrica[i][o] = RandomInteger(START, END, random);
                suma= suma+ matrica[i][o];
                
                if(matrica[i][o]==3){
                    sumOfEquals++;
                }
                log(matrica[i][o]);
                log(" ");
            }
            eilVidurkiai[i] = suma/31;
            suma =0;
            System.out.println();
        }
        
        log("Eiluciu vidurkiai");
        
        float min = eilVidurkiai[0];
        float a = 1;
        for(int i=0;i<eilVidurkiai.length;i++){
            log(eilVidurkiai[i]+" ");
            if(eilVidurkiai[i] != a){
                if(eilVidurkiai[i] < min){
                    min=eilVidurkiai[i];
                }
                
            }
        }
        
        System.out.println();
        
        log("Skaiciu kiekis masyve,kurie yra lygus 3");
        log(sumOfEquals);
        System.out.println("");
        System.out.println("Maziausias vienmacio masyvo skaicius, neitraukiant a reiksmes: " + min);
        
        //Reikia atlikti 3 uzduoti. isspausdinti a eilutes reiksme didejimo tvarka. a reiksme maziausias skaicius, tai a=1
        System.out.println();
        log("Pirmosios eilutes skaiciai isdestyti didejimo tvarka");
        System.out.println();
        
        int temp[] = new int[matrica.length];
        for(int i=0;i<matrica.length;i++){
            
            temp[i]=matrica[i][1];
        }
        
        for(int i=0;i<matrica.length-1;i++){
            for(int o=i+1;o<matrica.length;o++){
                if(temp[i] > temp[o]){
                    int tarp = temp[o];
                    temp[o] = temp[i];
                    temp[i]=tarp;
                }
            }
        }
        
        for(int i=0;i<matrica.length;i++){
            log(temp[i] + " ");
        }
        
        //Reikia atlikti 1 uzduoti. Reikia atspausdinti vienmacio masyvo maziausia elemento, neitraukiant a (maziausias skaicius is stud. Nr.)
        System.out.println();
        log("Vienmacio masyvo maziausias elementas, neitraukiant a");
        System.out.println();
        
        
        //6 uzduotis. Suskaičiuoti ir atspausdinti a stulpelio kas b-tojo elemento sumą. a = 1, b = 6
        System.out.println();
        log("a stulpelio, kas b-tojo elemento suma");
        System.out.println();
        suma = 0;
        
        for(int i=0;i<matrica[0].length;i+=6){
            suma+=matrica[1][i];
        }
        log(suma);
        
        //Mano paskutinis stud. numerio skaitmuo yra 1. Nr. 1 uzduoty jau padariau. 
        //pagal aprasyma supratau kad reikia daryti 7,8 ar 9
        //Pasirinkau 8 uzduoti:
        //reikia atspausdinti kiek dvimaciame masyve yra nelyginiu skaiciu, neitraukiant a stulpelio. a = 1
        System.out.println();
        log("elyginiu skaiciu kiekis matricoje, neitraukiant a stulpelio");
        System.out.println();
        
        int a=1;
        suma = 0;
        
        for(int i=0;i<matrica.length;i++){
            for(int o=0;o<matrica[0].length;o++){
                if(o!=a){
                    if((matrica[i][o] % 2) > 0){
                        suma++;
                    }
                }
            }
        }
        
        log("Nelyginiu skaiciu kiekis matricoje:" + suma);
        System.out.println();
        
        
        
    }
    
    
    //funkcija paimta is ineterneto. 
    private static int RandomInteger(int aStart, int aEnd, Random aRandom){
        if (aStart > aEnd) {
          throw new IllegalArgumentException("Start cannot exceed End.");
        }
        //get the range, casting to long to avoid overflow problems
        long range = (long)aEnd - (long)aStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long)(range * aRandom.nextDouble());
        int randomNumber =  (int)(fraction + aStart);    
        return randomNumber;
    }
    
    private static void log(Object aMsg){
    System.out.print(String.valueOf(aMsg));
  }
}
