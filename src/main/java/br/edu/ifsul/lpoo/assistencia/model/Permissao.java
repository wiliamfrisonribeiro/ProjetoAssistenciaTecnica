
package br.edu.ifsul.lpoo.assistencia.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Telmo
 */
@Table(name = "tb_permissao")
@Entity
public class Permissao implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_permissao", sequenceName = "seq_permissao_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_permissao", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(name = "descricao", length = 50, nullable = false)
    private String descricao;
    
    @Column(nullable = false)    
    @Enumerated(EnumType.STRING)
    private Componente componente;

    @Column(nullable = false)    
    @Enumerated(EnumType.STRING)    
    private Acao acao;
    
    public Permissao(){
        
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

    public Componente getComponente() {
        return componente;
    }

    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }
    
    
}
