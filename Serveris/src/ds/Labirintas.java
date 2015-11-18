package ds;

import java.util.ArrayList;

public class Labirintas {

    int aukstis;
    int plotis;
    Langelis[][] langeliai;
    public ArrayList<Taskas> taskai = new ArrayList<>();

    
    public Labirintas(int a, int p) {
        aukstis = a;
        plotis = p;
        langeliai = new Langelis[a][p];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < p; j++) {
                langeliai[i][j] = new Langelis(i, j);
                if (i > 0) {
                    langeliai[i][j].kaimynai[Kryptys.VIRSUS.ordinal()] = langeliai[i - 1][j];
                    langeliai[i][j].galiEiti[Kryptys.VIRSUS.ordinal()] = true;
                    langeliai[i - 1][j].kaimynai[Kryptys.APACIA.ordinal()] = langeliai[i][j];
                    langeliai[i - 1][j].galiEiti[Kryptys.APACIA.ordinal()] = true;
                }
                if (j > 0) {
                    langeliai[i][j].kaimynai[Kryptys.KAIRE.ordinal()] = langeliai[i][j - 1];
                    langeliai[i][j].galiEiti[Kryptys.KAIRE.ordinal()] = true;
                    langeliai[i][j - 1].kaimynai[Kryptys.DESINE.ordinal()] = langeliai[i][j];
                    langeliai[i][j - 1].galiEiti[Kryptys.DESINE.ordinal()] = true;
                }
            }
        }
    }

    public Taskas pridetiTaska(String p, int e, int s) {
        if (e >= 0 && e < aukstis && s >= 0 && s < plotis) {
            Taskas t = new Taskas(p, langeliai[e][s]);
            taskai.add(t);
            return t;
        }
        return null;
    }
    
    public void SalintiTaska(String name){
        int i =0;
        for (Taskas t : taskai) {
            if(t.pavadinimas.equals(name)){
                System.out.println("Taskas trinamas");
                taskai.remove(i);
                break;
            }
            i++;
        }
    }
    
    public void KeistiTaskoVieta(int TaskoNumeris, int Eilute, int Stulpelis){
        Taskas t = taskai.get(TaskoNumeris);
                
        if (Eilute >= 0 && Eilute < aukstis && Stulpelis >= 0 && Stulpelis < plotis) {
            Taskas t2 = new Taskas(t.pavadinimas, langeliai[Eilute][Stulpelis]);
            taskai.set(TaskoNumeris, t2);
        }
    }
    
    public void KeistiTaskoVietaPagalVarda(String name, int Eilute, int Stulpelis){
        
        int i =0;
        int nr = -1;
        for (Taskas t : taskai) {
            
            if(t.pavadinimas.equals(name) == true){
                nr = i;
                break;
            }
            i++;
        }
        
       
        if(nr > 0){

            if (Eilute >= 0 && Eilute < aukstis && Stulpelis >= 0 && Stulpelis < plotis) {
                Taskas t2 = new Taskas(name, langeliai[Eilute][Stulpelis]);
                taskai.set(nr, t2);
            }
        } 
        
    }

    public void keistiSiena(int e, int s, Kryptys k) {
        if (e >= 0 && e < aukstis && s >= 0 && s < plotis) {
            Langelis l = langeliai[e][s];
            if (l.kaimynai[k.ordinal()] != null) {
                l.galiEiti[k.ordinal()] = !l.galiEiti[k.ordinal()];
                l.kaimynai[k.ordinal()].galiEiti[(k.ordinal() + 2) % 4]
                        = l.galiEiti[k.ordinal()];
            }
        }
    }

    public void spausdinti() {
        for (int i = 0; i < aukstis; i++) {
            for (int j = 0; j < plotis; j++) {
                Langelis l = langeliai[i][j];
                System.out.println(i + " " + j + ": " + l.galiEiti[0] + " " + l.galiEiti[1] + " " + l.galiEiti[2] + " " + l.galiEiti[3]);
            }
        }
    }
    
    public int langeliuSkaiciavimas(int sk){
        int LangeliuSk = 0;
        for (int i = 0; i < aukstis; i++) {
            for (int j = 0; j < plotis; j++) {
                Langelis l = langeliai[i][j];
                int sienuSk =0;
                if(l.galiEiti[0] == false){
                    sienuSk++;
                }
                if(l.galiEiti[1] == false){
                    sienuSk++;
                }
                if(l.galiEiti[2] == false){
                    sienuSk++;
                }
                if(l.galiEiti[3] == false){
                    sienuSk++;
                }
                if(sienuSk == sk){
                    LangeliuSk++;
                }
            }
        }
        return LangeliuSk;
    }
    

    public int getAukstis() {
        return aukstis;
    }

    public int getPlotis() {
        return plotis;
    }

    public Langelis[][] getLangeliai() {
        return langeliai;
    }

    public ArrayList<Taskas> getTaskai() {
        return taskai;
    }
    
    
}
