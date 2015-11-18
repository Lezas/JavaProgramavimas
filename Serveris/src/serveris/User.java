package serveris;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;

public class User extends Thread {

    //issisaugom soctek tipo objekta, nes per ji gausim duoenu srautus

    Socket clientSocket;
    //vartotojui priskiriam jo slapyvardi
    String nick;
    //srauta turim kaip klases kintamaji, kad galetumem is naujo nekurti, o ta pati naudoti
    PrintWriter out;
    //klientas zinos ir apie visus tuo metu prisijungusius klientus
    ArrayList<User> klientai;

    //per konstruktoriu paduodam visus butinus objektus

    public User(Socket s, ArrayList<User> k, String n) {
        clientSocket = s;
        klientai = k;
        nick = n;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (Exception e) {
        }
    }

    //si gija nuolat skaitys duomenis is kleinto ir ziures kaip i tai reaguoti, t.y. ka jam atgal pasiusti ir pan.

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(nick + " parase: " + inputLine);
                if (inputLine.equals("Bye.")) {
                    out.println("Bye.");
                    klientai.remove(this);
                    for (User u:klientai) {
                        u.out.println("out:"+this.nick);
                    }
                    break;
                } else if (inputLine.startsWith("v:")) {
                    //jei prasideda v: tada persiunciam visiems kleintams ta pati teksta
                    atsakytiVisiems(inputLine);
                } else if (inputLine.startsWith("pm:")) {
                    //jei prasideda pm: tada siunciam tik tam klientui, kurio nick toks pat, kaip tekstas po pirmo : iki antro :
                    String[] dalys = inputLine.split(":", 3);
                    siustiZinute(dalys[2], dalys[1]);
                } else if(inputLine.startsWith("tp:")){
                    String[] dalys = inputLine.split(":", 3);
                    siustiKoordinates(dalys[1],dalys[2]);
                } else if(inputLine.startsWith("out:")) {
                    KlientasAtsijungia();
                    PasalintiKlientaIsServerio();
                } else {
                    //atsakom jam tekstu, kuris nurodo koks yra zinutes ilgis
                    out.println("Ilgis: " + inputLine.length());
                }
            }
            //kada baigia klausyma (po Bye.) tada uzdarom visus srautus
            clientSocket.close();
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //metode einam per visa klientu sarasa ir visiems siunciam ta pati teksta
    public void atsakytiVisiems(String txt) throws Exception {
        int i = 1;
        for (User u : klientai) {
            if (!u.equals(this)) {
                u.out.println("[" + (LocalTime.now()) + "] " + nick + ": " + txt.substring(2));
            }
        }
    }
    //metode einam per visu kleintu sarasa ir tam, kurio nick toks koki nurode vartotojas, tam tik siunciam zinute
    public void siustiZinute(String txt, String n) {
        for (User u : klientai) {
            if (u.nick.equals(n)) {
                u.out.println("[" + (LocalTime.now()) + "] " + nick + ": " + txt);
                break;
            }
        }
    }
    
    public void siustiKoordinates(String a, String p){
        for (User u : klientai) {
            if (!u.equals(this)) {
                u.out.println("tp:"+nick+":"+ a +":"+p);
                break;
            }
        }
    }
    
    public void KlientasAtsijungia(){
        for (User u : klientai) {
            if (!u.equals(this)) {
                u.out.println("out:"+nick);
                break;
            }
        }
    }
    
    public void PasalintiKlientaIsServerio(){
        int i =0;
        for (User u : klientai) {
            if (u.equals(this)) {
                
                break;
            }
            i++;
        }
        
        klientai.remove(i);
    }
}
