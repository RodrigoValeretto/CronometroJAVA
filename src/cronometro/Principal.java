/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cronometro;

import java.text.DecimalFormat;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author rodrigo
 */
public class Principal {  
    private GUI gui;    
    public Principal()
    {
        gui = new GUI();
    }
    
   /* public void incmin() throws InterruptedException
    {
        DecimalFormat fmt = new DecimalFormat("00");
        semaforo.lock();
        try
        {
            while(segs < 60)
                cond.await();
            this.gui.getMin().setText(fmt.format(this.mins));
            this.mins++;
            this.segs = 0;
        }finally{semaforo.unlock();}
    }

    public void incseg() throws InterruptedException
    {
        DecimalFormat fmt = new DecimalFormat("00");
        semaforo.lock();
        try
        {
            while(msegs < 1000)
                cond.await();
            this.gui.getSeg().setText(fmt.format(this.segs));
            this.segs++;
            this.msegs = 0;
        }finally{semaforo.unlock();}
    }
    
    public void incmseg()
    {
        DecimalFormat fmt = new DecimalFormat("000");
        semaforo.lock();
        try
        {
            this.gui.getMseg().setText(fmt.format(this.msegs));
            this.msegs++;
        }finally{semaforo.unlock();}
    }    
    */
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Principal cron = new Principal();
    }
}
