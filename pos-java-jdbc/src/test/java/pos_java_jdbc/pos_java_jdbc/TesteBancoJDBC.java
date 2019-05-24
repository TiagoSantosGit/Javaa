package pos_java_jdbc.pos_java_jdbc;

import java.util.List;

import org.junit.Test;

import UserPosDAO.UserPosDAO;
import model.BeanUserfone;
import model.UserPosJava;

public class TesteBancoJDBC {

	@Test
	public void iniBanco() {
//		SingleConnection.getConnection();
		UserPosDAO userPosDAO = new UserPosDAO();
		UserPosJava userposjava = new UserPosJava();

		userposjava.setNome("Matheus teste");
		userposjava.setEmail("matheus@gmail.com");

		userPosDAO.salvar(userposjava);
	}

	@Test
	public void initLisar() {
		UserPosDAO dao = new UserPosDAO();
		try {
			List<UserPosJava> list = dao.listar();
			for (UserPosJava userposjava : list) {
				System.out.println(userposjava);
				System.out.println("---------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void initBuscar() {
		UserPosDAO dao = new UserPosDAO();
		try {
			UserPosJava userposjava = dao.buscar(2);
			System.out.println(userposjava);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void initAtualizar() {
		UserPosDAO dao = new UserPosDAO();
		try {
			UserPosJava userposjava = dao.buscar(2);
			System.out.println(userposjava);
			userposjava.setNome("nome alterado");
			dao.atualizar(userposjava);
			dao.buscar(2);
			System.out.println(userposjava);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void initDeletar() {
		UserPosDAO dao = new UserPosDAO();
		try {
			dao.deletar(5L);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initListarFoneUser() {
		UserPosDAO dao = new UserPosDAO();
		try {
			List<BeanUserfone> list = dao.listaUserFone(12L);
			for (BeanUserfone beanuserfone : list) {
				System.out.println(beanuserfone);
				System.out.println("---------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
