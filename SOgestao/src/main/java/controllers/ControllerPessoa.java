package controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import dao.GenericDAO;
import dao.PessoaDAO;
import model.Pessoa;

@RestController
public class ControllerPessoa {
	GenericDAO dao = new GenericDAO();
	
	@RequestMapping("/cadastrarpessoa")
	public Pessoa salvaUsuario(@RequestBody String Pessoa) {
		Gson gson = new Gson();
		Pessoa p = null;
		p = gson.fromJson(Pessoa, Pessoa.class);
		System.out.println(p.getCPF()+p.getNome()+p.getCelular());
		dao.Save(p);
		//System.out.println(p.getCPF());
		return p;
		
		
	}

}
