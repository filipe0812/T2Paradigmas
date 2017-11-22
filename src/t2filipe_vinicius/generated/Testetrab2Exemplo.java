/* Arquivo gerado automaticamente pelo gerador de classes
 T2 Paradigmas UFSM - Filipe e Vinicius
*/

package t2filipe_vinicius.generated;

import java.util.LinkedList;


public class Testetrab2Exemplo {
	private static LinkedList<Testetrab2> lista;

	public static void executa() {
		Testetrab2DAO dao = new Testetrab2DAO();

		Testetrab2 x1 = new Testetrab2();
		Testetrab2 x2 = new Testetrab2();
		Testetrab2 x3 = new Testetrab2();

		x1.setCl1(1505965153);
		x1.setCl2(343651737);
		x1.setCl3(0.79482872093376);
		x1.setCl4("aymxeexhrpojld");
		x1.setCl5("mpxygya");
		x1.setCl6(false);
		x1.setCl7("sxvtu");
		x1.setCl8("wpjqjatnpyl");
		x1.setCl9("huobfooumgo");

		x2.setCl1(-1191113376);
		x2.setCl2(1237971354);
		x2.setCl3(0.7973121222867513);
		x2.setCl4("chvvwlwwvvst");
		x2.setCl5("xefvvd");
		x2.setCl6(false);
		x2.setCl7("uxupyq");
		x2.setCl8("airhhrjqii");
		x2.setCl9("nkrhwlrbumhgh");

		x3.setCl1(1881776801);
		x3.setCl2(1582666954);
		x3.setCl3(0.07172432292200459);
		x3.setCl4("jdpavsylicnb");
		x3.setCl5("farupolskoifv");
		x3.setCl6(true);
		x3.setCl7("yutqrw");
		x3.setCl8("xyfjpphwxecmns");
		x3.setCl9("tjxxbtfncocn");

		dao.inserir(x1);
		dao.inserir(x2);
		dao.inserir(x3);

		lista = dao.buscarTodos();

		if(lista.size() > 0)
			dao.excluir(lista.get(0));

		lista = dao.buscarTodos();

		if(lista.size() > 0){
			x1 = lista.get(0);
			x1.setCl2(637289111);
			x1.setCl3(0.5465112964843865);
			x1.setCl4("nkout");
			x1.setCl5("hodjgu");
			x1.setCl6(false);
			x1.setCl7("bxhsljrhis");
			x1.setCl8("cnihmq");
			x1.setCl9("rxuyutnit");
			dao.alterar(x1);
		}
	}
}
