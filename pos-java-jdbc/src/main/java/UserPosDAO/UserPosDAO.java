package UserPosDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaoJDBC.SingleConnection;
import model.BeanUserfone;
import model.Telefone;
import model.UserPosJava;

public class UserPosDAO {
	private Connection connection;

	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(UserPosJava userposjava) {
		try {
			String sql = "insert into userposjava ( nome, email) values(?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(2, userposjava.getNome());
			insert.setString(3, userposjava.getEmail());
			insert.execute();
			connection.commit(); // salva o banco
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public List<UserPosJava> listar() throws Exception {
		List<UserPosJava> list = new ArrayList<UserPosJava>();

		String sql = "select * from userposjava";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		;

		while (resultado.next()) {
			UserPosJava userposjava = new UserPosJava();
			userposjava.setId(resultado.getLong("id"));
			userposjava.setNome(resultado.getString("nome"));
			userposjava.setEmail(resultado.getString("email"));
		}
		return list;
	}

	public UserPosJava buscar(int id) throws Exception {
		UserPosJava userposjava = new UserPosJava();
		String sql = "select * from userposjava where id = " + id;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			userposjava.setId(resultado.getLong("id"));
			userposjava.setNome(resultado.getString("nome"));
			userposjava.setEmail(resultado.getString("email"));
		}
		return userposjava;
	}

	public void atualizar(UserPosJava userposjava) {
		try {
			String sql = "update userposjava set nome = ? where id = " + userposjava.getId();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userposjava.getNome());
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void deletar(long id) {
		try {
			String sql = "delete from userposjava where id = " + id;
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void salvarTelefone(Telefone telefone) {
		try {
			String sql = "insert into telefoneuser ( numero, tipo, usuario) values(?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, telefone.getUsuario());
			insert.execute();
			connection.commit(); // salva o banco
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public List<BeanUserfone> listaUserFone(Long idUser) {
		List<BeanUserfone> list = new ArrayList<BeanUserfone>();

		String sql = "select nome, numero, email from telefoneuser as fone inner join userposjava as userp on fone.usuariopessoa - userp.id where userp.id = " + idUser;;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();
			while (resultado.next()) {
				BeanUserfone beanuserfone = new BeanUserfone();
				beanuserfone.setNumero(resultado.getString("id"));
				beanuserfone.setNome(resultado.getString("nome"));
				beanuserfone.setEmail(resultado.getString("email"));
				list.add(beanuserfone);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
