package br.com.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-05-25T10:34:40.916-0300")
@StaticMetamodel(Cidades.class)
public class Cidades_ {
	public static volatile SingularAttribute<Cidades, Long> id;
	public static volatile SingularAttribute<Cidades, String> nome;
	public static volatile SingularAttribute<Cidades, Estados> estados;
}
