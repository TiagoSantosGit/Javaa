package br.com.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-05-23T15:04:50.942-0300")
@StaticMetamodel(Lancamento.class)
public class Lancamento_ {
	public static volatile SingularAttribute<Lancamento, Long> id;
	public static volatile SingularAttribute<Lancamento, String> numeroNotaFiscal;
	public static volatile SingularAttribute<Lancamento, String> empresaOrigem;
	public static volatile SingularAttribute<Lancamento, String> empresaDestino;
	public static volatile SingularAttribute<Lancamento, Pessoa> usuario;
}
