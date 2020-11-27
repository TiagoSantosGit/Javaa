package br.com.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-10-20T19:54:10.665-0300")
@StaticMetamodel(Pessoa.class)
public class Pessoa_ {
	public static volatile SingularAttribute<Pessoa, Long> id;
	public static volatile SingularAttribute<Pessoa, String> nome;
	public static volatile SingularAttribute<Pessoa, String> sobrenome;
	public static volatile SingularAttribute<Pessoa, String> sexo;
	public static volatile SingularAttribute<Pessoa, String> login;
	public static volatile SingularAttribute<Pessoa, String> senha;
	public static volatile SingularAttribute<Pessoa, String> perfilUser;
	public static volatile SingularAttribute<Pessoa, String> nivelProgramador;
	public static volatile SingularAttribute<Pessoa, String> cep;
	public static volatile SingularAttribute<Pessoa, String> logradouro;
	public static volatile SingularAttribute<Pessoa, String> complemento;
	public static volatile SingularAttribute<Pessoa, String> bairro;
	public static volatile SingularAttribute<Pessoa, String> localidade;
	public static volatile SingularAttribute<Pessoa, String> uf;
	public static volatile SingularAttribute<Pessoa, String> unidade;
	public static volatile SingularAttribute<Pessoa, String> ibge;
	public static volatile SingularAttribute<Pessoa, String> gia;
	public static volatile SingularAttribute<Pessoa, Cidades> cidades;
	public static volatile SingularAttribute<Pessoa, String> fotoIconBase64;
	public static volatile SingularAttribute<Pessoa, String> extencao;
	public static volatile SingularAttribute<Pessoa, byte[]> fotoIconBase64Original;
}
