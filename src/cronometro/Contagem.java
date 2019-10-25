/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cronometro;

import java.text.DecimalFormat;
import javax.swing.JTextField;

/**
 *
 * @author rodrigo
 */
public class Contagem implements Runnable{
    JTextField min;
    JTextField seg;
    JTextField mseg;
    
    public Contagem(JTextField min, JTextField seg, JTextField mseg)
    {
        this.min = min;
        this.seg = seg;
        this.mseg = mseg;
    }
    
    @Override
    public void run(){
        int k = Integer.parseInt(this.min.getText());
        int j = Integer.parseInt(this.seg.getText());
        int i = Integer.parseInt(this.mseg.getText());
        DecimalFormat fmts = new DecimalFormat("00");
        DecimalFormat fmtms = new DecimalFormat("000");
        DecimalFormat fmtm = new DecimalFormat("00");
        while(!Thread.interrupted())
        {
            this.min.setText(fmtm.format(k));
            try{
                for(; j < 60; j++)
                {
                    this.seg.setText(fmts.format(j));
                    for(; i < 1000; i++)
                    {
                        this.mseg.setText(fmtms.format(i));
                        Thread.sleep(0, 10);
                    }
                    i = 0;
                }
            }catch(InterruptedException ex){return;}
            k++;
            j = 0;
        }
        return;
    }  
}
