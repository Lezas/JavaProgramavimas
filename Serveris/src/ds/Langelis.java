package ds;
public class Langelis {
    int st, eil;
    Langelis[] kaimynai;
    public boolean[] galiEiti;
    public Langelis(int e, int s){
        eil = e;
        st = s;
        kaimynai = new Langelis[4];
        galiEiti = new boolean[4];
    }

    public int getSt() {
        return st;
    }

    public int getEil() {
        return eil;
    }
    
    public boolean galimaKryptis(Kryptys k){
        return kaimynai[k.ordinal()]!=null;
    }
    
}
