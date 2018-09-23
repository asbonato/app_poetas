package model;

import java.io.IOException;
import java.util.ArrayList;

import to.ClienteTO;
import dao.ClienteDAO;

public class Vendedor {
	public ArrayList<ClienteTO> listarClientes() throws IOException{
		ArrayList<ClienteTO> lista;
		ClienteDAO dao = new ClienteDAO();
		lista = dao.listarClientes();
		return lista;
	}
	public ArrayList<ClienteTO> listarClientes(String chave) throws IOException{
		ArrayList<ClienteTO> lista;
		ClienteDAO dao = new ClienteDAO();
		lista = dao.listarClientes(chave);
		return lista;
	}

}
