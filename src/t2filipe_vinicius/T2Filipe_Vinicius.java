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
    public final class DBinfo{
        public final static String driver = "org.postgresql.Driver";
        public final static String url    = "jdbc:postgresql://localhost:5432/postgres";
        public final static String user   = "postgres";
        public final static String login  = "123456";
    } 
    
    public static void main(String[] args) {
        
        Conector.configDB(DBinfo.driver, DBinfo.url, DBinfo.user, DBinfo.login);
        
        //acessa o banco e copia todos os dados necessários para a memória
        LinkedList<Tabela> tabelas = Metadata.getTables();
        
        
        //repete o loop para cada tabela dentro da lista só pra mostrar na tela
        for(int x = 0; x < tabelas.size(); x++){
            System.out.println("Nome da tabela: " + tabelas.get(x).getNome() + ", PK: " + tabelas.get(x).getChavePrimaria());
            
            //repete o loop para cada coluna dentro da tabela
            for(int y = 0; y < tabelas.get(x).getNumerColunas(); y++){
                System.out.print("Coluna: " + tabelas.get(x).getColunaById(y).getNome());
                System.out.println(", tipo: " + tabelas.get(x).getColunaById(y).getTipo());
            }
            System.out.println(""); //extra enter
        }
        
        new Criador(tabelas, "C:\\trab2Para\\");
        new CriadorDAO(tabelas, "C:\\trab2Para\\");
    }
}
