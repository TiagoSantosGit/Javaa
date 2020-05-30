package br.com.converter;

import java.io.Serializable;

import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.entidades.Estados;

@FacesConverter(forClass = Estados.class, value = "estadoConverter" )
public class EstadoConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;
	// Retorna o objeto inteiro
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String codigoEstado) {
		/*
		 * FacesContext -> contexto do JSF UIComponent -> o componente JSF usado String
		 * -> código do objeto
		 */
		EntityManager entityManager = CDI.current().select(EntityManager.class).get();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Estados estados = (Estados) entityManager.find(Estados.class, Long.parseLong(codigoEstado));
		entityTransaction.commit();
		return estados;
	}

	// Retorna apenas o código em String
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object estado) {
		if (estado == null) { 
			return null;
		}
		if (estado instanceof Estados) {
			return ((Estados) estado).getId().toString();
		} else {
			return estado.toString();
		}
	}
}
