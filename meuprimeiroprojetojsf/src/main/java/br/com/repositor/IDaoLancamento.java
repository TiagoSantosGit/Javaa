package br.com.repositor;

import java.util.List;

import br.com.entidades.Lancamento;

public interface IDaoLancamento {
	
	List<Lancamento> consultar(Long codUser);

}
