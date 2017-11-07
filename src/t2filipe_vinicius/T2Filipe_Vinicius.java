/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t2filipe_vinicius;

import java.sql.SQLException;
import static t2filipe_vinicius.Metadata.getColumnsMetadata;
import static t2filipe_vinicius.Metadata.getTablesMetadata;
import static t2filipe_vinicius.Metadata.printGeneralMetadata;

/**
 *
 * @author Filipe
 */
public class T2Filipe_Vinicius {

    public static void main(String[] args) {
        try {
         //   printGeneralMetadata();
            
            // Print all the tables of the database scheme, with their names and
            // structure
            getColumnsMetadata(getTablesMetadata());
        } catch (SQLException e) {
            System.err.println("There was an error retrieving the metadata properties: " + e.getMessage());
        }
    }
}
