
package br.edu.ifsul.lpoo.assistencia.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Telmo Junior
 */
@Entity
@Table(name = "tb_pessoajuridica")
@DiscriminatorValue("PJ")
public class PessoaJuridica extends Pessoa implements Serializable{
    
    @Column(name = "cnpj", length = 14, nullable = false)
    private String cnpj;
    
    public PessoaJuridica(){
        
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    
}
