/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cronometro;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author rodrigo
 */
public class GUI extends JFrame{
    private JTextField min = new JTextField(2);
    private JTextField seg = new JTextField(2);
    private JTextField mseg = new JTextField(3);
    private JButton fechar = new JButton("Fechar");
    private JButton reset = new JButton("Reset");
    private JButton ctrl = new JButton("Iniciar");
    private JPanel butpainel = new JPanel();
    private JPanel txtpainel = new JPanel();
    private Thread t = new Thread(new Contagem(min,seg,mseg));
    
    public GUI()
    {
        super("Cronômetro");
        this.setSize(new Dimension(300,100));
        this.setLayout(new BorderLayout());
        GridBagLayout grid = new GridBagLayout();
        txtpainel.setLayout(grid);
        GridBagConstraints c = grid.getConstraints(butpainel);
        
        min.setEditable(false);
        seg.setEditable(false);
        mseg.setEditable(false);

        min.setText("00");
        seg.setText("00");
        mseg.setText("000");
        
        min.setHorizontalAlignment(JTextField.LEFT);
        seg.setHorizontalAlignment(JTextField.LEFT);
        mseg.setHorizontalAlignment(JTextField.LEFT);
        
        c.insets = new Insets(0,0,0,5);
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 15;
        c.ipady = 4;
        txtpainel.add(min , c);
        c.gridx = 1;
        txtpainel.add(seg , c);
        c.gridx = 2;
        c.ipadx = 32;
        txtpainel.add(mseg , c);

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
               t.interrupt();
               ctrl.setText("Iniciar");
               t = new Thread(new Contagem(min, seg, mseg));
            }
        });
        
        ctrl.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ctrl.getText().equals("Iniciar"))
                {
                    t.start();
                    ctrl.setText("Parar");
                }else{
                    t.interrupt();
                    t = new Thread(new Contagem(min,seg,mseg));
                    ctrl.setText("Iniciar");
                }
            }
        });
        
    }

    public JTextField getMin() {
        return min;
    }

    public void setMin(JTextField min) {
        this.min = min;
    }

    public JTextField getSeg() {
        return seg;
    }

    public void setSeg(JTextField seg) {
        this.seg = seg;
    }

    public JTextField getMseg() {
        return mseg;
    }

    public void setMseg(JTextField mseg) {
        this.mseg = mseg;
    }
    
}
