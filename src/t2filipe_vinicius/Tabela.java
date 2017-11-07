/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t2filipe_vinicius;

import java.util.LinkedList;

/**
 *
 * @author Filipe
 */
public class Tabela {
    private LinkedList<Coluna> colunas;
    private String nome;
    
    public Tabela(String n){
        nome = n;
        colunas = new LinkedList();
    }
    
    public void addColuna(String nome, String tipo){
        colunas.add(new Coluna(nome, tipo));
    }
    
    public int getNumerColunas(){
        return colunas.size();
    }
    
    public Coluna getColunaById(int id){
        return colunas.get(id);
    }
    
    public String getNome(){
        return nome;
    }
}
