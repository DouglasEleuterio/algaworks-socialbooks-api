package com.algaworks.socialbooks.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LivrosResources {

	@RequestMapping(value= "/livros", method = RequestMethod.GET)
	public String listar(){
		return "Rest Aplicado, Git passo-a-passo";
	}
	
}
