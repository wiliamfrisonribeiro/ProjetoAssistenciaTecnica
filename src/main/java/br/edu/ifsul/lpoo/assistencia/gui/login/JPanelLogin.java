package br.edu.ifsul.lpoo.assistencia.gui.login;

import br.edu.ifsul.lpoo.assistencia.controler.Controle;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author Telmo
 */
public class JPanelLogin extends JPanel implements ActionListener {
    private JLabel lblCPF;
    private JLabel lblSenha;
    private JTextField txfCPF;
    private JPasswordField psfSenha;
    private JButton btnLogar;
    private GridBagConstraints gbc;
    private GridBagLayout gridBagLayout;
    
    private Controle controle;
    
    public JPanelLogin(Controle controle){
        this.controle = controle;
        initComponents();
    }
    private void initComponents(){     
        gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        //inicializar os componentes e registro o actionlistner para o botão
        lblCPF = new JLabel("CPF:");
        txfCPF = new JTextField(20);//20 é o numero de colunas (a largura)
        //txfCPF.setText("00000000000");
        lblSenha = new JLabel("Senha:");
        psfSenha = new JPasswordField(20);
        //psfSenha.setText("1234");
        btnLogar = new JButton("Logar");
        btnLogar.addActionListener(this);
        btnLogar.setActionCommand("comando_logar");
        
        gbc = new GridBagConstraints();
        gbc.gridy = 0; //seta a linha
        gbc.gridx = 0; //seta coluna
        this.add(lblCPF, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridy = 0; //seta a linha
        gbc.gridx = 1; //seta coluna
        this.add(txfCPF, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridy = 1; //seta a linha
        gbc.gridx = 0; //seta coluna
        this.add(lblSenha, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridy = 1; //seta a linha
        gbc.gridx = 1; //seta coluna
        this.add(psfSenha, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridy = 2; //seta a linha
        gbc.gridx = 1; //seta coluna
        this.add(btnLogar, gbc);
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {     
        if(e.getActionCommand().equals(btnLogar.getActionCommand())){
            String cpf  = txfCPF.getText().trim();//remove os espaços em branco
            String senha = new String(psfSenha.getPassword()).trim();
            if(cpf.length() > 0 && senha.length() > 0){
                controle.autenticar(cpf, senha);
            }else{
                JOptionPane.showMessageDialog(this, "Informe CPF e/ou Senha", "Informe", JOptionPane.INFORMATION_MESSAGE);
            }
            /*
              1) Validar o formulário
              2) No método autenticar, implementar a autenticação via BD
            */            
        }
    }
    
}
