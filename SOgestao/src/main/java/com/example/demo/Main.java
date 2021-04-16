package com.example.demo;

import dao.EnderecoDAO;
import dao.PessoaDAO;
import model.Endereco;
import model.Pessoa;
import model.Usuario;

public class Main {

	public static void main(String[] args) {
		/*
		 * Pessoa p = new Pessoa(); p.setCPF("1234567890"); PessoaDAO dao = new
		 * PessoaDAO(); EnderecoDAO endao = new EnderecoDAO(); Endereco end = new
		 * Endereco(); end.setNumero("522"); endao.Save(end);
		 * 
		 * p.setEndereço(end); dao.Save(p);
		 */
		Usuario user = new Usuario();
		user.setCPF("456");
		user.setCargo("adm");
		PessoaDAO dao = new PessoaDAO();
		dao.Save(user);

	}

}
