
package br.edu.ifsul.lpoo.assistencia.model.dao;

import br.edu.ifsul.lpoo.assistencia.model.Cidade;
import br.edu.ifsul.lpoo.assistencia.model.Estado;
import br.edu.ifsul.lpoo.assistencia.model.Usuario;
import java.util.List;

/**
 *
 * @author Telmo
 */
public interface InterfacePersistencia {
    
    public Boolean conexaoAberta();
    public void fecharConexao();
    public Object find(Class c, Object id) throws Exception;
    public void persist(Object o) throws Exception;
    public List<Estado> listEstado() throws Exception;
    public List<Estado> listEstado(String nome) throws Exception;
    public List<Cidade> listCidade(String nome) throws Exception;
    public List<Cidade> listCidade() throws Exception;
    public List<Usuario> listUsuario() throws Exception;
    
    public void remover(Object o) throws Exception;
    //método que deverá ser implementado para a próxima aula:
    public Usuario doLogin(String cpf, String senha) throws Exception;
    
}
