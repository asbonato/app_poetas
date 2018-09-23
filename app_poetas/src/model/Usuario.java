package model;

import java.io.IOException;

import to.UsuarioTO;
import dao.UsuarioDAO;

public class Usuario {
	private String username;
	private String password;
	
	public Usuario(){
		
	}
	public Usuario(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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
	
	public boolean validar() throws IOException{
		UsuarioTO to = getTO();
		UsuarioDAO dao = new UsuarioDAO();
		return dao.validar(to);
	}
	public UsuarioTO getTO() {
		UsuarioTO to = new UsuarioTO();
		to.setUsername(username);
		to.setPassword(password);
		return to;
	}
}
