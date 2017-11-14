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
            
            //Cria a classe
            iniciarClasseDAO(nomeTabela);
      
    }
    private static void iniciarClasseDAO(String nomeTabela) {
        String nomeClasse = nomeTabela.substring(0, 1).toUpperCase().concat(nomeTabela.substring(1));
        //nome da classeDAO e abre chaves
        file.format("public class %sDAO {\n",nomeClasse);
    }
    private static void criarMetodoAbrirConexao() {
        file.format("\tpublic Connection abrir() {\n\t\tConnection c = null;\n\t\ttry {\n\t\t\tClass.forName(BD.JDBC_DRIVER);\n\t\t}\n\t\tcatch (ClassNotFoundException e) {\n\t\t\tSystem.err.println(\"O Driver não foi encontrado na memória.\");\n\t\t}\n\t\ttry {\n\t\t\tc = DriverManager.getConnection(BD.URL_CONEXAO, BD.USUARIO, BD.SENHA);\n\t\t}\n\t\tcatch (SQLException e) {\n\t\t\te.printStackTrace();\n\t\t}\n\t\treturn c;\n\t}\n");
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
    private static void finalizarClasseDAO() {
        
    }
}
