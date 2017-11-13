/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t2filipe_vinicius;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Formatter;
/**
 *
 * @author vinicius
 */
public class CriadorDAO {
    private static Formatter file;
    public CriadorDAO(LinkedList<Tabela> tabelas) {
        for(Tabela tabela: tabelas) {
            String nomeTabela = tabela.getNome();
            String endereco;
            if(System.getProperty("os.name").contains("Linux"))
                endereco = "/home/armitage/Desktop/"+nomeTabela+".java";
            else
                endereco = "c:\\"+nomeTabela+".java";
            try {
                file = new Formatter(endereco);
            }
            catch (Exception e) {
                System.out.println("Erro em: \"file = new Formatter(endereco)\"");
                return;
            }
            
            
    }
    private static void iniciarClasse() {
        
    }
    private static void criarMetodoAbrirConexao() {
        
    }
    private static void criarMetodoInserir() {
        
    }
    private static void criarMetodoAlterar() {
        
    }
    private static void criarMetodoExcluir() {
        
    }
    private static void criarMetodoBuscarUm() {
        
    }
    private static void criarMetodoBuscarTodos() {
        
    }
    private static void finalizarClasse() {
        
    }
}
