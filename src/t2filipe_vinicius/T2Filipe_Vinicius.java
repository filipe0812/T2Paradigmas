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
public class T2Filipe_Vinicius {

    public static void main(String[] args) {
        LinkedList<Tabela> tabelas = Metadata.getTables();
        
        
        //repete o loop para cada tabela dentro da lista
        for(int x = 0; x < tabelas.size(); x++){
            System.out.println(""); //extra enter
            System.out.println("Nome da tabela: " + tabelas.get(x).getNome());
            
            //repete o loop para cada coluna dentro da tabela
            for(int y = 0; y < tabelas.get(x).getNumerColunas(); y++){
                System.out.print("Coluna: " + tabelas.get(x).getColunaById(y).getNome());
                System.out.println(", tipo: " + tabelas.get(x).getColunaById(y).getTipo());
            } 
        }
    }
}
