package controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	PessoaDAO dao;
	UsuarioDAO userdao;
	EnderecoDAO enddao;
	UsuarioBusiness ubus;
	

	@RequestMapping("/cadastraruser")
	public Pessoa salvaUsuario(@RequestBody String Pessoa) {
		Gson gson = new Gson();
		Pessoa p = null;
		p = gson.fromJson(Pessoa, Pessoa.class);
		userdao.Save(ubus.Validacao(p.getUsuario()));
		enddao.Save(p.getEndereco());
		dao.Save(p);
		
		//System.out.println(p.getCPF());
		return p;
		
		
	}
	

}
