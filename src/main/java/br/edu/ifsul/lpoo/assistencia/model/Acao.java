
package br.edu.ifsul.lpoo.assistencia.model;

public enum Acao {
    
    AUTENTICAR, INCLUIR, ALTERAR, EXCLUIR, EDITAR, LISTAR;
    
    public static Acao getAcao(String acao){
        
        if(AUTENTICAR.toString().equals(acao)){
            
            return AUTENTICAR;
            
        }else if(INCLUIR.toString().equals(acao)){
            
            return INCLUIR;
        
        }else if(ALTERAR.toString().equals(acao)){
            
            return ALTERAR;
        
        }else if(EXCLUIR.toString().equals(acao)){
            
            return EXCLUIR;
        
        }else if(EDITAR.toString().equals(acao)){
            
            return EDITAR;
        
        }else if(LISTAR.toString().equals(acao)){
            
            return LISTAR;
        
        }
        
        return null;
    }
}
