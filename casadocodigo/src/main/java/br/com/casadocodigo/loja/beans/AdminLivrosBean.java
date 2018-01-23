package br.com.casadocodigo.loja.beans;

import java.io.IOException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.dao.AutorDao;
import br.com.casadocodigo.loja.dao.LivroDao;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Autor;
import br.com.casadocodigo.loja.models.Livro;

@Named
@RequestScoped
public class AdminLivrosBean {

	Livro livro = new Livro();

	@Inject
	LivroDao livroDAO;
	@Inject
	AutorDao autorDAO;
	@Inject
	FacesContext context;
	
	private Part capaDoLivro;

	@Transactional
	public String salvar() throws IOException {
	
		livroDAO.salvar(livro);
		FileSaver file = new FileSaver();
		livro.setCapaPath(file.write(capaDoLivro, "livros"));
		
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Livro cadastrado com sucesso!"));

		return "/livros/lista?faces-redirect=true";
		
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Autor> getAutores() {
		return autorDAO.getLista();
	}

	public Part getCapaDoLivro() {
		return capaDoLivro;
	}

	public void setCapaDoLivro(Part capaDoLivro) {
		this.capaDoLivro = capaDoLivro;
	}
	
	


}
