package ds;
public class Taskas {
    public Langelis vieta;
    String pavadinimas;
    public Taskas(String p, Langelis l){
        pavadinimas = p;
        vieta = l;
    }
    public void eik(Kryptys k){
        if(vieta.galiEiti[k.ordinal()]){
            vieta = vieta.kaimynai[k.ordinal()];
        }
    }
    public void kurEsi(){
        System.out.println(pavadinimas+": "+vieta.eil+", "+vieta.st);
    }
}
