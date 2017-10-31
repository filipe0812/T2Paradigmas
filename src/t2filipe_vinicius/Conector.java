/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t2filipe_vinicius;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Filipe
 */
public class Conector {
    private static String status = "Não conectou...";
    
    public static Connection getConexaoMySQL() {
        
        Connection connection = null;          
        try {

                            
            Class.forName("com.mysql.jdbc.Driver");
   
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/T1Paradigmas", "root", "123456");
 
 
            if (connection != null) {
                status = ("STATUS--->Conectado com sucesso!");
            } else {
                status = ("STATUS--->Não foi possivel realizar conexão");
            }
 
            return connection;
        } catch (ClassNotFoundException e) {  //Driver não encontrado
            System.out.println("O driver expecificado nao foi encontrado.");
            e.printStackTrace();
 
            return null;
        } catch (SQLException e) {
 
            //Não conseguindo se conectar ao banco
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            return null;
        }
    }
 
    
    public static boolean FecharConexao() {
        try {
            getConexaoMySQL().close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    
    public static Connection ReiniciarConexao() {
        FecharConexao();

        return getConexaoMySQL();
    }

    
    public static String statusConection() {
        return status;
    }
}

