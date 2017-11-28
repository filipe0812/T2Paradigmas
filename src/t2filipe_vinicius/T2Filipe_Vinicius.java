/*
 * T2 paradigmas UFSM 2017/2
 * Filipe Albanio e Vinicius Bohrer
 * Para salvar os arquivos em uma pasta diferente, só editar o path na chamada do criador que quiser modificar
 */
package t2filipe_vinicius;

import java.util.LinkedList;


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
        
        System.out.println("\n\nExecutando criador:\n");
        new Criador       (tabelas, 
                "C:\\", 
                "/home/armitage/Desktop/");
        
        System.out.println("\n\nExecutando criador DAO:\n");
        new CriadorDAO    (tabelas, 
                "C:\\", 
                "/home/armitage/Desktop/");
        
        System.out.println("\n\nExecutando criador Exemplo:\n");
        new CriadorExemplo(tabelas, 
                "C:\\", 
                "/home/armitage/Desktop/");
    }
}
