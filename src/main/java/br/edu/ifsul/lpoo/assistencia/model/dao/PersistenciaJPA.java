
package br.edu.ifsul.lpoo.assistencia.model.dao;

import br.edu.ifsul.lpoo.assistencia.model.Cidade;
import br.edu.ifsul.lpoo.assistencia.model.Estado;
import br.edu.ifsul.lpoo.assistencia.model.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Telmo Junior
 */
public class PersistenciaJPA implements InterfacePersistencia{
    
    private EntityManagerFactory factory;
    private EntityManager entity;
    
    public PersistenciaJPA(){        
        factory = Persistence.createEntityManagerFactory("pu_db_assistencia");
        entity = factory.createEntityManager();        
    }
    @Override
    public Boolean conexaoAberta() {
        if(entity == null)
            return false;        
        return entity.isOpen();
    }
    @Override
    public void fecharConexao() {
        entity.close();
    }

    @Override
    public Object find(Class c, Object id) {
        return entity.find(c, id);
    }

    @Override
    public void persist(Object o) throws Exception {
        
       entity.getTransaction().begin();
       entity.persist(o);
       entity.getTransaction().commit();
       
       //entity.getTransaction().rollback();
    }

    @Override
    public List<Estado> listEstado() throws Exception {
          
        return (List<Estado>) entity.createNamedQuery("Estado.list").getResultList();
    }

    @Override
    public void remover(Object o) throws Exception {

       entity.getTransaction().begin();
       entity.remove(o);
       entity.getTransaction().commit();
        
        
    }

    @Override
    public List<Estado> listEstado(String nome) throws Exception {
        
      return (List<Estado>) 
          entity.createNamedQuery("Estado.list.bynome").setParameter("param", nome).getResultList();
    }

    @Override
    public List<Cidade> listCidade(String nome) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario doLogin(String cpf, String senha) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> listUsuario() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cidade> listCidade() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
