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


public class Metadata {

    static Connection connection = null;
    static DatabaseMetaData metadata = null;

    // Static block for initialization
    static {
        connection = Conector.getConexaoMySQL();

        try {
            metadata = connection.getMetaData();
        } catch (SQLException e) {
            System.err.println("There was an error getting the metadata: " + e.getMessage());
        }
    }


    public static void printGeneralMetadata() throws SQLException {
        System.out.println("Database Product Name: "
                        + metadata.getDatabaseProductName());
        System.out.println("Database Product Version: "
                        + metadata.getDatabaseProductVersion());
        System.out.println("Logged User: " + metadata.getUserName());
        System.out.println("JDBC Driver: " + metadata.getDriverName());
        System.out.println("Driver Version: " + metadata.getDriverVersion());
        System.out.println("\n");
    }


    public static ArrayList getTablesMetadata() throws SQLException {
        String table[] = { "TABLE" };
        ResultSet rs = null;
        ArrayList tables = null;

        // receive the Type of the object in a String array.
        rs = metadata.getTables(null, null, null, table);
        tables = new ArrayList();
        while (rs.next()) {
            String tableName = rs.getString("TABLE_NAME");
            if(!tableName.startsWith("sql_"))
                tables.add(tableName);
            
        }
        return tables;
    }

    
    public static void getColumnsMetadata(ArrayList<String> tables) throws SQLException {
        ResultSet rs = null;
        
        // Print the columns properties of the actual table
        for (String actualTable : tables) {
            rs = metadata.getColumns(null, null, actualTable, null);
            System.out.println(actualTable.toUpperCase());
            while (rs.next()) {
                System.out.println(rs.getString("COLUMN_NAME") + " "
                                + rs.getString("TYPE_NAME") + " "
                                + rs.getString("COLUMN_SIZE"));
            }
            System.out.println("\n");
        }
    }
}
