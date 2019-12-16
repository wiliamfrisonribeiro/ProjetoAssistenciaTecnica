
package br.edu.ifsul.lpoo.assistencia.testes;

import br.edu.ifsul.lpoo.assistencia.model.Estado;
import br.edu.ifsul.lpoo.assistencia.model.dao.PersistenciaJDBC;
import org.junit.Test;

/**
 *
 * @author wiliam
 */
public class TestePersistenciaJDBC {
    
    //@Test
    public void testePersistenciaEstado() throws Exception{
        
        PersistenciaJDBC persistencia = new PersistenciaJDBC();
        if(persistencia.conexaoAberta()){
            
            Estado e = (Estado) persistencia.find(Estado.class, 1);
            if(e != null){
                
                System.out.println("achou no bd o estado: "+e.getNome());
            }else{
                System.out.println("nao achou o estado 1");
       
            }
            
            persistencia.fecharConexao();
        }else{
            System.out.println("Nao abriu conexao jdbc");
        }
    }
    

    
}
