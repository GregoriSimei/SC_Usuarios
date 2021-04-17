package business;

import model.Usuario;

public class UsuarioBusiness {
	public Usuario Validacao(Usuario user) {
		user.setAtivo(false);
		return user;
		
	}

}
