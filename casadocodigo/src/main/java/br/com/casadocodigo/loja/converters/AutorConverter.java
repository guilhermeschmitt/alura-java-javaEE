package br.com.casadocodigo.loja.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.casadocodigo.loja.models.Autor;

@FacesConverter("autorConverter")
public class AutorConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent componente, String autorId) {
		if(autorId == null || autorId.trim().isEmpty()) return null;
		
		Autor autor = new Autor();
		autor.setId(Integer.valueOf(autorId));
		
		return autor;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent componente, Object autorObject) {
		if(autorObject == null) return null;
		
		Autor autor = (Autor) autorObject;
		return autor.getId().toString();
	}

}
