
package br.edu.ifsul.lpoo.assistencia.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Telmo Junior
 */
@Embeddable
public class ContaReceberID implements Serializable {
    
    @Column(name = "numeroparcela")
    private Integer numeroParcela;
    
    @ManyToOne
    @JoinColumn(name = "ordemservico_id")
    private OrdemServico ordemServico;
    
    public ContaReceberID(){
        
    }

    public Integer getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(Integer numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }
    
}
