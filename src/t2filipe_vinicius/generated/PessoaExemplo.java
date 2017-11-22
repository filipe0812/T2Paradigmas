/* Arquivo gerado automaticamente pelo gerador de classes
 T2 Paradigmas UFSM - Filipe e Vinicius
*/

package t2filipe_vinicius.generated;

import java.util.LinkedList;


public class PessoaExemplo {
	private static LinkedList<Pessoa> lista;

	public static void executa() {
		PessoaDAO dao = new PessoaDAO();

		Pessoa x1 = new Pessoa();
		Pessoa x2 = new Pessoa();
		Pessoa x3 = new Pessoa();

		x1.setNome("lacvolopd");
		x1.setCodigo(1647454762);

		x2.setNome("wwqskw");
		x2.setCodigo(-1058958268);

		x3.setNome("oixiktt");
		x3.setCodigo(162032529);

		dao.inserir(x1);
		dao.inserir(x2);
		dao.inserir(x3);

		lista = dao.buscarTodos();

		if(lista.size() > 0)
			dao.excluir(lista.get(0));

		lista = dao.buscarTodos();

		if(lista.size() > 0){
			x1 = lista.get(0);
			x1.setNome("sureammfpeip");
			dao.alterar(x1);
		}
	}
}
