
package br.edu.ifsul.lpoo.assistencia.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class JPanelHome extends JPanel {
    
    private JLabel lblTexto;
    
    public JPanelHome(){
        initComponents();
    }
    
    private void initComponents(){
        lblTexto = new JLabel("Tela de boas Vindas");
        this.add(lblTexto);
    }
    
}
