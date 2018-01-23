package br.com.casadocodigo.loja.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.loja.dao.LivroDao;
import br.com.casadocodigo.loja.models.Livro;

@Model //Visivel apenas por 1 request
public class HomeBean {

	@Inject
	LivroDao livroDAO;
	
	public List<Livro> ultimosLancamentos(){
		System.out.println("Entrou nos ultimos lançamentos");
		return livroDAO.ultimosLancamentos();
		
	}
	
	public List<Livro> demaisLivros(){
		System.out.println("Entrou nos demais livros");
		return livroDAO.demaisLancamentos();
		
	}
	
}
