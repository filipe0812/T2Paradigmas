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


public class PessoaDAO {
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

	public void inserir(Pessoa x) {
		Connection conexao = abrir();
		try {
			PreparedStatement ps = conexao.prepareStatement("INSERT INTO pessoa (nome, codigo) VALUES (?, ?)");
			ps.setString(1, x.getNome());
			ps.setInt(2, x.getCodigo());
			ps.execute();
			ps.close();
			conexao.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public LinkedList<Pessoa> buscarTodos() {
		Connection conexao = abrir();
		LinkedList<Pessoa> x = new LinkedList<Pessoa>();
		try {
			PreparedStatement ps = conexao.prepareStatement("SELECT * FROM pessoa");
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				Pessoa temp = new Pessoa();
				temp.setNome(rs.getString("nome"));
				temp.setCodigo(rs.getInt("codigo"));
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

	public void alterar(Pessoa x) {
		Connection conexao = abrir();
		try {
			PreparedStatement ps = conexao.prepareStatement("UPDATE pessoa SET nome = ? WHERE codigo = ?");
			ps.setString(1, x.getNome());
			ps.setInt(2, x.getCodigo());
			ps.execute();
			ps.close();
			conexao.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void excluir(Pessoa x) {
		Connection conexao = abrir();
		try {
			PreparedStatement ps = conexao.prepareStatement("DELETE FROM pessoa WHERE codigo = ?");
			ps.setInt(1, x.getCodigo());
			ps.execute();
			ps.close();
			conexao.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
