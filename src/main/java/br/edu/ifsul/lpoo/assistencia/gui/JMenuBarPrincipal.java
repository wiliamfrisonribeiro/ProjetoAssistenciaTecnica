
package br.edu.ifsul.lpoo.assistencia.gui;

import br.edu.ifsul.lpoo.assistencia.controler.Controle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;


public class JMenuBarPrincipal extends JMenuBar implements ActionListener {
    private JMenu menuArquivo;
    private JMenu menuListagens;
    private JMenu menuCadastros;
    
    private JMenuItem menuItemCidades;
    private JMenuItem menuItemUsuario;
    private JMenuItem menuItemSair;
    
    private Controle controle;
    
    //construtor que recebe o Controle
    public JMenuBarPrincipal(Controle c){
        this.controle = c;
        initComponents();
    }
    private void initComponents(){
        menuArquivo = new JMenu("Arquivo");
        menuItemSair = new JMenuItem("Sair");
        menuArquivo.add(menuItemSair);
        this.add(menuArquivo);
        
        menuItemSair.addActionListener(this);
        menuItemSair.setActionCommand("comando_sair");
        
        menuCadastros = new JMenu("Cadastros");
        menuItemUsuario = new JMenuItem("Usuário");
        menuCadastros.add(menuItemUsuario);
        this.add(menuCadastros);
        
        menuItemUsuario.addActionListener(this);
        menuItemUsuario.setActionCommand("comando_usuario");
        
        
        menuListagens = new JMenu("Listagens");
        menuItemCidades = new JMenuItem("Cidade");
        
        menuItemCidades.addActionListener(this);
        menuItemCidades.setActionCommand("comando_cidades");
        
        menuListagens.add(menuItemCidades);
        this.add(menuListagens);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
      if(e.getActionCommand().equals(menuItemUsuario.getActionCommand())){
          //mudar o painel (card)
          controle.alteraPainel("tela_usuario");
          
      }else if(e.getActionCommand().equals(menuItemCidades.getActionCommand())){
          
          controle.alteraPainel("tela_listagem_cidade");
          
      }else if(e.getActionCommand().equals(menuItemSair.getActionCommand())){
          
          controle.finaliza();
      }
        
    }
    
}
