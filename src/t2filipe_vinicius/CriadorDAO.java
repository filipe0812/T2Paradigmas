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
            
            //Cria metodo para inserir dados na tabela do banco de dados
            criarMetodoInserir(tabela);
            
            //Cria metodo que retorna todos objetos na tabela
            criarMetodoBuscarTodos(tabela);
            
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
        String metodo = "\tpublic void inserir("+nomeClasse+" x) {\n\t\tConnection conexao = abrir();\n\t\ttry {\n\t\t\tPreparedStatement ps = conexao.prepareStatement(\"INSERT INTO "+nomeTabela+" (";
        int numeroColunas = t.getNumerColunas();
        int z=0;
        for(Coluna coluna: t.getColunas()) {
            z++;
            metodo += coluna.getNome();
            if(z==numeroColunas)
                break;
            metodo += ", ";
        }
        metodo += ") VALUES (";
        for(int x=0; x<numeroColunas; x++) {
            metodo += "?";
            if(x==(numeroColunas-1))
                metodo +=")\");\n";
            else
                metodo +=", ";
        }
        int y = 0;
        for(Coluna coluna: t.getColunas()) {
            y ++;
            String tipo = Criador.traduzTipoDado(coluna.getTipo());
            String nomeMetodoGet = coluna.getNome();
            metodo += "\t\t\tps.set"+tipo.substring(0, 1).toUpperCase().concat(tipo.substring(1))+"("+y+", x.get"+nomeMetodoGet.substring(0, 1).toUpperCase().concat(nomeMetodoGet.substring(1))+"());\n" ;
        }
        metodo += "\t\t}\n\t\tcatch (SQLException e) {\n\t\t\te.printStackTrace();\n\t\t}\n\t}\n";
        
        file.format(metodo);
    }
    private static void criarMetodoAlterar() {
        
    }
    private static void criarMetodoExcluir() {
        
    }
    private static void criarMetodoBuscarUm() {
        //nao sei se precisa desse metodo
    }
    private static void criarMetodoBuscarTodos(Tabela t) {
        String nomeTabela = t.getNome();
        String nomeClasse = nomeTabela.substring(0, 1).toUpperCase().concat(nomeTabela.substring(1));
        String metodo = "\tpublic Collection<"+nomeClasse+"> buscarTodos() {\n\t\tConnection conexao = abrir();\n\t\tCollection<"+nomeClasse+"> x = new ArrayList<"+nomeClasse+">();\n\t\ttry {\n\t\t\tStatement s = conexao.createStatement();\n\t\t\tResultSet rs = s.executeQuery(\"SELECT * FROM "+nomeTabela+"\");\n\t\t\twhile (rs.next()) {\n\t\t\t\t"+nomeClasse+" temp = new "+nomeClasse+"();\n";
        for(Coluna coluna: t.getColunas()) {
            String nomeAtributo = coluna.getNome();
            String tipo = Criador.traduzTipoDado(coluna.getTipo());
            tipo = tipo.substring(0, 1).toUpperCase().concat(tipo.substring(1));
            metodo += "\t\t\t\ttemp.set"+nomeAtributo.substring(0, 1).toUpperCase().concat(nomeAtributo.substring(1))+"(rs.get"+tipo+"(\""+nomeAtributo+"\"));\n";
        }
        metodo += "\t\t\t\tx.add(temp);\n\t\t\t}\n\t\t\trs.close();\n\t\t\tconexao.close();\n\t\t}\n\t\tcatch (SQLException e) {\n\t\t\te.printStackTrace();\n\t\t}\n\t\treturn x;\n\t}\n";
        file.format(metodo);
    }
    private static void finalizarClasseDAO() {
        file.format("}\n");
        file.close();
    }
}
