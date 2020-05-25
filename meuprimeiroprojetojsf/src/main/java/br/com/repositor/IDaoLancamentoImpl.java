package br.com.repositor;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.JPAUtil.JPAUtil;
import br.com.entidades.Lancamento;

public class IDaoLancamentoImpl implements IDaoLancamento {

	@SuppressWarnings("unchecked")
	@Override
	public List<Lancamento> consultar(Long codUser) {
		List<Lancamento> lista;

		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		lista = entityManager.createQuery(" from Lancamento where usuario.id = " + codUser).getResultList();
		return lista;
	}

}
