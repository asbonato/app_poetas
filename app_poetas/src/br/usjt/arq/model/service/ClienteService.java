package br.usjt.arq.model.service;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.arq.model.dao.ClienteDAO;
import br.usjt.arq.model.entity.Cliente;

public class ClienteService {
	ClienteDAO dao;
	
	public ClienteService() {
		this.dao = new ClienteDAO();
	}

	public int criar(Cliente cliente) throws IOException {
		return dao.incluir(cliente);
	}

	public void atualizar(Cliente cliente) throws IOException {
		dao.atualizar(cliente);
	}

	public void excluir(Cliente cliente) throws IOException {
		dao.excluir(cliente);
	}

	public Cliente carregar(int id) throws IOException {
		return dao.carregar(id);
	}
	
	public ArrayList<Cliente> listarClientes() throws IOException{
		return dao.listarClientes();
	}
	public ArrayList<Cliente> listarClientes(String chave) throws IOException{
		return dao.listarClientes(chave);
	}

}
