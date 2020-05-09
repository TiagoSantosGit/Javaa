package posjavamavenhibernate;

import java.util.List;

import org.junit.Test;

import DAO.DaoGeneric;
import Model.TelefoneUser;
import Model.UsuarioPessoa;

public class TesteHibernate {

	@Test
	public void testeHibernateUtil() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setEmail("tiagosantospnz@Hotmail.com");
		pessoa.setLogin("admin");
		pessoa.setSenha("admin");
		pessoa.setSobrenome("santos");
		pessoa.setNome("tiago");
		pessoa.setIdade(32L);
		daoGeneric.salvar(pessoa);
	}

	@Test
	public void testeBuscar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(2L);
		pessoa = daoGeneric.pesquisar(pessoa);
		// pessoa = daoGeneric.pesquisar(pessoa);
		System.out.println("Consulta: " + pessoa);
	}

	@Test
	public void testeBuscar2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa = daoGeneric.pesquisar2(3L, UsuarioPessoa.class);
		System.out.println("Consulta: 2 " + pessoa);
	}

	@Test
	public void testeUpdate() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = daoGeneric.pesquisar2(4L, UsuarioPessoa.class);
		pessoa.setLogin("Show");
		pessoa.setSenha("Show");
		pessoa.setNome("thiago");
		pessoa = daoGeneric.updateMerge(pessoa);
		System.out.println("UpdadeMerge: " + pessoa);
	}

	@Test
	public void testeDelete() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = daoGeneric.pesquisar2(5L, UsuarioPessoa.class);
		daoGeneric.deletarPoID(pessoa);
		System.out.println("Deleted: " + pessoa);
	}

	@Test
	public void testeConsultar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.listar(UsuarioPessoa.class);

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("--------------------------------------------");
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testeQueryList() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa where id = 10")
				.getResultList();
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("------- ---- ---------------- -------- -----");
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testeQueryListMaxResult() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa order by id")
				.setMaxResults(1).getResultList();
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("-***-------------------------- -------- -----");
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testeQueryListParameter() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager()
				.createQuery(" from UsuarioPessoa where nome = :nome and sobrenome = :sobrenome")
				.setParameter("nome", "tiago").setParameter("sobrenome", "santos").getResultList();
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println(">>>>-----------------------------------------");
		}
	}

	@Test
	public void testeQuerySomaIdade() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		Long somaIdade = (Long) daoGeneric.getEntityManager().createQuery("select sum(u.idade) from UsuarioPessoa u ")
				.getSingleResult();
		Double mediaIdade = (Double) daoGeneric.getEntityManager()
				.createQuery("select avg(u.idade) from UsuarioPessoa u ").getSingleResult();
		System.out.println("Idade total : " + somaIdade);
		System.out.println("Idade media : " + mediaIdade);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testeNameQuery1() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.todos")
				.getResultList();
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println(">??>-----------------------------------------");
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testeNameQuery2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.buscaPorNome")
				.setParameter("nome", "tiago").getResultList();
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println(">>:::-----------------------------------------");
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testeGravaTelefone() {
		DaoGeneric daoGeneric = new DaoGeneric();
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar2(20L, UsuarioPessoa.class);
		TelefoneUser telefoneUser = new TelefoneUser();
		telefoneUser.setTipo("Celular");
		telefoneUser.setNumero("1928747632");
		telefoneUser.setUsuarioPessoa(pessoa);
		daoGeneric.salvar(telefoneUser);
	}
}
