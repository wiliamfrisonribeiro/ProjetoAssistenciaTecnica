package br.edu.ifsul.lpoo.assistencia.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Telmo Junior
 */
@Entity
@Table(name = "tb_contareceber")
public class ContaReceber implements Serializable {
    
    @EmbeddedId
    private ContaReceberID id;
    
    @Column(name = "datavencimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar dataVencimento;
    
    @Column(name = "valor", precision = 2, nullable = false)
    private Float valor;
    
    @Column(name = "valorpago", precision = 2)
    private Float valorpago;
    
    @Column(name = "datapagamento")
    @Temporal(TemporalType.DATE)
    private Calendar datapagamento;
    

    
    public ContaReceber(){
        
    }

    public ContaReceberID getId() {
        return id;
    }

    public void setId(ContaReceberID id) {
        this.id = id;
    }

    public Calendar getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Calendar dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Float getValorpago() {
        return valorpago;
    }

    public void setValorpago(Float valorpago) {
        this.valorpago = valorpago;
    }

    public Calendar getDatapagamento() {
        return datapagamento;
    }

    public void setDatapagamento(Calendar datapagamento) {
        this.datapagamento = datapagamento;
    }
    
}
