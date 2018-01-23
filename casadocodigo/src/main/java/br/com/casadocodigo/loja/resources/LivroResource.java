package br.com.casadocodigo.loja.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import br.com.casadocodigo.loja.dao.LivroDao;
import br.com.casadocodigo.loja.models.Livro;

@Path("livros")
public class LivroResource {

    @Inject
    private LivroDao dao;

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("lancamentos")
    @Wrapped(element="livros")
    public List<Livro> ultimosLancamentos() {
        return dao.ultimosLancamentos();

        /*
         * A anotação @GET, indica qual método HTTP deve ser utilizado para acessar a
         * requisição. A anotação @Path, é onde podemos passar o caminho para acessar o
         * recurso, por exemplo @Path("json"). Já a @Produces, indica qual formato será
         * disponibilizado. Essa anotação recebe um array com os formatos que desejamos
         * disponibilizar. No caso dessa alternativa, iremos disponibilizar os dados no
         * formato JSON.
         * 
         * Essa requisição ficará disponível em
         * http://localhost:8080/casadocodigo/services/livros/lancamentos .O "/services",
         * vem da configuração do JaxRs, feita na classe JaxRsConfiguration no pacote
         * config
         */
    
    }

}
