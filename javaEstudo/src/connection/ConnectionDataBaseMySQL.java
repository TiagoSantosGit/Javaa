package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * responsavel por fazer a conexão com o banco de dados
 * 
 * @author TiagoSantos
 *
 */
public class ConnectionDataBaseMySQL {

    private static String banco = "jdbc:mysql://localhost:3307/sys?autoReconnect=true";
    private static String user = "root";
    private static String password = "tiago";
    private static Connection connection = null;

	static {
		conectar();
	}

	public ConnectionDataBaseMySQL() {
		conectar();
	}

	private static void conectar() {
		try {
			if (connection == null) {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(banco, user, password);
				connection.setAutoCommit(false);
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro ao conectar com o banco de dados " + e.getMessage());
		}
	}

	public static Connection getConnection() {
		return connection;
	}
}
