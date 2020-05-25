package br.com.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.JPAUtil.JPAUtil;
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
		EntityManager entityManeger = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManeger.getTransaction();
		entityTransaction.begin();
		Cidades Cidades = (Cidades) entityManeger.find(Cidades.class, Long.parseLong(codigoCidade));
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
