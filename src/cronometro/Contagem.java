/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cronometro;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodrigo
 */
public class Contagem implements Runnable{
    Cronometro cron;
    
    public Contagem(Cronometro c)
    {
        this.cron = c;
    }
    
    @Override
    public void run(){
        while(true)
        {
            try
            {
                cron.incmseg();
                cron.incseg();
                cron.incmin();
            }catch(InterruptedException ex){}
        }
    }  
}
