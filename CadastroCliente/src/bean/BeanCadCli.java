package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.SingleConnection;

public class BeanCadCli {

	private int codigo;
	private String nome;
	private String endereco;
	private int numero;
	private Connection connection = SingleConnection.getConnection();

	public boolean salveCli(BeanCadCli model) {

		try {
			String sql = "insert into cliente ( nome, endereco, numero) values(?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, model.getNome());
			insert.setString(2, model.getEndereco());
			insert.setInt(3, model.getNumero());
			insert.execute();
			connection.commit(); // salva o banco
			return true;
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return false;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
}
