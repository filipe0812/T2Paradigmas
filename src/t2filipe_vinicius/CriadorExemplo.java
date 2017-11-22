/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t2filipe_vinicius;

import java.util.LinkedList;
import java.util.Formatter;
import java.util.Random;

/**
 *
 * @author Filipe
 */
public class CriadorExemplo {
    private static Formatter file;
    
    public CriadorExemplo(LinkedList<Tabela> tabelas, String windowsPath, String linuxPath){
        for(Tabela tabela: tabelas) {
            
            if(tabela.getHasSpaces()){
                continue;
            }
            
            if(tabela.getCreateDAO() == false){
                continue;
            }
            
            String nomeTabela = tabela.getNome();
            String nomeClasse = nomeTabela.substring(0, 1).toUpperCase().concat(nomeTabela.substring(1));
            String endereco;
            
            if(System.getProperty("os.name").contains("Linux"))            
                endereco = linuxPath + nomeClasse + "Exemplo.java";
            else
                endereco = windowsPath + nomeClasse + "Exemplo.java";
            try {
                file = new Formatter(endereco);
            }
            catch (Exception e) {
                System.err.println("Erro em: \"file = new Formatter("+endereco+")\"");
                return;
            }
            
            //Cria a classe
            iniciarClasseExemplo(nomeClasse);
            
            //inicializa variáveis com valores aleatorios
            for(int index = 0; index < 3; index++){
                geraRandomExemplo(index, tabela);
            }
                
            //insere variaveis no banco de dados
            insereBancoExemplo();
            
            //carrega do banco os dados
            carregaBancoExemplo();
            
            //excluir do banco
            excluirBancoExemplo();
            
            //carrega do banco os dados
            carregaBancoExemplo();
            
            //alterar item no banco
            alterarBancoExemplo(tabela);
            
            //fecha chaves e fecha arquivo
            finalizarClasseExemplo();
            
            //confirma que o arquivo ja foi criado
            System.out.format("Arquivo %sExemplo.java criado\n",nomeTabela);
        }
    }
    
    private static void iniciarClasseExemplo(String nomeClasse) {
        //nome da classe e abre chaves
        
        file.format("/* Arquivo gerado automaticamente pelo gerador de classes\n"
                + " T2 Paradigmas UFSM - Filipe e Vinicius\n*/\n\n"
                + "package t2filipe_vinicius.generated;\n\n"
                + "import java.util.LinkedList;\n\n\n"
                + "public class %sExemplo {\n"
                + "\tprivate static LinkedList<%s> lista;\n\n"
                + "\tpublic static void executa() {\n"
                + "\t\t%sDAO dao = new %sDAO();\n\n"
                + "\t\t%s x1 = new %s();\n"
                + "\t\t%s x2 = new %s();\n"
                + "\t\t%s x3 = new %s();\n\n", nomeClasse, nomeClasse, nomeClasse, nomeClasse, nomeClasse, nomeClasse, nomeClasse, nomeClasse, nomeClasse, nomeClasse);
    }
    
    private static void geraRandomExemplo(int index, Tabela t) {
        String nomeTabela = t.getNome();
        String nomeClasse = nomeTabela.substring(0, 1).toUpperCase().concat(nomeTabela.substring(1));
        String metodo = "";
        
        index++;
        
        for(Coluna coluna: t.getColunas()) {
            String nomeColuna = coluna.getNome().substring(0, 1).toUpperCase().concat(coluna.getNome().substring(1));
            metodo += "\t\tx" + index + ".set" + nomeColuna + "(" + generateValue(coluna.getTipo()) + ");\n";
        }
        metodo += "\n";
        file.format(metodo);
    }
    
    private static void insereBancoExemplo() {
        file.format("\t\tdao.inserir(x1);\n"
                + "\t\tdao.inserir(x2);\n"
                + "\t\tdao.inserir(x3);\n\n");
    }
    
    private static void carregaBancoExemplo() {
        file.format("\t\tlista = dao.buscarTodos();\n\n");
    }
    
    private static void excluirBancoExemplo() {
        file.format("\t\tif(lista.size() > 0)\n"
                + "\t\t\tdao.excluir(lista.get(0));\n\n");
    }
    
    private static void alterarBancoExemplo(Tabela t) {
        String nomeTabela = t.getNome();
        String nomeClasse = nomeTabela.substring(0, 1).toUpperCase().concat(nomeTabela.substring(1));
        String metodo = "";
        
        metodo += "\t\tif(lista.size() > 0){\n"
                + "\t\t\tx1 = lista.get(0);\n";
        
        for(Coluna coluna: t.getColunas()) {
            
            if(coluna.getNome().equals(t.getChavePrimaria())){
                continue;
            }
            
            String nomeColuna = coluna.getNome().substring(0, 1).toUpperCase().concat(coluna.getNome().substring(1));
            metodo += "\t\t\tx1.set" + nomeColuna + "(" + generateValue(coluna.getTipo()) + ");\n";
        }
        
        metodo += "\t\t\tdao.alterar(x1);\n"
                + "\t\t}\n";
        
        file.format(metodo);
    }
    
    private static void finalizarClasseExemplo() {
        file.format("\t}\n}\n");
        file.close();
    }
    
    private static String generateValue(String tipoColuna){
        switch(Criador.traduzTipoDado(tipoColuna)){
            case "int"     : return (new Integer ((new Random()).nextInt    ())).toString();
            case "double"  : return (new Double  ((new Random()).nextDouble ())).toString();
            case "boolean" : return (new Boolean ((new Random()).nextBoolean())).toString();
        }
        
        //default eh string
        String resp = "\"";
        char caractere;
        int repeticao = (new Random()).nextInt(10);
        
        //varia entre 5 e 14
        for(int i = 0; i < repeticao + 5; i++){
            caractere = (char) ('a' + (new Random()).nextInt(25));
            
            resp += caractere;
        }
        
        resp += "\"";
        return resp;
    }
}
