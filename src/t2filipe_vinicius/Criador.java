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
public class Criador {
    private static Formatter file;
    
    
    public Criador(LinkedList<Tabela> tabelas, String windowsPath, String linuxPath) {
        for(Tabela tabela: tabelas) {
            
            if(tabela.getHasSpaces()){
                System.err.println("Tabela: \"" + tabela.getNome() + "\" contem espacos no nome da tabela ou de alguma coluna, nao sera criado classes");
                continue;
            }
            
            String nomeTabela = tabela.getNome();
            String nomeClasse = nomeTabela.substring(0, 1).toUpperCase().concat(nomeTabela.substring(1));
            String endereco;
            
            if(System.getProperty("os.name").contains("Linux"))
                endereco = linuxPath + nomeClasse + ".java";
            else
                endereco = windowsPath + nomeClasse+".java";
            try {
                file = new Formatter(endereco);
            } catch (Exception e) {
                System.err.println("Erro em: \"file = new Formatter("+endereco+")\"");
                return;
            }
            
            //Cria a classe
            iniciarClasse(nomeClasse);
            
            //Cria os Atributos
            for(Coluna coluna: tabela.getColunas()) {
                criarAtributos(coluna.getNome(),coluna.getTipo());
            }
            
            //Cria os getters e setters
            for(Coluna coluna: tabela.getColunas()) {
                criarMetodos(coluna.getNome(),coluna.getTipo());
            }
            
            //Finaliza a classe
            finalizarClasse();
            
            //confirma que o arquivo ja foi criado
            System.out.format("Arquivo %s.java criado\n",nomeTabela);
        }
    }
    
    
    private static void iniciarClasse(String nomeClasse) {
        //nome da classe e abre chaves
        
        file.format("/* Arquivo gerado automaticamente pelo gerador de classes\n"
                + " T2 Paradigmas UFSM - Filipe e Vinicius\n*/\n\n"
                + "package t2filipe_vinicius.generated;\n\n\n"
                + "public class %s {\n",nomeClasse);
    }
    
    
    private static void criarAtributos(String nomeColuna, String tipoColuna) {
        String nomeAtributo = nomeColuna.toLowerCase();
        
        String tipoDado = traduzTipoDado(tipoColuna);
        //escreve atributo
        file.format("\tprivate %s %s;\n", tipoDado, nomeAtributo);
    }
    
    
    private static void criarMetodos(String nomeColuna, String tipoColuna) {
        nomeColuna = nomeColuna.toLowerCase();
        
        String nomeMetodo = nomeColuna.substring(0, 1).toUpperCase().concat(nomeColuna.substring(1));
        
        String tipoDado = traduzTipoDado(tipoColuna);
        
        //escreve set
        file.format("\n\tpublic void set%s(%s %s) {\n\t\tthis.%s = %s;\n\t}\n",nomeMetodo,tipoDado,nomeColuna,nomeColuna,nomeColuna);
        //escreve get
        file.format("\tpublic %s get%s() {\n\t\treturn this.%s;\n\t}\n", tipoDado, nomeMetodo, nomeColuna);
    }
    
    
    private static void finalizarClasse() {
        //fecha chaves e fecha arquivo
        file.format("}\n");
        file.close();
    }
    
    
    public static String traduzTipoDado(String tipoDadoDB) { 
               
        switch(tipoDadoDB) {
            case "varchar"  : return "String";
       
            case "int8":
            case "int4":
            case "int2"     : return "int";
       
            case "float8"   : return "double";
            
            case "bool"     : return "boolean";
        }
        return "String"; //Default
    }
}
