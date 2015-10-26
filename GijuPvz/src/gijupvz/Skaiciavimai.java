package gijupvz;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class Skaiciavimai extends Thread {

    double x_pr, x_gl, x_zn;
    JTextArea rez;
    JButton b;

    @Override
    public void run() {
        double y;
        double y_max = Double.MIN_VALUE;
        double x_y_max = 0;
        try {

            for (double x = x_pr; x <= x_gl; x += x_zn) {
                y = Math.sin(x) * Math.pow(2, x) + x;
                if (y > y_max) {
                    y_max = y;
                    x_y_max = x;
                }
                //System.out.println("x: " + x + " y:" + y);
                rez.append("x: " + x + " y:" + y + "\n");
                Thread.sleep(1000);
            }
            new MaxReiksmesIsvedimas(y_max, x_y_max).setVisible(true);
            //b.setEnabled(true);
        } catch (Exception e) {
            new MaxReiksmesIsvedimas(y_max, x_y_max).setVisible(true);
        }
    }

    public Skaiciavimai(double a, double b, double c, JTextArea d, JButton e) {
        x_pr = a;
        x_gl = b;
        x_zn = c;
        rez = d;
        this.b = e;
        this.start();
    }

}
