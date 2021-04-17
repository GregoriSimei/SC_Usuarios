package controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import business.UsuarioBusiness;
import dao.EnderecoDAO;
import dao.GenericDAO;
import dao.PessoaDAO;
import dao.UsuarioDAO;
import model.Pessoa;
import model.Usuario;
@RestController
public class ControllerUsuario {
	PessoaDAO dao = new PessoaDAO();
	EnderecoDAO enddao = new EnderecoDAO();
	UsuarioBusiness userbus = new UsuarioBusiness();
	
	
	

	@RequestMapping("/cadastraruser")
	public Pessoa salvaUsuario(@RequestBody String Pessoa) {
		Gson gson = new Gson();
		Pessoa p = null;
		p = gson.fromJson(Pessoa, Pessoa.class);
		userbus.Validacao(p.getUsuario());	// Validacao = deixa ativo como false e cria a request
		enddao.Save(p.getEndereco());
		dao.Save(p);
		
		//System.out.println(p.getCPF());
		return p;
		
		
	}
	

}
