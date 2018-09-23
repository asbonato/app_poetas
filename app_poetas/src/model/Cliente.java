package model;

import java.io.IOException;

import to.ClienteTO;
import dao.ClienteDAO;

public class Cliente {
	private int id;
	private String nome;
	private String fone;
	private String email;

	public Cliente(int id, String nome, String fone, String email) {
		this.id = id;
		this.nome = nome;
		this.fone = fone;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFoto(){
		String foto = this.email.replace('@', '_');
		return foto.replace('.', '_');
	}

	public void criar() throws IOException {
		ClienteDAO dao = new ClienteDAO();
		ClienteTO to = getTO();
		dao.incluir(to);
		//como o campo id da tabela cliente é auto_increment, um novo id será criado 
		this.id = to.getId();
	}

	public ClienteTO getTO() {
		ClienteTO to = new ClienteTO();
		to.setId(id);
		to.setNome(nome);
		to.setFone(fone);
		to.setEmail(email);
		return to;
	}

	public void atualizar() throws IOException {
		ClienteDAO dao = new ClienteDAO();
		ClienteTO to = getTO();
		dao.atualizar(to);
	}

	public void excluir() throws IOException {
		ClienteDAO dao = new ClienteDAO();
		ClienteTO to = new ClienteTO();
		to.setId(id);
		dao.excluir(to);
		setId(-1);
		setNome(null);
		setFone(null);
		setEmail(null);
	}

	public void carregar() throws IOException {
		ClienteDAO dao = new ClienteDAO();
		ClienteTO to = dao.carregar(id);
		nome = to.getNome();
		fone = to.getFone();
		email = to.getEmail();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fone == null) {
			if (other.fone != null)
				return false;
		} else if (!fone.equals(other.fone))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
