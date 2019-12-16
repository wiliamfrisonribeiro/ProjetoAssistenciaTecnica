
package br.edu.ifsul.lpoo.assistencia.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Telmo
 */
@Table(name = "tb_cidade" )
@Entity
public class Cidade implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_cidade", sequenceName = "seq_cidade_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_cidade", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    
    @ManyToOne
    private Estado estado;
    
    public Cidade(){
        
        
    }

    public Cidade(Integer id, String nome, Estado estado){
        this.id = id;
        this.nome = nome;
        this.estado = estado;        
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString(){
        return nome;
    }
    
    @Override
    public boolean equals(Object param){
        if(param != null && param instanceof Cidade ){
            Cidade c = (Cidade) param;
            if(c.getId().intValue() == this.id){
                return true;
            }
        }
        
        return false;
    }
    
}
