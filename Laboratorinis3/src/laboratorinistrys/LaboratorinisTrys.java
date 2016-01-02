/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package laboratorinistrys;

//pasinaudojau https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html esanciu kodu.
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
/**
 *
 * IIF-13 Pauliaus Kupetčio. Std nr: 20133163
 * 
 * 
 */

import java.io.Serializable;
class Pastatas implements Comparable, Serializable{
    String Adresas;
    int AukstuSk;
    int StatMetai;
    int butai;
    
    public Pastatas(){
        this.Adresas = "";
        this.AukstuSk = 0;
        this.StatMetai = 0;
        this.butai = 0;
    }
    public Pastatas(String Adresas, int AukstuSk, int StatMetai, int butai){
        this.Adresas = Adresas;
        this.AukstuSk = AukstuSk;
        this.StatMetai = StatMetai;
        this.butai = butai;
    }
    
    public int KiekAukstu(){
    return this.AukstuSk;
    }
    
    public boolean ArEgzistuojaAukstas(int aukstas){
        if(aukstas <= this.AukstuSk){
            System.out.println("Aukstas" + aukstas + "Egzistuoja");
            return true;
        } else {
            System.out.println("Aukstas" + aukstas + "neegsistuoja");
            return false;
        }
    }
    
    public void InfoIsvedimas(){
        System.out.println(this.Adresas + " | " + this.AukstuSk + " | " + this.StatMetai + " | " + this.butai);
    }
    
    @Override
    public String toString() {
        return "Pastatas{"+"Adresas="+Adresas+ ", AukstuSk=" + AukstuSk +
                ", StatMetai=" + StatMetai + ", butai=" + butai + ")";     
    }
    
    @Override
    public int compareTo(Object o){
        String sis = Adresas + " " + AukstuSk + " " + StatMetai + " " + butai;
        Pastatas lyg = (Pastatas) o;
        String kitas = lyg.Adresas + " " + lyg.AukstuSk + " " + lyg.StatMetai + " " + lyg.butai;
        return sis.compareTo(kitas);
    }
}


public class LaboratorinisTrys {
    
    public static int WordsAmount(String s) throws Exception {
        
        //kodas paimtas is https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
        URL oracle = new URL("http://www.puzzlers.org/pub/wordlists/pocket.txt");
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));
        int sum=0;
        
        String inputLine;
        while ((inputLine = in.readLine()) != null){
            if(inputLine.contains(s) == true){
                sum++;
            }
        }
        in.close();
        return sum;
    }
   
    public static void Error(Exception e) {
        System.out.println("Something Wrong:" + e.getMessage());
    }

    public static void main(String[] args) {
        try{
            int sum;
            String VardoPirmosiosRaides = "pau";
            sum = WordsAmount(VardoPirmosiosRaides);
            System.out.println("Amount of words with letters `"+ VardoPirmosiosRaides + "` : " + sum);
            
            Scanner klv = new Scanner(System.in);
          
            
            List<Pastatas> list = new ArrayList();
            
            
            while(true){
                try{
                    System.out.println("Ka norit atlikti?\n"
                            + "1) prideti pastata naudodamiesi klaviatura?\n"
                            + "2) prideti pastata is failo\n"
                            + "3) Spausdinti visa objektu sarasa\n"
                            + "4) Irasyti objekta i faila\n"
                            + "5) baigti darba\n");
                    int k = klv.nextInt();
                    klv.nextLine();
                    
                    switch(k){
                        case 1: {
                            Pastatas P = InputFromKeyboard(klv);
                            list.add(P);
                        } break;
                        case 2: {
                            System.out.println("Iveskite failo pavadinima");
                            String fail = klv.nextLine();
                            readFromFile(list, fail);
                        } break;
                        case 3: {
                            Collections.sort(list);
                            int nr = 0;
                            for(Pastatas p : list) {
                                System.out.println(++nr + ": " +p);
                            }
                        } break;
                        case 4: {
                            System.out.println("Kelinta pastata norite irasyti: ");
                            int nr = klv.nextInt();
                            System.out.println("Koks failo pavadinimas: ");
                            String pav =  klv.nextLine();
                            writeToFile(list.get(nr-1),pav);
                        } break;
                        case 5: {
                            klv.close();
                            System.exit(0);
                        } break;
                    }
                    
                } catch (Exception e){
                    Error(e);
                }
                
            }
            
            
        } catch(Exception e){
            Error(e);
        }
    }
    
    private static Pastatas readObjectFromFile(String pav) throws Exception {
        ObjectInputStream fail = new ObjectInputStream(new FileInputStream(pav));
        Pastatas P = (Pastatas) fail.readObject();
        fail.close();
        return P;
    }
    
    private static void writeToFile(Pastatas P, String FName) throws Exception{
        ObjectOutputStream fail = new ObjectOutputStream(new FileOutputStream(FName));
        fail.writeObject(P);
        fail.close();
    }
    
    private static void readFromFile(List<Pastatas> s, String FName) throws Exception{
        File failas = new File(FName);
        Scanner fl = new Scanner(failas);
        fl.useDelimiter(";");
        while(fl.hasNext()){
            String eilute = fl.nextLine();
            String[] mas = eilute.split(";");
            int aukstuSk = Integer.parseInt(mas[1].trim());
            int StatMetai = Integer.parseInt(mas[2].trim());
            int butai = Integer.parseInt(mas[3].trim());
            Pastatas naujas = new Pastatas(
                    mas[0].trim(),
                    aukstuSk,
                    StatMetai,
                    butai
            );
            s.add(naujas);
        }
                
    }

    private static Pastatas InputFromKeyboard(Scanner sc) throws Exception {
        System.out.println("Adresas");
        String adresas = sc.nextLine();
        System.out.println("Aukstu skaicius");
        int AukstuSk = sc.nextInt();
        System.out.println("Statybos metai");
        int StatMetai = sc.nextInt();
        System.out.println("Butu skaicius");
        int butai = sc.nextInt();
        
        return new Pastatas(adresas, AukstuSk, StatMetai, butai);
    }
}
