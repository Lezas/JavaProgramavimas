/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package papildoma;

/**
 *
 * @author Vartotojas
 */

public class Medis {

    Lapas virsune;

    public void pridetiLapa(int sk) {
        if (virsune != null) {
            virsune.pridetiLapa(sk, virsune);
        } else {
            virsune = new Lapas(sk, null);
        }
    }

public void salintiLapa(int sk) {
        Lapas sl = rastiLapa(sk);
        if (sl.desine == null && sl.kaire == null) {
            if (sk > sl.tevas.reiksme) {
                sl.tevas.desine = null;
            } else if (sk < sl.tevas.reiksme) {
                sl.tevas.kaire = null;
            }    
        }
        else {
            Lapas desine = sl.desine;
            Lapas kaire = sl.kaire;
           
            if (sl.tevas == null) {
                sl = desine;
                desine.tevas = null;
                virsune = desine;
            }
            else if (sl.tevas.kaire == sl) {
                sl.tevas.kaire = desine;
                desine.tevas = sl.tevas;
                sl = sl.tevas.kaire;
            } 
            else {
                sl.tevas.desine = desine;
                desine.tevas = sl.tevas;
                sl = sl.tevas.desine;
            }

            while (sl.kaire != null) {
                sl = sl.kaire;
            }
            sl.kaire = kaire;
            if (kaire != null) {
                kaire.tevas = sl;
            }
         
        }
    }

    public Lapas rastiLapa(int sk) {
        if (virsune != null) {
            return virsune.rasti(sk);
        }
        return null;
    }

    public void spausdinti() {
        if (virsune != null) {
            virsune.spausdintiLapa("");
            System.out.println("------------------");
        } else {
            System.out.println("Medis tuscias");
        }
    }
}
