
package br.edu.ifsul.lpoo.assistencia.testes;

import br.edu.ifsul.lpoo.assistencia.model.Acao;
import br.edu.ifsul.lpoo.assistencia.model.Cidade;
import br.edu.ifsul.lpoo.assistencia.model.Componente;
import br.edu.ifsul.lpoo.assistencia.model.Estado;
import br.edu.ifsul.lpoo.assistencia.model.Permissao;
import br.edu.ifsul.lpoo.assistencia.model.Usuario;
import br.edu.ifsul.lpoo.assistencia.model.dao.PersistenciaJPA;
import java.util.Calendar;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author wiliam
 */
public class TestePersistenciaJPA { 
    
    @Test
    public void testeUusario() throws Exception{    
      
        PersistenciaJPA conexaoJPA = new PersistenciaJPA();
        
        System.out.println("Conexao JPA: "+conexaoJPA.conexaoAberta());
        
        if(conexaoJPA.conexaoAberta()){
            Estado est = (Estado) conexaoJPA.find(Estado.class, 1);
            if(est == null){
                est = new Estado();
                est.setNome("Rio Grande do Sul");
                est.setUf("RS");
                conexaoJPA.persist(est);
                System.out.println("Inseriu o Estado: "+est.getId());
            }else{
                System.out.println("Achou o estado :"+est.getId());
            }
            
            Cidade cid = (Cidade) conexaoJPA.find(Cidade.class, 1);
            if(cid == null){
                cid = new Cidade();
                cid.setNome("Passo Fundo");
                cid.setEstado(est);
                conexaoJPA.persist(cid);
                System.out.println("Inseriu a Cidade: "+cid.getId());                
            }else{
                System.out.println("Achou a Cidade: "+cid.getId());
            }
            
            Permissao p = (Permissao) conexaoJPA.find(Permissao.class, 1);
            if(p == null){
                p = new Permissao();
                p.setAcao(Acao.AUTENTICAR);
                p.setComponente(Componente.TELALOGIN);
                p.setDescricao("autenticar na tela de login");
                conexaoJPA.persist(p);
                System.out.println("Inseriu a Permissao: "+p.getId());
            }else{
                System.out.println("Achou a permissao: "+p.getId());
            }
            
            Permissao p2 = (Permissao) conexaoJPA.find(Permissao.class, 2);
            if(p2 == null){
                p2 = new Permissao();
                p2.setAcao(Acao.LISTAR);
                p2.setComponente(Componente.TELAUSUARIO);
                p2.setDescricao("LISTAR na tela de TELAUSUARIO");
                conexaoJPA.persist(p2);
                System.out.println("Inseriu a Permissao: "+p2.getId());
            }else{
                System.out.println("Achou a permissao: "+p2.getId());
            }
            
             
            Usuario usu = (Usuario) conexaoJPA.find(Usuario.class, "00000000000");
            
            if(usu == null){
                usu = new Usuario();
                usu.setNome("Usuario de Testes");
                usu.setCidade(cid);
                usu.setSenha("1234");
                usu.setCpf("00000000000");
                usu.setCep("99010010");
                usu.setNumero("123");
                usu.setPermissao(p);
                
                usu.setDtCadastro(Calendar.getInstance());
                conexaoJPA.persist(usu);
                System.out.println("Inseriu o usuario "+usu.getCpf());
                
            }else{
                System.out.println("Achou Usuario "+usu.getCpf()+" com "+usu.getPermissoes().size()+" permissoes.");
            }
            
            Usuario usu2 = (Usuario) conexaoJPA.find(Usuario.class, "00000000001");
            
            if(usu2 == null){
                usu2 = new Usuario();
                usu2.setNome("Usuario para Compras");
                usu2.setCidade(cid);
                usu2.setSenha("4567");
                usu2.setCpf("00000000001");
                usu2.setCep("99010010");
                usu2.setNumero("123");
                
                usu2.setDtCadastro(Calendar.getInstance());
                conexaoJPA.persist(usu2);
                System.out.println("Inseriu o usuario "+usu2.getCpf());
                
            }else{
                System.out.println("Achou Usuario "+usu2.getCpf()+" com "+usu2.getPermissoes().size()+" permissoes.");
            }
            
            System.out.println("Fechando conexao com o BD");
            conexaoJPA.fecharConexao();
        
        }
    }
    
    
   // @Test
    public void testeListatemParametroEstado() throws Exception{ 
        
        PersistenciaJPA conexaoJPA = new PersistenciaJPA();
        if(conexaoJPA.conexaoAberta()){
            
            List<Estado> listEstados = conexaoJPA.listEstado("%R%");
            for(Estado e : listEstados){
                  System.out.println("Encontrou o estado: "+e.getId());
            }        
        }
    }
    
    //@Test
    public void testeListatemEstado() throws Exception{    
        //sequencia do teste
        //abrir a conexao e fecha-la no final
        //1 - listar todas as instancias de Estado
        //2 - para cada instancia: mostrar, alterar e remover
        //3 - caso a lista esteja vazia, inserir dois novos registro
        
        PersistenciaJPA conexaoJPA = new PersistenciaJPA();
        if(conexaoJPA.conexaoAberta()){
            
            List<Estado> listEstados = null;//completar
            if(listEstados.isEmpty()){//testar                
                for(Estado e : listEstados){
                  System.out.println("Encontrou o estado: "+e.getId());   
                  //alterar algo
                  conexaoJPA.remover(e);
                  System.out.println("Removeu o estado "+e.getId());
                }                
            }else{
                //implementar
                
                
            }
            conexaoJPA.fecharConexao();
        }

    
    }
    
    //@Test
    public void testePersistenciaEstado() throws Exception{    
       
        PersistenciaJPA conexaoJPA = new PersistenciaJPA();
        if(conexaoJPA.conexaoAberta()){
        
            Estado est =  (Estado) conexaoJPA.find(Estado.class, 1);
            if(est == null){
                est = new Estado();//estado: New ou detached
                est.setNome("Acre");
                est.setUf("AC");
                conexaoJPA.persist(est);
                //qual é o status (JPA) do est agora ??? managed
                System.out.println("Codigo do novo estado : "+est.getId());
                
                //não existe no BD o registro 1
            }else{                
                System.out.println("Nome: "+est.getNome());
                est.setNome("Amazonas");
                est.setUf("AM");
                conexaoJPA.persist(est);
                //existe o registro 1
            }
            conexaoJPA.fecharConexao();
        }
        
        
        
        
    }    
    //@Test
    public void testarConexaoJPA() throws Exception{
        
        PersistenciaJPA conexaoJPA = new PersistenciaJPA();
        if(conexaoJPA.conexaoAberta()){
            Estado est = new Estado();//nesse momento o estado é New
            System.out.println("id: "+est.getId());
            est.setNome("Rio Grande do Sul");
            est.setUf("RS");
            conexaoJPA.persist(est);//nesse momento o estado para para Managed
            System.out.println("id: "+est.getId());
            est = new Estado();//com a nova instancia passa para New novamente
            est.setNome("Santa Catarina");
            est.setUf("SC");
            conexaoJPA.persist(est);//estado: managed
            System.out.println("nome: "+est.getNome());
            
            System.out.println("Abriu a conexao com o BD");
            conexaoJPA.fecharConexao();
        }else{
            System.out.println("Nao abriu a conexao");
        }
        
    }    
        
       
}
