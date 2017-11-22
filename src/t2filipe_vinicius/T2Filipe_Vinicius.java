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
        
        System.out.println("\nExecutando criador:");
        new Criador       (tabelas, 
                "C:\\Users\\Filipe\\Google Drive\\Trabalho\\Projects\\Net Beans - java\\Trabalho02Paradigmas\\T2Filipe_Vinicius\\src\\t2filipe_vinicius\\generated\\", 
                "/home/armitage/Desktop/");
        
        System.out.println("\nExecutando criador DAO:");
        new CriadorDAO    (tabelas, 
                "C:\\Users\\Filipe\\Google Drive\\Trabalho\\Projects\\Net Beans - java\\Trabalho02Paradigmas\\T2Filipe_Vinicius\\src\\t2filipe_vinicius\\generated\\", 
                "/home/armitage/Desktop/");
        
        System.out.println("\nExecutando criador Exemplo:");
        new CriadorExemplo(tabelas, 
                "C:\\Users\\Filipe\\Google Drive\\Trabalho\\Projects\\Net Beans - java\\Trabalho02Paradigmas\\T2Filipe_Vinicius\\src\\t2filipe_vinicius\\generated\\", 
                "/home/armitage/Desktop/");
    }
}
