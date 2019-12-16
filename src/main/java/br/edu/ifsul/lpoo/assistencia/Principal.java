
package br.edu.ifsul.lpoo.assistencia;

import br.edu.ifsul.lpoo.assistencia.controler.Controle;

public class Principal {
    //construtor
    public Principal(){
        new Controle();
    }
    //método que será executado inicialmente
    public static void main(String args[]){
        new Principal();//cria uma instancia da classe Principal
    }
}
