package business;

import model.Usuario;
import dao.GenericDAO;
import dao.UsuarioDAO;
import model.Request;
public class UsuarioBusiness {
	
	UsuarioDAO userdao = new UsuarioDAO();
	GenericDAO dao = new GenericDAO();
	public Usuario Validacao(Usuario user) {
		Request req = new Request();
		user.setAtivo(false);
		req.setUsuario(user);
		req.setStatus("pendente");
		userdao.Save(user);
		dao.Save(req);
		
		return user;
		
	}

}
