/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cronometro;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author rodrigo
 */
public class Cronometro extends JFrame{
    private int mins;
    private int segs;
    private int msegs;
    private Lock semaforo;
    private Condition cond;

    private JTextField min = new JTextField(2);
    private JTextField seg = new JTextField(2);
    private JTextField mseg = new JTextField(3);
    private JButton fechar = new JButton("Fechar");
    private JButton reset = new JButton("Reset");
    private JButton ctrl = new JButton("Iniciar");
    private JPanel butpainel = new JPanel();
    private JPanel txtpainel = new JPanel();
    
    public Cronometro()
    {
        super("Cronômetro");
        this.setSize(new Dimension(300,75));
        this.setLayout(new BorderLayout());
        txtpainel.setLayout(new GridLayout(1,3));
        butpainel.setLayout(new GridLayout(1,3));
        semaforo = new ReentrantLock();
        cond = semaforo.newCondition();
        
        min.setEditable(false);
        seg.setEditable(false);
        mseg.setEditable(false);

        min.setText("00");
        seg.setText("00");
        mseg.setText("000");
        
        min.setHorizontalAlignment(JTextField.LEFT);
        seg.setHorizontalAlignment(JTextField.LEFT);
        mseg.setHorizontalAlignment(JTextField.LEFT);
        
        txtpainel.add(min);
        txtpainel.add(seg);
        txtpainel.add(mseg);

        butpainel.add(fechar);
        butpainel.add(reset);
        butpainel.add(ctrl);
        
        this.add(txtpainel, BorderLayout.CENTER);
        this.add(butpainel, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        fechar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        reset.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               min.setText("00");
               seg.setText("00");
               mseg.setText("000");
            }
        });
        
        ctrl.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
    }
    
    public void incmin() throws InterruptedException
    {
        DecimalFormat fmt = new DecimalFormat("00");
        semaforo.lock();
        try
        {
            while(segs < 60)
                cond.await();
            this.min.setText(fmt.format(this.mins));
            this.mins++;
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
            this.seg.setText(fmt.format(this.segs));
            this.segs++;
        }finally{semaforo.unlock();}
    }
    
    public void incmseg()
    {
        DecimalFormat fmt = new DecimalFormat("000");
        semaforo.lock();
        try
        {
            this.min.setText(fmt.format(this.mins));
            this.mins++;
        }finally{semaforo.unlock();}
    }    
}
