package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.BeanTelefones;
import connection.SingleConnection;

public class DaoTelefones {
	private Connection connection;

	public DaoTelefones() {
		connection = SingleConnection.getConnection();
	}
	
	public void Salvar(BeanTelefones fone) {

		try {
			String sql = "insert into telefone(numero, tipo, usuario) values(?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, fone.getNumero());
			insert.setString(2, fone.getTipo());
			insert.setLong(3, fone.getUsuario());
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

	public List<BeanTelefones> listar(Long user) throws Exception {
		List<BeanTelefones> listar = new ArrayList<BeanTelefones>();
		String sql = "select * from telefone where usuario =" + user;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			BeanTelefones beanTelefones = new BeanTelefones();
			beanTelefones.setId(resultSet.getLong("id"));
			beanTelefones.setNumero(resultSet.getString("numero"));
			beanTelefones.setTipo(resultSet.getString("tipo"));
			beanTelefones.setUsuario(resultSet.getLong("usuario"));
			listar.add(beanTelefones);
		}
		return listar;
	}

	public void delete(String id) {

		try {
			String sql = "delete from telefone where id = '" + id + "'";
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

	public BeanTelefones consultar(String id) throws Exception {

		String sql = "select * from telefone where id='" + id + "'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			BeanTelefones beanTelefones = new BeanTelefones();
			beanTelefones.setId(resultSet.getLong("id"));
			beanTelefones.setNumero(resultSet.getString("numero"));
			beanTelefones.setTipo(resultSet.getString("tipo"));
			beanTelefones.setUsuario(resultSet.getLong("usuario"));
			return beanTelefones;
		}
		return null;
	}
}
