
package br.edu.ifsul.lpoo.assistencia.controler;

import br.edu.ifsul.lpoo.assistencia.gui.JFramePrincipal;
import br.edu.ifsul.lpoo.assistencia.gui.JMenuBarPrincipal;
import br.edu.ifsul.lpoo.assistencia.gui.JPanelHome;
import br.edu.ifsul.lpoo.assistencia.gui.cidade.JPanelListagem;
import br.edu.ifsul.lpoo.assistencia.gui.login.JPanelLogin;
import br.edu.ifsul.lpoo.assistencia.gui.usuario.JPanelUsuario;
import br.edu.ifsul.lpoo.assistencia.model.Usuario;
import br.edu.ifsul.lpoo.assistencia.model.dao.PersistenciaJDBC;
import javax.swing.JOptionPane;


public class Controle {

    private JFramePrincipal framePrincipal;
    private PersistenciaJDBC conexao;
    
    private JPanelListagem pnlListagemCidade;
    private JPanelLogin pnlLogin;
    
    private JPanelUsuario pnlUsuario;
    
    private JMenuBarPrincipal menuPrincipal;
    
    private JPanelHome pnlHome;
    
    public Controle(){
        conexao = new PersistenciaJDBC();
        if(conexao.conexaoAberta()){
            JOptionPane.showMessageDialog(null, "Conectou em : "+conexao.getUrl(), "Conexão", JOptionPane.INFORMATION_MESSAGE);
            initComponents();
        }else{
            JOptionPane.showMessageDialog(null, "Nao conectou em "+conexao.getUrl()+" Motivo: "+conexao.getMensagemErro(), "Conexão", JOptionPane.ERROR_MESSAGE);
        }        
    }
    
    private void initComponents(){
        
        this.framePrincipal = new JFramePrincipal();//inicializa
        
        //passa como parâmetro a instancia de conexao, para permitir
        //o acesso ao métodos dentro de pnlListagemCidade
        pnlListagemCidade = new JPanelListagem(getConexao());//inicializa
        
        pnlLogin = new JPanelLogin(this);
        
        menuPrincipal = new JMenuBarPrincipal(this);
        
        pnlUsuario = new JPanelUsuario(this);
        
        pnlHome = new JPanelHome();
        
        //adiciona o painel no frame (nesse caso não precisa do layout manager)
       // this.framePrincipal.getContentPane().add(pnlListagemCidade);        
        //this.framePrincipal.getContentPane().add(pnlLogin);
        
        this.framePrincipal.addCard(pnlLogin, "tela_login");
        this.framePrincipal.addCard(pnlListagemCidade, "tela_listagem_cidade");
        
        this.framePrincipal.addCard(pnlUsuario, "tela_usuario");
        
        this.framePrincipal.addCard( pnlHome, "tela_home");
        
        this.framePrincipal.viewCard("tela_login");
        
        //o frame deverá ser apresenta somente após a inclusão dos paineis.
        this.framePrincipal.setVisible(true);
                
    }
    public void alteraPainel(String nome){
        
         this.framePrincipal.viewCard(nome);
    }
    
    public void autenticar(String cpf, String senha){       
        //chamar um método da classe PersistenciaJDBC para
        //validar o cpf e senha informados pela interface gráfica
        try{
            Usuario u = getConexao().doLogin(cpf, senha);
            if(u != null){
                 this.framePrincipal.viewCard("tela_home");
                 this.framePrincipal.setJMenuBar(menuPrincipal);
            }else{
                JOptionPane.showMessageDialog(pnlLogin, "cpf ou Senha inválidos!", "Inválido", JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(pnlLogin, "Erro ao autenticar "+e.getLocalizedMessage(), "Autenticação", JOptionPane.ERROR_MESSAGE);
        }
   
    }
    
    public void finaliza(){
        conexao.fecharConexao();
        System.exit(0);
    }
    

    public PersistenciaJDBC getConexao() {
        return conexao;
    }
}
