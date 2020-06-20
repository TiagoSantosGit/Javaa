package DAO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import Model.UsuarioPessoa;

public class DaoUsuario<E> extends DaoGeneric<UsuarioPessoa> implements Serializable {

	private static final long serialVersionUID = 1L;

	public void removerUsuario(UsuarioPessoa pessoa) throws Exception {

		String sqlDeleteFone = "delete from telefoneuser where usuariopessoa_id = " + pessoa.getId();
		String sqlDeleteEmail = "delete from emailuser where usuariopessoa_id = " + pessoa.getId();
		getEntityManager().getTransaction().begin();
		getEntityManager().createNativeQuery(sqlDeleteFone).executeUpdate();
		getEntityManager().createNativeQuery(sqlDeleteEmail).executeUpdate();
		getEntityManager().getTransaction().commit();
		super.deletarPoID(pessoa);
	}

	@SuppressWarnings("unchecked")
	public List<UsuarioPessoa> pesquisar(String campoPesquisa) {
		Query query = super.getEntityManager()
				.createQuery("from UsuarioPessoa where nome like '%" + campoPesquisa + "%'");
		return query.getResultList();
	}

}
