package br.com.cursojsf;

import javax.persistence.Persistence;

public class TesteJPA {

	public static void main(String[] args) {
		// sempre uma instância para todos os usuários
		Persistence.createEntityManagerFactory("meuprimeiroprojetojsf");
	}
	
}
