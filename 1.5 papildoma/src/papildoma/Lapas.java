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
public class Lapas {

    int reiksme;
    Lapas kaire;
    Lapas desine;
    Lapas tevas;

    public Lapas(int l, Lapas t) {
        reiksme = l;
        tevas = t;
    }

    public void pridetiLapa(int sk, Lapas t) {
        if (sk > reiksme) {
            if (desine != null) {
                desine.pridetiLapa(sk, this.desine);
            } else {
                desine = new Lapas(sk, t);
            }
        } else if (sk < reiksme) {
            if (kaire != null) {
                kaire.pridetiLapa(sk, this.kaire);
            } else {
                kaire = new Lapas(sk, t);
            }
        }
    }

    public void spausdintiLapa(String tabas) {
        if (kaire != null) {
            kaire.spausdintiLapa(tabas + "\t");
        }
        System.out.println(tabas + " " + this.reiksme+" - "+((tevas!=null)?tevas.reiksme:0));
        if (desine != null) {
            desine.spausdintiLapa(tabas + "\t");
        }
    }

    public Lapas rasti(int sk) {
        if (this.reiksme == sk) {
            return this;
        } else {
            if (sk > this.reiksme && desine != null) {
                return desine.rasti(sk);
            } else if (sk < this.reiksme && kaire != null) {
                return kaire.rasti(sk);
            }
        }
        return null;
    }
    


    @Override
    public String toString() {
        return "Lapas{" + "reiksme=" + reiksme+" "+tevas.reiksme + '}';
    }
    
}
