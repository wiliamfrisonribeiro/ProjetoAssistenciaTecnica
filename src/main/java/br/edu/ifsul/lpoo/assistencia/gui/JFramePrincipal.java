
package br.edu.ifsul.lpoo.assistencia.gui;

    //extender de JFramePrinci
import java.awt.CardLayout;
import javax.swing.JPanel;



public class JFramePrincipal extends javax.swing.JFrame{  
    
    private CardLayout cardLayout;
    private JPanel pnlCard;
    
    //construtor padrão
    public JFramePrincipal() {
        initComponents();//chama o método
    }    
    //aqui faremos a personalizacao do nosso JFramePrincipal
    private void initComponents(){        
        cardLayout = new CardLayout();//inicializa
        pnlCard = new JPanel();
        pnlCard.setLayout(cardLayout);
        this.getContentPane().add(pnlCard);//adiciona o painel no jframe
        
        this.setTitle("Sisteminha para a Assistencia Técnica do meu Pai");
        this.setExtendedState(JFramePrincipal.MAXIMIZED_BOTH);//setar maximizado
        this.setDefaultCloseOperation(JFramePrincipal.EXIT_ON_CLOSE);//fechar o proceso
        
    }
    public void addCard(JPanel painel, String nome){
        pnlCard.add(painel, nome);
    }
    public void viewCard(String nome){
        cardLayout.show(pnlCard, nome);
    }
    
}
