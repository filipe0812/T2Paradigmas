/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t2filipe_vinicius;

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
            String nomeClasse = nomeTabela.substring(0, 1).toUpperCase().concat(nomeTabela.substring(1));
            String endereco;
            if(System.getProperty("os.name").contains("Linux"))             //TODO: pegar endereço como parametro na main
                endereco = "/home/armitage/Desktop/"+nomeClasse+"DAO.java";
            else
                endereco = "c:\\"+nomeClasse+"DAO.java";
            try {
                file = new Formatter(endereco);
            }
            catch (Exception e) {
                System.out.println("Erro em: \"file = new Formatter(endereco)\"");
                return;
            }
            
            //Cria a classe
            iniciarClasseDAO(nomeClasse);
            
            //Cria primeiro metodo de comunicaçao com o banco de dados
            criarMetodoAbrirConexao();
            
            //Cria Metodo para inserir dados na tabela do banco de dados
            criarMetodoInserir(tabela);
            
            
            //fecha chaves e fecha arquivo
            finalizarClasseDAO();
            
            //confirma que o arquivo ja foi criado
            System.out.format("Arquivo %sDAO.java criado\n\n",nomeTabela);
        }
    }
    private static void iniciarClasseDAO(String nomeClasse) {
        //nome da classeDAO e abre chaves
        file.format("public class %sDAO {\n",nomeClasse);
    }
    private static void criarMetodoAbrirConexao() { //TODO adicionar servidor e senha por parametro
        file.format("\tpublic Connection abrir() {\n\t\tConnection c = null;\n\t\ttry {\n\t\t\tClass.forName(BD.JDBC_DRIVER);\n\t\t}\n\t\tcatch (ClassNotFoundException e) {\n\t\t\tSystem.err.println(\"O Driver não foi encontrado na memória.\");\n\t\t}\n\t\ttry {\n\t\t\tc = DriverManager.getConnection(BD.URL_CONEXAO, BD.USUARIO, BD.SENHA);\n\t\t}\n\t\tcatch (SQLException e) {\n\t\t\te.printStackTrace();\n\t\t}\n\t\treturn c;\n\t}\n");
    }
    private static void criarMetodoInserir(Tabela t) {
        String nomeTabela = t.getNome();
        String nomeClasse = nomeTabela.substring(0, 1).toUpperCase().concat(nomeTabela.substring(1));
        String metodo1 = "\tpublic void inserir("+nomeClasse+" x) {\n\t\tConnection conexao = abrir();\n\t\ttry {\n\t\t\tPreparedStatement ps = conexao.prepareStatement(\"INSERT INTO "+nomeTabela+" (";
        int numeroColunas = t.getNumerColunas();
        int z=0;
        for(Coluna coluna: t.getColunas()) {
            z++;
            metodo1 += coluna.getNome();
            if(z==numeroColunas)
                break;
            metodo1 += ", ";
        }
        metodo1 += ") VALUES (";
        for(int x=0; x<numeroColunas; x++) {
            metodo1 += "?";
            if(x==(numeroColunas-1))
                metodo1 +=")\");\n";
            else
                metodo1 +=", ";
        }
        int y = 0;
        for(Coluna coluna: t.getColunas()) {
            y ++;
            String tipo = Criador.traduzTipoDado(coluna.getTipo());
            String nomeMetodoGet = coluna.getNome();
            metodo1 += "\t\t\tps.set"+tipo.substring(0, 1).toUpperCase().concat(tipo.substring(1))+"("+y+", x.get"+nomeMetodoGet.substring(0, 1).toUpperCase().concat(nomeMetodoGet.substring(1))+"());\n" ;
        }
        metodo1 += "\t\t}\n\t\tcatch (SQLException e) {\n\t\t\te.printStackTrace();\n\t\t}\n\t}\n";
        
        file.format(metodo1);
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
        file.format("}\n");
        file.close();
    }
}
