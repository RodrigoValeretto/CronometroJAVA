/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cronometro;

/**
 *
 * @author rodrigo
 */
public class Principal {     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GUI cron = new GUI();
        Contagem cont = new Contagem(cron);
        Thread t1 = new Thread(cont);
        
        t1.start();
    }
}
