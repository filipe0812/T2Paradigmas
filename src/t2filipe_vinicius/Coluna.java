/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t2filipe_vinicius;

/**
 *
 * @author Filipe
 */
public class Coluna {
    private String nome, tipo;
    
    public Coluna(String n, String t){
        nome = n;
        tipo = t;
    }
    
    public String getNome(){
        return nome;
    }
    public String getTipo(){
        return tipo;
    } 
}
