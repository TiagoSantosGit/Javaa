package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCursoJsp;
import connection.SingleConnection;

public class DaoUsuario {
	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}

	public void Salvar(BeanCursoJsp usuario) {

		try {
			String sql = "insert into usuario(login, senha, nome, telefone) values(?,?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getTelefone());
			insert.execute();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
	}

	public List<BeanCursoJsp> listar() throws Exception {
		List<BeanCursoJsp> listar = new ArrayList<BeanCursoJsp>();
		String sql = "select * from usuario";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			beanCursoJsp.setId(resultSet.getLong("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setNome(resultSet.getString("nome"));
			beanCursoJsp.setTelefone(resultSet.getString("telefone"));
			listar.add(beanCursoJsp);
		}
		return listar;
	}

	public void delete(String id) {

		try {
			String sql = "delete from usuario where id = '" + id + "'";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
	}

	public BeanCursoJsp consultar(String id) throws Exception {

		String sql = "select * from usuario where id='" + id + "'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			beanCursoJsp.setId(resultSet.getLong("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setNome(resultSet.getString("nome"));
			beanCursoJsp.setTelefone(resultSet.getString("telefone"));
			return beanCursoJsp;
		}
		return null;
	}

	public void atualizar(BeanCursoJsp usuario) {

		try {
			String sql = "update usuario set login = ?, senha = ?, nome = ?, telefone = ? where id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, usuario.getLogin());
			preparedStatement.setString(2, usuario.getSenha());
			preparedStatement.setString(3, usuario.getNome());
			preparedStatement.setString(4, usuario.getTelefone());
			preparedStatement.setLong(5, usuario.getId());
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
	}
	public boolean validarLogin(String login) throws Exception {

		String sql = "select count(login) as qtd from usuario where login ='" + login + "'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;
		}
		return false;
	}	
}
