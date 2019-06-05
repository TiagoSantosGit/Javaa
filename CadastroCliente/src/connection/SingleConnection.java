package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Faz conexão com o banco de dados a cada início de sessão
 * 
 * @author TiagoSantos
 *
 */
public class SingleConnection {

	private static String base = "jdbc:postgresql://localhost:5432/cadcli?autoReconnect=true";
	private static String user = "postgres";
	private static String pass = "tiago";
	private static Connection connection = null;

	static {
		connect();
	}

	public SingleConnection() {
		connect();
	}

	public static void connect() {
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(base, user, pass);
				connection.setAutoCommit(false);
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro ao conectar com a base de dados!! " + e.getMessage());
		}
	}

	public static Connection getConnection() {
		return connection;
	}
}
