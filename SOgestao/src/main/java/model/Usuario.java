package model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Usuarios")
public class Usuario extends Pessoa{
	private String cargo;
	private String username;
	private String password;
	private Boolean ativo;
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	

}
