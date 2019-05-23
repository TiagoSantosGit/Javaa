package pos_java_jdbc.pos_java_jdbc;

import org.junit.Test;

import conexaoJDBC.SingleConnection;

public class TesteBancoJDBC {

	@Test
	public void iniBanco() {
		SingleConnection.getConnection();
	}
}
