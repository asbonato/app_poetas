package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import to.ClienteTO;
import dao.ClienteDAO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteDAOTest {
	static ClienteDAO dao;
	static ClienteTO to;
	
	/*
	 * Antes de rodar este teste, certifique-se que nao ha no banco
	 * nenhuma linha com o id igual ao do escolhido para o to instanciado
	 * abaixo. Se houver, delete. 
	 * Certifique-se de que o fixture cliente 1 existe no banco.
	 * Certifique-se também que sobrecarregou o equals na classe ClienteTO
	 * Além disso, a ordem de execução dos testes é importante; por isso a anotação
	 * FixMethodOrder logo acima do nome desta classe
	 */
	@BeforeClass
	public static void setUp() throws Exception {
		dao = new ClienteDAO();
		to = new ClienteTO();
		to.setFone("123456789");
		to.setNome("Bela Lugosi");
		to.setEmail("bela@bauhaus.net");
		to.setId(-1);
	}
	
	@Test
	public void test00Carregar() {
		//para funcionar o cliente 1 deve ter sido carregado no banco por fora
		//delete from cliente;
		//insert into cliente (nome, fone, email) values ('nome1', 'fone1', 'email1');
		ClienteTO fixture = new ClienteTO();
		fixture.setFone("fone1");
		fixture.setNome("nome1");
		fixture.setEmail("email1");
		fixture.setId(1);
		ClienteTO novo = null;
		try {
			novo = dao.carregar(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("testa inclusao", novo, fixture);
	}

	@Test
	public void test01Inserir() {
		ClienteTO novo = null;
		try {
			dao.incluir(to);
			novo = dao.carregar(to.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("testa inclusao", novo, to);
	}
	
	@Test
	public void test02Atualizar() {
		to.setFone("999999");
		ClienteTO novo = null;
		try {
			dao.atualizar(to);
			novo = dao.carregar(to.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("testa inclusao", novo, to);
	}
	
	@Test
	public void test03Excluir() {
		to.setNome(null);
		to.setFone(null);
		to.setEmail(null);
		ClienteTO novo = null;
		try {
			dao.excluir(to);
			novo = dao.carregar(to.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("testa inclusao", novo, to);
	}
}
