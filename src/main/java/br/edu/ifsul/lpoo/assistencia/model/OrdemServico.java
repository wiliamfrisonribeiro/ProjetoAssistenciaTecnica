
package br.edu.ifsul.lpoo.assistencia.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author Telmo Junior
 */
@Entity
@Table(name = "tb_ordemservico")
public class OrdemServico implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_ordemservico", sequenceName = "seq_ordemservico_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_ordemservico", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "dataabertura", nullable = false)
    private Calendar dataAbertura;
    
    @Column(name = "descricao", length = 100, nullable = false)
    private String descricao;
    
    @Column(name = "resolucao", length = 200)
    private String resolucao;
    
    @Column(name = "valorprodutos", precision = 2)
    private Float valorProdutos;
    
    @Column(name = "valortotal", precision = 2)
    private Float valorTotal;
    
    @Column(name = "quatidadeparcelas")
    private Integer quantidadeParcelas;
    
    @Column(nullable = false)    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @OneToMany(mappedBy = "ordemServico")
    private Set<ItemProduto> intesProdutos;    
    
    @OneToMany(mappedBy = "id.ordemServico")
    private Set<ContaReceber> parcelas;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "pessoajuridica_id", nullable = false)
    private PessoaJuridica cliente;
    
    @Column(nullable = false)    
    @Enumerated(EnumType.STRING)
    private FormaPagamento formapagamento;
    
    public OrdemServico(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Calendar dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getResolucao() {
        return resolucao;
    }

    public void setResolucao(String resolucao) {
        this.resolucao = resolucao;
    }

    public Float getValorProdutos() {
        return valorProdutos;
    }

    public void setValorProdutos(Float valorProdutos) {
        this.valorProdutos = valorProdutos;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(Integer quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<ContaReceber> getParcelas() {
        return parcelas;
    }

    public void setParcelas(Set<ContaReceber> parcelas) {
        this.parcelas = parcelas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PessoaJuridica getCliente() {
        return cliente;
    }

    public void setCliente(PessoaJuridica cliente) {
        this.cliente = cliente;
    }

    public FormaPagamento getFormapagamento() {
        return formapagamento;
    }

    public void setFormapagamento(FormaPagamento formapagamento) {
        this.formapagamento = formapagamento;
    }

    public Set<ItemProduto> getIntesProdutos() {
        return intesProdutos;
    }

    public void setIntesProdutos(Set<ItemProduto> intesProdutos) {
        this.intesProdutos = intesProdutos;
    }
    
}
