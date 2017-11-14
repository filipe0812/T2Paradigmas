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
    private static Connection connection = null;
    
    private static String driverDB;
    private static String urlDB;
    private static String userDB;
    private static String loginDB;
    
    public static void configDB(String driver, String url, String user, String login){
        driverDB = driver;
        urlDB = url;
        userDB = user;
        loginDB = login;
    }
    
    public static Connection getDBConection() {
        
        connection = null;          
        try {
                            
            Class.forName(driverDB);
   
            connection = DriverManager.getConnection(urlDB, userDB, loginDB);
 
 
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
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    
    public static Connection ReiniciarConexao() {
        FecharConexao();

        return getDBConection();
    }

    
    public static String statusConection() {
        return status;
    }
}

