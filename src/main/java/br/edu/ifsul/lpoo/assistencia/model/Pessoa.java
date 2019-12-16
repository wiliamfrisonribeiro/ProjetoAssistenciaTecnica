
package br.edu.ifsul.lpoo.assistencia.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Telmo Junior 
 */

@Table(name = "tb_pessoa")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)//single table mais eficiente, mas gera mais campos opcionais
@DiscriminatorColumn(name = "tipoPessoa")
public class Pessoa implements Serializable{
    
    @Id
    @Column(name = "cpf", length = 11, nullable = false)
    private String cpf;
    
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;
    
    @Column(name = "cep", length = 8, nullable = false)
    private String cep;
    
    @Column(name = "numero", length = 10, nullable = false)
    private String numero;
    
    @Column(name = "complemento", length = 50)
    private String complemento;
    
    @Column(name = "data_cadastro", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar dtCadastro;
    
    @ManyToOne
    private Cidade cidade;
    
    @Column(name = "tipoPessoa", nullable = false)
    private String tipoPessoa;
    
    public Pessoa(){
        
    }
    
    public Pessoa(String cpf){
        
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Calendar getDtCadastro() {
        return dtCadastro;
    }
    
    public java.sql.Date getDtCadastroSQL() {
        return new java.sql.Date(dtCadastro.getTimeInMillis());
    }

    public void setDtCadastro(Calendar dtCadastro) {
        this.dtCadastro = dtCadastro;
    }
    
    public void setDtCadastroSQL(java.sql.Date dt) {        
        this.dtCadastro = Calendar.getInstance();
        this.dtCadastro.setTimeInMillis(dt.getTime());
    }
        

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }
    
}
