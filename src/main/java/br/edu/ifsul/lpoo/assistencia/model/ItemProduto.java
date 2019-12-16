
package br.edu.ifsul.lpoo.assistencia.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Telmo Junior
 */

@Entity
@Table(name = "tb_itemproduto")
public class ItemProduto implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_itemproduto", sequenceName = "seq_itemproduto_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_itemproduto", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(name = "quantidade", precision = 2, nullable = false)
    private Float quantidade;
    
    @Column(name = "valortotal", precision = 2, nullable = false)
    private Float valorTotal;
    
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    
    @ManyToOne
    @JoinColumn(name = "ordemservico_id")
    private OrdemServico ordemServico;
    
    public ItemProduto(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Float quantidade) {
        this.quantidade = quantidade;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    
    
}
