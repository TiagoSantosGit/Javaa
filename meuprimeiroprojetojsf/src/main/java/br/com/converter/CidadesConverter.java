package br.com.converter;

import java.io.Serializable;

import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.entidades.Cidades;

@FacesConverter(forClass = Cidades.class, value = "cidadeConverter")
public class CidadesConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;
	// Retorna o objeto inteiro
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String codigoCidade) {
		/*
		 * FacesContext -> contexto do JSF UIComponent -> o componente JSF usado String
		 * -> código do objeto
		 */
		EntityManager entityManager = CDI.current().select(EntityManager.class).get();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Cidades Cidades = (Cidades) entityManager.find(Cidades.class, Long.parseLong(codigoCidade));
		entityTransaction.commit();
		return Cidades;
	}

	// Retorna apenas o código em String
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object cidade) {
		if (cidade == null) {
			return null;
		}
		if (cidade instanceof Cidades) {
			return ((Cidades) cidade).getId().toString();
		} else {
			return cidade.toString();
		}
	}
}
