/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds;

import java.util.Random;
import vw.Piesinys;
/**
 *
 * @author Vartotojas
 */
public class AutoJudejimas extends Thread{
    Labirintas l;
    int taskas;
    Piesinys p;
    int KiekPaejo=0;
    
    public void ParametruIvedimas(Labirintas l, int taskoNr, Piesinys p){
        this.l = l;
        this.taskas = taskoNr;
        this.p = p;
        this.start();
    }
    @Override
    public void run(){
        int kryptis;
        int SleepTime;
        try {
            Random rand = new Random();

    // nextInt excludes the top value so we have to add 1 to include the top value
       int [][] array;
       array = new int[l.aukstis][l.plotis];
       
        while(true){
            kryptis = rand.nextInt(4);
             SleepTime = rand.nextInt(3) + 1;
            switch (kryptis) {
            case 0:
                l.getTaskai().get(taskas).eik(Kryptys.VIRSUS);
                break;
            case 1:
                l.getTaskai().get(taskas).eik(Kryptys.DESINE);
                break;
            case 2:
                l.getTaskai().get(taskas).eik(Kryptys.APACIA);
                break;
            case 3:
                l.getTaskai().get(taskas).eik(Kryptys.KAIRE);
                break;
            }
            p.repaint();
            array[l.getTaskai().get(taskas).vieta.eil][l.getTaskai().get(taskas).vieta.st]++;
            if(array[l.getTaskai().get(taskas).vieta.eil][l.getTaskai().get(taskas).vieta.st] == 1){
                this.KiekPaejo++;
            }
            
            Thread.sleep(SleepTime*1000);
            
        }
            //b.setEnabled(true);
        } catch (Exception e) {
           
        }
    }
    
    public int KiekPaejo(){
        return this.KiekPaejo;
    }
}
