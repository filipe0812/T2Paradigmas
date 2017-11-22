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
    private final LinkedList<Coluna> colunas;
    private final String nome;
    private final String chavePrimaria;
    private boolean createDAO;
    private boolean hasSpaces;
    
    public Tabela(String n, String key){
        nome = n;
        chavePrimaria = key;
        createDAO = true;
        
        hasSpaces = n.contains(" ") || key.contains(" ");
        
        colunas = new LinkedList();
    }
    
    public void setCreateDaoFalse(){
        createDAO = false;
    }
    
    public boolean getCreateDAO(){
        return createDAO;
    }
    
    public boolean getHasSpaces(){
        return hasSpaces;
    }
    
    public void addColuna(String nome, String tipo){
        colunas.add(new Coluna(nome, tipo));
        if(nome.contains(" ")){
            hasSpaces = true;
        }
    }
    
    public String getChavePrimaria(){
        return chavePrimaria;
    }
    
    public LinkedList<Coluna> getColunas() {    //adicionei 08/11
        return colunas;
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
