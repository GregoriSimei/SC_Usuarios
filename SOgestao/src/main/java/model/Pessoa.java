package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


import dao.EntidadeBase;

@Entity
public class Pessoa implements EntidadeBase{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	public String CPF;
	private String Nome;
	private String Celular;
	private String DataNascimento;
	@OneToOne(fetch = FetchType.LAZY)
	private Endereco Endereco;
	@OneToOne(fetch = FetchType.LAZY)
	private Usuario Usuario;
	
	public Endereco getEndereco() {
		return Endereco;
	}
	public void setEndereco(Endereco endereco) {
		Endereco = endereco;
	}
	public Usuario getUsuario() {
		return Usuario;
	}
	public void setUsuario(Usuario usuario) {
		Usuario = usuario;
	}
	public Pessoa() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public Serializable getId() {
		return id;
	}
	public int getIdInt() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getCelular() {
		return Celular;
	}
	public void setCelular(String celular) {
		Celular = celular;
	}
	public String getDataNascimento() {
		return DataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		DataNascimento = dataNascimento;
	}
	
	

}
