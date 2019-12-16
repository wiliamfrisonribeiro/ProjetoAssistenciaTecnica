
package br.edu.ifsul.lpoo.assistencia.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Telmo Junior
 */
@Table(name = "tb_estado")
@Entity
@NamedQueries({ @NamedQuery(name = "Estado.list", 
                    query="select e from Estado e order by e.nome asc"),
                @NamedQuery(name = "Estado.list.bynome", 
                    query="select e from Estado e where lower(e.nome) = lower(:param)")
})
public class Estado implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_estado", sequenceName = "seq_estado_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_estado", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    
    @Column(name = "uf", length = 2, nullable = false)
    private String uf;
    
    public Estado(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
    
}
