package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.jpa.QueryHints;

import br.com.casadocodigo.loja.models.Livro;

/* @Stateful
Com stateful, setando o type e fazendo o find, não precisaríamos usar a query que criamos, porém, teríamos um select a mais.
Perdendo desempenho. */

@Stateful
public class LivroDao {

	
	/* PersistenceContext Faz a injeção do EM no nosso DAO*/
	 @PersistenceContext(type=PersistenceContextType.EXTENDED)
	//@PersistenceContext 
	private EntityManager em;

	public void salvar(Livro livro) {
		em.persist(livro);
	}

	public List<Livro> lista() {
		String jpql = "Select distinct(l) from Livro l join fetch l.autores";
		return em.createQuery(jpql, Livro.class).getResultList();
	}

	public List<Livro> ultimosLancamentos() {
		String jpql = "Select l from Livro l order by l.id desc";
		return em.createQuery(jpql, Livro.class)
				.setMaxResults(5)
				.setHint(QueryHints.HINT_CACHEABLE, true)
				.getResultList();
	}

	public List<Livro> demaisLancamentos() {
		String jpql = "Select l from Livro l order by l.id desc";
		return em.createQuery(jpql, Livro.class)
				.setFirstResult(5)
				.setHint(QueryHints.HINT_CACHEABLE, true)
				.getResultList();

	}

	public Livro buscaPorId(Integer id) {
		//em.find(Livro.class, id)
		String jpql = "select l from Livro l join fetch l.autores where l.id = :id";
		return em.createQuery(jpql, Livro.class).setParameter("id", id).getSingleResult();
	}

}
