
package br.edu.ifsul.lpoo.assistencia.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Telmo Junior
 */
@Entity
@Table(name = "tb_foto")
public class Foto implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_foto", sequenceName = "seq_foto_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_foto", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(name = "descricao", length = 50, nullable = false)
    private String descricao;
    
    @Column(name = "arquivo", nullable = false)
    @Lob
    private byte[] arquivo;
    
    @Column(name = "nomearquivo", length = 50, nullable = false)
    private String nomeArquivo;
    
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;//bidirecional - o mappedBy referencia esse cara aqui
    
    public Foto(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
}
