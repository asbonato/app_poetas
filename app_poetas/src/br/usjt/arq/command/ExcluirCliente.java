package br.usjt.arq.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.usjt.arq.model.entity.Cliente;
import br.usjt.arq.model.service.ClienteService;

public class ExcluirCliente implements Command {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pId = request.getParameter("id");
		int id = -1;
		try {
			id = Integer.parseInt(pId);
		} catch (Exception e) {

		}

		ClienteService service = new ClienteService();
		Cliente cliente = new Cliente();
		cliente.setId(id);

		RequestDispatcher view = null;
		HttpSession session = request.getSession();

		service.excluir(cliente);
		@SuppressWarnings("unchecked")
		ArrayList<Cliente> lista = (ArrayList<Cliente>) session
				.getAttribute("lista");
		int pos = busca(cliente, lista);
		if (pos >= 0){
			lista.remove(pos);
		}
		session.setAttribute("lista", lista);
		view = request.getRequestDispatcher("ListarClientes.jsp");
		view.forward(request, response);
	}

	public int busca(Cliente chave, ArrayList<Cliente> lista) {
		Cliente cliente;
		for (int i = 0; i < lista.size(); i++) {
			cliente = lista.get(i);
			if (chave.getId() == cliente.getId()) {
				return i;
			}
		}
		return -1;
	}

}
