/* Arquivo gerado automaticamente pelo gerador de classes
 T2 Paradigmas UFSM - Filipe e Vinicius
*/

package t2filipe_vinicius.generated;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


public class Testetrab2DAO {
	public Connection abrir() {
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
		}
		catch (ClassNotFoundException e) {
			System.err.println("O Driver não foi encontrado na memória.");
		}
		try {
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123456");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	public void inserir(Testetrab2 x) {
		Connection conexao = abrir();
		try {
			PreparedStatement ps = conexao.prepareStatement("INSERT INTO testetrab2 (cl1, cl2, cl3, cl4, cl5, cl6, cl7, cl8, cl9) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, x.getCl1());
			ps.setInt(2, x.getCl2());
			ps.setDouble(3, x.getCl3());
			ps.setString(4, x.getCl4());
			ps.setString(5, x.getCl5());
			ps.setBoolean(6, x.getCl6());
			ps.setString(7, x.getCl7());
			ps.setString(8, x.getCl8());
			ps.setString(9, x.getCl9());
			ps.execute();
			ps.close();
			conexao.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public LinkedList<Testetrab2> buscarTodos() {
		Connection conexao = abrir();
		LinkedList<Testetrab2> x = new LinkedList<Testetrab2>();
		try {
			PreparedStatement ps = conexao.prepareStatement("SELECT * FROM testetrab2");
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				Testetrab2 temp = new Testetrab2();
				temp.setCl1(rs.getInt("cl1"));
				temp.setCl2(rs.getInt("cl2"));
				temp.setCl3(rs.getDouble("cl3"));
				temp.setCl4(rs.getString("cl4"));
				temp.setCl5(rs.getString("cl5"));
				temp.setCl6(rs.getBoolean("cl6"));
				temp.setCl7(rs.getString("cl7"));
				temp.setCl8(rs.getString("cl8"));
				temp.setCl9(rs.getString("cl9"));
				x.add(temp);
			}
			rs.close();
			ps.close();
			conexao.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}

	public void alterar(Testetrab2 x) {
		Connection conexao = abrir();
		try {
			PreparedStatement ps = conexao.prepareStatement("UPDATE testetrab2 SET cl2 = ?, cl3 = ?, cl4 = ?, cl5 = ?, cl6 = ?, cl7 = ?, cl8 = ?, cl9 = ? WHERE cl1 = ?");
			ps.setInt(1, x.getCl2());
			ps.setDouble(2, x.getCl3());
			ps.setString(3, x.getCl4());
			ps.setString(4, x.getCl5());
			ps.setBoolean(5, x.getCl6());
			ps.setString(6, x.getCl7());
			ps.setString(7, x.getCl8());
			ps.setString(8, x.getCl9());
			ps.setInt(9, x.getCl1());
			ps.execute();
			ps.close();
			conexao.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void excluir(Testetrab2 x) {
		Connection conexao = abrir();
		try {
			PreparedStatement ps = conexao.prepareStatement("DELETE FROM testetrab2 WHERE cl1 = ?");
			ps.setInt(1, x.getCl1());
			ps.execute();
			ps.close();
			conexao.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
