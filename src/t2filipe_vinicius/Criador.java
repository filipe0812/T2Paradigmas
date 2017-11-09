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
public class Criador {
    private static Formatter file;
    public Criador(LinkedList<Tabela> tabelas) {
        for(Tabela tabela: tabelas) {
            String nomeTabela = tabela.getNome();
            String endereco;
            if(System.getProperty("os.name").contains("Linux"))
                endereco = "/media/Shared/"+nomeTabela+".java";
            else
                endereco = "c:\\"+nomeTabela+".java";   //TODO arrumar endereço windows
            try {
                file = new Formatter(endereco);
            } catch (FileNotFoundException e) {
                System.out.println("Erro em: \"file = new Formatter(endereco)\"");
                return;
            }
            
            //Cria a classe
            iniciarClasse(nomeTabela);
            
            //Cria os Atributos
            for(Coluna coluna: tabela.getColunas()) {
                criarAtributos(coluna.getNome().toLowerCase(),coluna.getTipo());
            }
            
            //Cria os getters e setters
            for(Coluna coluna: tabela.getColunas()) {
                criarMetodos(coluna.getNome().toLowerCase(),coluna.getTipo());
            }
            
            //Finaliza a classe
            finalizarClasse();
            
            System.out.format("Arquivo %s.java criado\n",nomeTabela); //confirma que o arquivo ja foi criado
        }
    }
    private static void iniciarClasse(String nomeTabela) {
        String nomeClasse = nomeTabela.substring(0, 1).toUpperCase().concat(nomeTabela.substring(1)); //primeira maiuscula
        file.format("class %s {\n",nomeClasse);
    }
    private static void criarAtributos(String nomeColuna, String tipoColuna) {
        String tipoDado = tipoColuna;
        switch(tipoDado) {
            case "varchar": tipoDado = "String";break;
            case "int4": tipoDado = "Integer";break;
            //TODO adicionar mais tipos de dados
        }
        //escreve atributo
        file.format("\tprivate %s %s;\n", tipoDado, nomeColuna);
    }
    private static void criarMetodos(String nomeColuna, String tipoColuna) {
        String nomeMetodo = nomeColuna.substring(0, 1).toUpperCase().concat(nomeColuna.substring(1)); //primeira maiuscula
        String tipoDado = tipoColuna;
        switch(tipoDado) {
            case "varchar": tipoDado = "String";break;
            case "int4": tipoDado = "Integer";break;
            //TODO adicionar mais tipos de dados
        }
        //escreve set
        file.format("\n\tpublic void set%s(%s %s) {\n\t\tthis.%s = %s;\n\t}\n",nomeMetodo,tipoDado,nomeColuna,nomeColuna,nomeColuna);
        //escreve get
        file.format("\tpublic %s get%s() {\n\t\treturn this.%s;\n\t}\n", tipoDado, nomeMetodo, nomeColuna);
    }
    private static void finalizarClasse() {
        file.format("}\n");
        file.close();
    }
}


// ao inves de colocar aqueles 2 switch's da pra fazer um metodo so pra "traduzir" o tipo do dado