
package br.edu.ifsul.lpoo.assistencia.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @disciplina LPOO
 * @author Telmo Junior
 */

@Entity
@Table(name = "tb_usuario")
@DiscriminatorValue("U")
public class Usuario extends Pessoa implements Serializable {
    
    @Column(name = "senha", length = 4, nullable = false)
    private String senha;
    
    @Column(name = "data_ultima_acesso")//opcional
    @Temporal(TemporalType.DATE)
    private Calendar dtUltimoAcesso;
    
    @ManyToMany//o antigo onetoMany cria uma unique para a coluna permissao_id
    @JoinTable(name = "tb_permissoes", joinColumns = {@JoinColumn(name = "usuario_cpf")}, 
                                       inverseJoinColumns = {@JoinColumn(name = "permissao_id")})
        
    private List<Permissao> permissoes;//essa nao é bidirecional
    
    public Usuario(){
        
    }
    
    public Usuario(String cpf){
        
        super(cpf);//chama o construtor da classe genérica
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Calendar getDtUltimoAcesso() {
        return dtUltimoAcesso;
    }
    
    public java.sql.Date getDtUltimoAcessoSQL() {
        
        if(dtUltimoAcesso != null){
            
             return new java.sql.Date(dtUltimoAcesso.getTimeInMillis());
        }
        
        return null;
        
    }

    public void setDtUltimoAcesso(Calendar dtUltimoAcesso) {
        this.dtUltimoAcesso = dtUltimoAcesso;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }
    
    public void setPermissao(Permissao permissao) {
        if(this.permissoes == null)
            this.permissoes = new ArrayList();
        this.permissoes.add(permissao);
    }
    
}
