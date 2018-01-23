package br.com.casadocodigo.loja.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.loja.models.Compra;

public class CompraDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	public void salvar(Compra compra) {
		em.persist(compra);
	}

	public Compra buscaPorUuid(String uuid) {
		String jpql = "Select c from Compra c where c.uuid = :uuid";
		return em.createQuery(jpql, Compra.class).setParameter("uuid", uuid).getSingleResult();
	}

}
