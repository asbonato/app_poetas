package br.usjt.arq.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.arq.model.entity.Cliente;
import br.usjt.arq.model.service.ClienteService;

public class EditarCliente implements Command {

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
		RequestDispatcher view = null;

		Cliente cliente = service.carregar(id);
		request.setAttribute("cliente", cliente);
		view = request.getRequestDispatcher("AlterarCliente.jsp");

		view.forward(request, response);

	}
}
