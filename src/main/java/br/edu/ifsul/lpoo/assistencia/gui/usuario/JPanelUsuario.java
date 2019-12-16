
package br.edu.ifsul.lpoo.assistencia.gui.usuario;

import br.edu.ifsul.lpoo.assistencia.controler.Controle;
import java.awt.CardLayout;
import javax.swing.JPanel;


public class JPanelUsuario extends javax.swing.JPanel {

    //criar duas cartas e adiciona-las no baralho
    private JPanelEdicao edicao;
    private JPanelListagem listagem;
    private Controle controle;
    
    public JPanelUsuario(Controle c) {
        this.controle = c;
        initComponents();
        initBaralho();
    }
    private void initBaralho(){
        edicao = new JPanelEdicao(this);
        listagem = new JPanelListagem(this);
        this.addCard(getEdicao(), "edicao");
        this.addCard(getListagem(), "listagem");
        this.viewCard("listagem");
    }
    
    //criar os metodos addCard e viewCard    
    public void addCard(JPanel painel, String nome){
        this.add(painel, nome);
    }
    public void viewCard(String nome){
       //casting de  LayoutManager para CardLayout
      ((CardLayout) this.getLayout()).show(this, nome);
    }
    
    public Controle getControle(){
        return controle;
    }
    

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.CardLayout());
    }// </editor-fold>//GEN-END:initComponents

    public JPanelEdicao getEdicao() {
        return edicao;
    }

    public JPanelListagem getListagem() {
        return listagem;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
