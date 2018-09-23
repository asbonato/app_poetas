package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;
import model.Vendedor;
import to.ClienteTO;
import util.JSonFacade;

@WebServlet("/cliente")
public class ServicoManterClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	/*
	 * configurar a request e a response para todos os métodos
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		super.service(request, response);
	}
	/*
	 * listar clientes
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String chave = request.getParameter("chave");
		Vendedor vendedor = new Vendedor();
		ArrayList<ClienteTO> lista = null;

		PrintWriter out = response.getWriter();

		try {
			if (chave != null && chave.length() > 0) {
				lista = vendedor.listarClientes(chave);
			} else {
				lista = vendedor.listarClientes();
			}
			out.println(JSonFacade.listToJSon(lista));
		} catch (Exception e) {
			e.printStackTrace();
			out.println(JSonFacade.errorToJSon(e));
		}
		
	}

	/*
	 * inclusão de clientes
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		StringBuilder sb = JSonFacade.montaJSon(request);
		PrintWriter out = response.getWriter();

		try {
			Cliente cliente = JSonFacade.jSonToCliente(sb.toString());
			cliente.criar();
			//retorna o cliente cadastrado com o id atribuido pelo banco
			out.println(JSonFacade.clienteToJSon(cliente));
		} catch (Exception e) {
			e.printStackTrace();
			out.println(JSonFacade.errorToJSon(e));
		}
	}
	/*
	 * atualiza clientes
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		StringBuilder sb = JSonFacade.montaJSon(request);
		PrintWriter out = response.getWriter();

		try {
			Cliente cliente = JSonFacade.jSonToCliente(sb.toString());
			cliente.atualizar();
			//retorna o cliente atualizado
			out.println(JSonFacade.clienteToJSon(cliente));
		} catch (Exception e) {
			e.printStackTrace();
			out.println(JSonFacade.errorToJSon(e));
		}
	}

	/*
	 * exclusão de clientes
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder sb = JSonFacade.montaJSon(request);
		PrintWriter out = response.getWriter();

		try {
			Cliente cliente = JSonFacade.jSonToCliente(sb.toString());
			cliente.excluir(); 
			//retorna dados null se o cliente foi deletado
			out.println(JSonFacade.clienteToJSon(cliente));
		} catch (Exception e) {
			e.printStackTrace();
			out.println(JSonFacade.errorToJSon(e));
		}
	}
	
}
