/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t2filipe_vinicius;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;


public class Metadata {
    private static Connection connection = null;
    private static DatabaseMetaData metadata = null;
    private static LinkedList<Tabela> tabelas = new LinkedList();

    // Static block for initialization
    static {
        connection = Conector.getConexaoMySQL();

        try {
            metadata = connection.getMetaData();
        } catch (SQLException e) {
            System.err.println("There was an error getting the metadata: " + e.getMessage());
        }
    }

    public static LinkedList<Tabela> getTables(){
        tabelas = new LinkedList();
        
        try {
            getColumnsMetadata(getTablesMetadata());
        } catch (SQLException e) {
            System.err.println("There was an error retrieving the metadata properties: " + e.getMessage());
        }
        
        return tabelas;
    }
    
    private static ArrayList getTablesMetadata() throws SQLException {
        String table[] = { "TABLE" };
        ResultSet rs = null;
        ArrayList tables = null;

        //lê todos os nomes de tabelas e adiciona na lista de nomes
        rs = metadata.getTables(null, null, null, table);
        tables = new ArrayList();
        while (rs.next()) {
            String tableName = rs.getString("TABLE_NAME");
            if(!tableName.startsWith("sql_"))
                tables.add(tableName);    
        }
        return tables;
    }

    
    private static void getColumnsMetadata(ArrayList<String> tables) throws SQLException {
        ResultSet rs = null;
        
        
        //varre todas as tabelas adicionado na lista de tabelas com as colunas
        for (String actualTable : tables) {
            
            rs = metadata.getColumns(null, null, actualTable, null);            
            Tabela tabelaAtual = new Tabela(actualTable);
            
            //varre todas as colunas da tabela adicionado elas na lista de tabelas
            while (rs.next()) {
                tabelaAtual.addColuna(rs.getString("COLUMN_NAME"), rs.getString("TYPE_NAME"));
            }
            
            //adiciona a tabela na lista, com o nome e colunas inserido anteriormente 
            tabelas.add(tabelaAtual);
        }
    }
}
