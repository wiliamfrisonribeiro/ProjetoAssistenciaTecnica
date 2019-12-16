
package br.edu.ifsul.lpoo.assistencia.model.dao;

import br.edu.ifsul.lpoo.assistencia.model.Acao;
import br.edu.ifsul.lpoo.assistencia.model.Cidade;
import br.edu.ifsul.lpoo.assistencia.model.Estado;
import br.edu.ifsul.lpoo.assistencia.model.Permissao;
import br.edu.ifsul.lpoo.assistencia.model.PessoaJuridica;
import br.edu.ifsul.lpoo.assistencia.model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Telmo
 */
public class PersistenciaJDBC implements InterfacePersistencia{   
    
    private String url = "jdbc:postgresql://localhost:5432/db_assistencia";
    private String user = "postgres";
    private String senha = "123456";    
    private String driver = "org.postgresql.Driver";
    private Connection conexao = null;
    private String mensagemErro = null;
    
    public PersistenciaJDBC(){        
        try{                
            Class.forName(driver);  
            System.out.println("Tentando estabelecer conexao com : "+url);
            this.conexao = (Connection) DriverManager.getConnection(url, user, senha);                              
        }catch(ClassNotFoundException | SQLException e){           
            e.printStackTrace();       
            mensagemErro = e.getLocalizedMessage();//recupera a mensagem de erro            
        }                
    }
    public String getMensagemErro() {
        return mensagemErro;
    }
    @Override
    public Boolean conexaoAberta() {
        if(this.conexao != null){
            try{
                return !this.conexao.isClosed();
            }catch(Exception e){
                e.printStackTrace();
            }                
        }        
        return false;        
    }

    @Override
    public void fecharConexao() {
        if(this.conexao != null){
            try{
               this.conexao.close();
            }catch(Exception e){
                e.printStackTrace();
            }                
        }                
    }
    @Override
    public Object find(Class c, Object id) throws Exception {
        
        //Estado, Cidade, PessoaJuridica, Usuario
        //select id, nome, uf from tb_estado where id = 1
        //select id, nome, estado_id from tb_cidade where id = 1
        // PessoaJuridica ??     
        System.out.println("c: "+c);
        if(c == Estado.class){            
           
                PreparedStatement ps = this.conexao.prepareStatement("select id, nome, uf from tb_estado where id = ?");
                ps.setInt(1, new Integer(id.toString()));
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    Estado est = new Estado();
                    est.setId(rs.getInt("id"));
                    est.setNome(rs.getString("nome"));
                    est.setUf(rs.getString("uf"));
                    return est;
                }
                
        }else if( c == Cidade.class){
            
            //recuperar uma cidade com o seu respectivo estado
            try{
                
            PreparedStatement ps = this.conexao.prepareStatement("select c.id as c_id, c.nome as c_nome, e.id as e_id, e.nome as e_nome, e.uf from tb_cidade c, tb_estado e where c.estado_id=e.id and c.id = ?");
                ps.setInt(1, new Integer(id.toString()));
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    Cidade cid = new Cidade();
                        cid.setId(rs.getInt("c_id"));
                        cid.setNome(rs.getString("c_nome"));
                    
                    Estado est = new Estado();
                        est.setId(rs.getInt("e_id"));
                        est.setNome(rs.getString("e_nome"));
                        est.setUf(rs.getString("uf"));
                        
                    cid.setEstado(est);
                        
                    return cid;
                }
                
            }catch(SQLException e){
                e.printStackTrace();
            }
            
        }else if(c == PessoaJuridica.class){
            
        }else if(c == Usuario.class){
            
                Usuario u = null;
                PreparedStatement ps = this.conexao.prepareStatement("select u.cpf as cpf, p.nome, u.senha, p.cep, p.numero, p.tipoPessoa, p.data_cadastro, c.id idCidade, c.nome as nomeCidade "
                                                                   + "from tb_usuario u, tb_pessoa p left join tb_cidade c on (c.id=p.cidade_id) " +
                                                                     "where u.cpf=p.cpf and u.cpf = ?");
                ps.setString(1, id.toString());
                System.out.println("find id :"+id.toString());
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    
                    u = new Usuario();
                    u.setCpf(rs.getString("cpf"));
                    u.setNome(rs.getString("nome"));
                    u.setSenha(rs.getString("senha"));
                    u.setNumero(rs.getString("numero"));
                    u.setCep(rs.getString("cep"));
                    u.setTipoPessoa(rs.getString("tipopessoa"));
                    u.setDtCadastroSQL(rs.getDate("data_cadastro"));
                    u.setCidade(new Cidade(rs.getInt("idCidade"), rs.getString("nomeCidade"), null));
                    
                    rs.close(); // fecha o curso no BD
                    
                    PreparedStatement ps2 = this.conexao.prepareStatement("select p.id, p.descricao, p.acao "
                                                                                            + "from tb_permissoes ps, tb_permissao p "
                                                                                            + "where p.id=ps.permissao_id and ps.usuario_cpf = ?");
                    ps2.setString(1, u.getCpf());
                    
                    ResultSet rs2 = ps2.executeQuery();
                    while(rs2.next()){
                        Permissao p = new Permissao();
                        p.setId(rs2.getInt("id"));
                        p.setDescricao(rs2.getString("descricao"));
                        p.setAcao(Acao.getAcao(rs2.getString("acao")));
                        
                        u.setPermissao(p);
                        
                    }
                    rs2.close();//fecha o curso no BD
                    
                    return u;
                    
                }
            
        }

        return null;
    }

    @Override
    public void persist(Object o) throws Exception {              
        if(o instanceof Estado){            
          Estado e = (Estado)o;
          if(e.getId() == null){                                 
            //insert
            PreparedStatement ps = this.conexao.prepareStatement("insert into tb_estado (id, nome, uf)  values (nextval('seq_estado_id'), ?, ?) returning id");

            ps.setString(1, e.getNome());
            ps.setString(2, e.getUf());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                e.setId(rs.getInt("id"));
           }   
        
            //estrategia para recuperar o id gerado
            //https://www.postgresql.org/docs/8.1/functions-sequence.html
            //select nextval('seq_estado_id')
            //RETURNING  id

          }else{
              //update
              PreparedStatement ps = this.conexao.prepareStatement("update tb_estado set nome = ?, uf = ? where id = ?");
              ps.setString(1, e.getNome());
              ps.setString(2, e.getUf());
              ps.setInt(3, e.getId());
              ps.execute();
          }   
          
        }else if(o instanceof Cidade){
            
            Cidade c = (Cidade) o;
            if(c.getId() == null){
                
               PreparedStatement ps = this.conexao.prepareStatement("insert into tb_cidade (id, nome, estado_id) values (nextval('seq_cidade_id'), ?,? ) returning id");
               ps.setString(1, c.getNome());
               ps.setInt(2, c.getEstado().getId());
               ResultSet rs = ps.executeQuery();
               if(rs.next()){
                   c.setId(rs.getInt(1));
               }
               
            }else{
                
                PreparedStatement ps = this.conexao.prepareStatement("update tb_cidade set estado_id = ?, nome = ? where id = ?");

                 ps.setInt(1, c.getEstado().getId());
                 ps.setString(2, c.getNome());
                 ps.setInt(3, c.getId());
                 ps.execute();
                
            }
                        
        }else if(o instanceof Usuario){
             
            Usuario u = (Usuario) o;
            
            if(u.getTipoPessoa() == null){
                
                PreparedStatement ps = this.conexao.prepareStatement("insert into tb_pessoa (cpf, nome, cep, numero, tipopessoa, data_cadastro, cidade_id ) "
                                                                   + "values (?,?,?,?,?,?,?)");
                ps.setString(1, u.getCpf());
                ps.setString(2, u.getNome());
                ps.setString(3, u.getCep());
                ps.setString(4, u.getNumero());
                ps.setString(5, "U");
                ps.setDate(6, u.getDtCadastroSQL());
                ps.setInt(7, u.getCidade().getId());
                ps.execute();
               

                ps = this.conexao.prepareStatement("insert into tb_usuario (cpf, senha) values (?,?)");
                ps.setString(1, u.getCpf());
                ps.setString(2, u.getSenha());
                ps.execute();
                
                if(u.getPermissoes() != null && u.getPermissoes().size() > 0){
                    for(Permissao p : u.getPermissoes()){
                    
                        ps = this.conexao.prepareStatement("insert into tb_permissoes (usuario_cpf, permissoes_id) values (?,?)");
                        ps.setString(1, u.getCpf());
                        ps.setInt(2, p.getId());
                        ps.execute();
                    }
                    
                }
                
            }else{
                
                PreparedStatement ps = this.conexao.prepareStatement("update tb_pessoa set nome = ?, cep = ?, numero = ?, cidade_id = ? "
                                                                   + "where cpf = ? ");
                
                ps.setString(1, u.getNome());
                ps.setString(2, u.getCep());
                ps.setString(3, u.getNumero());            
                ps.setInt(4, u.getCidade().getId());
                ps.setString(5, u.getCpf());
                ps.execute();
                
                ps = this.conexao.prepareStatement("update tb_usuario set senha = ? where cpf = ? ");
                ps.setString(1, u.getSenha());               
                ps.setString(3, u.getCpf());
                ps.execute();
                                
            }        
       }
    }

    @Override
    public List<Estado> listEstado() throws Exception {
        
        List<Estado> listEstado = null;
        try{
                PreparedStatement ps = this.conexao.prepareStatement("select id, nome, uf from tb_estado order by id asc");
                ResultSet rs = ps.executeQuery();
                listEstado = new ArrayList();//inicializamos a list
                while(rs.next()){
                    Estado est = new Estado();
                    est.setId(rs.getInt("id"));
                    est.setNome(rs.getString("nome"));
                    est.setUf(rs.getString("uf"));
                    listEstado.add(est);//adiciona na lista                    
                }                
            }catch(SQLException e){
                e.printStackTrace();
            }        
        return listEstado;        
    }

    @Override
    public List<Estado> listEstado(String nome) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remover(Object o) throws Exception {
        
        if(o instanceof Estado){
            Estado e = (Estado) o;
            
             PreparedStatement ps = this.conexao.prepareStatement("delete from tb_estado where id = ? ");
             ps.setInt(1, e.getId());//o 1 refere-se ao primeiro parâmetro: ?
             ps.execute();
            
        }else if(o instanceof Cidade){
            
        }else if(o instanceof Usuario){
            
             Usuario u = (Usuario) o;
            
             PreparedStatement ps = this.conexao.prepareStatement("delete from tb_usuario where cpf = ? ");
             ps.setString(1, u.getCpf());//o 1 refere-se ao primeiro parâmetro: ?
             ps.execute();
        }
                
    }

    public String getUrl() {
        return url;
    }

    @Override
    public List<Cidade> listCidade(String nome) throws Exception {        
        List<Cidade> listCidade = null;
        PreparedStatement ps = this.conexao.prepareStatement("select c.id as idCidade, c.nome as nomeCidade, e.id as idEstado, e.nome as nomeEstado, e.uf from tb_cidade c, tb_estado e where c.estado_id=e.id and c.nome like ?");
        
        ps.setString(1, "%"+nome+"%");
        ResultSet rs = ps.executeQuery();
        listCidade = new ArrayList();
        while(rs.next()){
            Cidade c = new Cidade();
            c.setId(rs.getInt("idCidade"));
            c.setNome(rs.getString("nomeCidade"));
            Estado estado = new Estado();
                estado.setId(rs.getInt("idEstado"));
                estado.setNome(rs.getString("nomeEstado"));
                estado.setUf(rs.getString("uf"));            
            c.setEstado(estado);
            listCidade.add(c);
        }
        return listCidade;
    }
    
    @Override
    public List<Cidade> listCidade() throws Exception {
      
        List<Cidade> listCidade = null;
        PreparedStatement ps = this.conexao.prepareStatement("select c.id as idCidade, c.nome as nomeCidade, e.id as idEstado, e.nome as nomeEstado, e.uf from tb_cidade c, tb_estado e where c.estado_id=e.id order by c.nome asc");
        
      
        ResultSet rs = ps.executeQuery();
        listCidade = new ArrayList();
        while(rs.next()){
            Cidade c = new Cidade();
            c.setId(rs.getInt("idCidade"));
            c.setNome(rs.getString("nomeCidade"));
            Estado estado = new Estado();
                estado.setId(rs.getInt("idEstado"));
                estado.setNome(rs.getString("nomeEstado"));
                estado.setUf(rs.getString("uf"));            
            c.setEstado(estado);
            listCidade.add(c);
        }
        return listCidade;
        
    }
    

    @Override
    public Usuario doLogin(String cpf, String senha) throws Exception {
            Usuario usu = null;
            PreparedStatement ps = 
                    this.conexao.prepareStatement("select cpf, senha from tb_usuario where cpf = ? and senha = ?");//complementar com os dados de
            //tb_pessoa
            ps.setString(1, cpf);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                usu = new Usuario();
                usu.setCpf(rs.getString("cpf"));
                usu.setSenha(rs.getString("senha"));
            }             
        return usu;
    }

    @Override
    public List<Usuario> listUsuario() throws Exception {      
         List<Usuario> listUsuario = null;
        PreparedStatement st = this.conexao.prepareStatement("select  p.cpf, p.cep, p.nome, c.id as idCidade, c.nome as nomeCidade from tb_usuario u, tb_pessoa p, tb_cidade c where u.cpf=p.cpf and p.cidade_id=c.id and p.tipopessoa = ?");        
        st.setString(1, "U");
        ResultSet rs = st.executeQuery();
        listUsuario = new ArrayList();
        while(rs.next()){
            Usuario u = new Usuario();
            u.setCpf(rs.getString("cpf"));
            u.setNome(rs.getString("nome"));
            u.setCep(rs.getString("cep"));
            u.setCidade(new Cidade(rs.getInt("idCidade"), rs.getString("nomeCidade"), null));
            listUsuario.add(u);
        }
        return listUsuario;
        
    }


    
}
