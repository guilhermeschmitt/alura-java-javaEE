package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.loja.models.Autor;

public class AutorDao {

	@PersistenceContext
	EntityManager em;
	
	public List<Autor> getLista() {
		return em.createQuery("Select a from Autor a", Autor.class).getResultList();
	}

}
