package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cliente;
import to.ClienteTO;

public class AlterarCliente implements Command {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pId = request.getParameter("id");
		String pNome = request.getParameter("nome");
		String pFone = request.getParameter("fone");
		String pEmail = request.getParameter("email");
		int id = -1;
		try {
			id = Integer.parseInt(pId);
		} catch (Exception e) {

		}

		Cliente cliente = new Cliente(id, pNome, pFone, pEmail);
		RequestDispatcher view = null;
		HttpSession session = request.getSession();

		cliente.atualizar();
		@SuppressWarnings("unchecked")
		ArrayList<ClienteTO> lista = (ArrayList<ClienteTO>) session
				.getAttribute("lista");
		int pos = busca(cliente, lista);
		lista.remove(pos);
		lista.add(pos, cliente.getTO());
		session.setAttribute("lista", lista);
		request.setAttribute("cliente", cliente.getTO());
		view = request.getRequestDispatcher("VisualizarCliente.jsp");
		view.forward(request, response);
	}

	public int busca(Cliente cliente, ArrayList<ClienteTO> lista) {
		ClienteTO to;
		for (int i = 0; i < lista.size(); i++) {
			to = lista.get(i);
			if (to.getId() == cliente.getId()) {
				return i;
			}
		}
		return -1;
	}

}
