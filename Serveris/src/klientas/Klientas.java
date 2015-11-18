package klientas;


import klientas.GUI;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Klientas {
    
    Socket soketas;
    PrintWriter out;
    BufferedReader in;

    public static void main(String[] args) {
        try {
            Klientas kl = new Klientas();
            //sukuriam socket tipo obejkta, nurodydami kokiu ip adresu ir kokiu prievadu kreiptis i serveri
            kl.soketas = new Socket("127.0.0.1", 1234);
            //pagal socket tipo objekta gaunas ivesties ir isvesties srautus
            kl.out = new PrintWriter(kl.soketas.getOutputStream(), true);
            kl.in = new BufferedReader(new InputStreamReader(kl.soketas.getInputStream()));
            //dar salia susikuriam skaitymo is klaviaturos srauta gyvam komandu perdavimui
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            
            GUI gvs = new GUI(kl);
            gvs.setVisible(true);
            String userInput;
            //susikuriam atskira gija, kuri skaitys is serverio gaunamus atsakymus ir juos atspausdins komandineje eiluteje
            Thread a = new Thread() {
                public void run() {
                    try {
                        //skaitys amzinai
                        while (true) {
                            String tekstas = kl.in.readLine();
                            if (tekstas.startsWith("nick:")) {
                                String[] spl = tekstas.split(":", 2);
                                //gvs.keistiAntraste(spl[1]);
                            } else
                            if (tekstas.startsWith("in:")) {
                                String[] spl = tekstas.split(":", 4);
                                gvs.pridetiKlienta(spl[1],Integer.parseInt(spl[2]),Integer.parseInt(spl[3]));
                            } else 
                            if (tekstas.startsWith("out:")) {
                                String[] spl = tekstas.split(":", 2);
                                gvs.pasalintiKlienta(spl[1]);
                            } else if(tekstas.startsWith("tp:")){
                                String[] spl = tekstas.split(":", 4);
                                gvs.taskoPadetis(spl[1],Integer.parseInt(spl[2]),Integer.parseInt(spl[3]));
                            }
                            else {
                                gvs.rodytiZinute(tekstas);
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            };
            a.start();
            //cia skaitom duomenis is klaviaturos
            while ((userInput = stdIn.readLine()) != null) {
                kl.out.println(userInput);
                if (userInput.equals("Bye.")) {
                    break;
                }
            }
            //jei baige ivedineti komandas, t.y. parase Bye. tada uzdarom visus srautus ir socket tipo objekta
            kl.uzdarytiSrautus();
            
            
            stdIn.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ivyko klaida ("+e.getMessage() + ")","Klaida",JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
    public void uzdarytiSrautus() throws Exception{
        System.out.println("Uzdarom srautus");
        out.close();
        in.close();
        soketas.close();
    }
    
    public void siustiZinuteIServeri(String txt){
        if (out != null) {
            out.println(txt);
        }
    }
    
        public void siustiKoordinatesIServeri(String txt){
        if (out != null) {
            out.println(txt);
        }
    }
}
