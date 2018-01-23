package br.com.casadocodigo.loja.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.loja.dao.LivroDao;
import br.com.casadocodigo.loja.models.Livro;

@Model //É uma notação que já junta o Requested e o Named
public class AdminListaLivrosBean {

	@Inject
	LivroDao livroDAO;
	private List<Livro> livros = new ArrayList<>();
	
	public List<Livro> getLivros(){
		this.livros  = livroDAO.lista();
		return livros;
	}
	
}
