package serveris;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Serveris {

    public static void main(String[] a) {
        ServerSocket serverSocket = null;
        //Turesim sarasa, kuriame saugosim informacija apie visus prisijungusius klientus
        ArrayList<User> klientai = new ArrayList();
        //skaitliukas vien tam, kad kiekvienas vartotojas turetu univalu varda
        int counter = 1;
        try {
            //susikuriam ServerSocket tipo objekta, nurodydami kuriuo prievadu klausysimes infromacijos
            serverSocket = new ServerSocket(1234);
            boolean testi = true;
            //turim amzina cikla
            while (testi) {
                //laukiama naujo prisijungusio kliento
                Socket clientSocket = serverSocket.accept();
                //System.out.println(clientSocket.getPort());//cia galim suzinoti koki prievada jam skyre
                System.out.println("Sulauke naujo kliento " + clientSocket.getInetAddress().getHostAddress());//galim gauti ir koks jo ip adresas
                //kiekvienam esanciame klientu sarase pranesam, kad prisijunge naujas kleintas
                String nikas = "Klientas_" + counter++;
                User klientas = new User(clientSocket, klientai, nikas);
                klientas.out.println("nick:"+nikas);
                for (User u : klientai) {
                    
                    u.out.println("Prisijunge naujas klientas: " + nikas);
                    u.out.println("in:" + nikas+":0:0");
                    
                    klientas.out.println("Prisijunges:" + u.nick);
                    klientas.out.println("in:" + u.nick+":0:0");
                }
                
                klientai.add(klientas);
                
                //paleidziam gija, kad klientas pradetu klausytis komandu is serverio
                klientas.start();
            }
        } catch (Exception e) {
            try {
                //jei buvo klaidu uzdarom serveri
                serverSocket.close();
            } catch (Exception ex) {
            }
            //baigiam rpogramos darba
            System.exit(1);
        }
    }
}
