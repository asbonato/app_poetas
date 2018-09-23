package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import to.ClienteTO;
import factory.ConnectionFactory;

public class ClienteDAO {
	
	public void incluir(ClienteTO to) throws IOException {
		String sqlInsert = "INSERT INTO cliente(nome, fone, email) VALUES (?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, to.getNome());
			stm.setString(2, to.getFone());
			stm.setString(3, to.getEmail());
			stm.execute();
			String sqlSelect = "SELECT LAST_INSERT_ID()";
			try(PreparedStatement stm1 = conn.prepareStatement(sqlSelect);
					ResultSet rs = stm1.executeQuery();){
					if(rs.next()){
						to.setId(rs.getInt(1));
					}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	}

	public void atualizar(ClienteTO to) throws IOException {
		String sqlUpdate = "UPDATE cliente SET nome=?, fone=?, email=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, to.getNome());
			stm.setString(2, to.getFone());
			stm.setString(3, to.getEmail());
			stm.setInt(4, to.getId());
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	}

	public void excluir(ClienteTO to) throws IOException {
		String sqlDelete = "DELETE FROM cliente WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, to.getId());
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	}

	public ClienteTO carregar(int id) throws IOException {
		ClienteTO to = new ClienteTO();
		to.setId(id);
		String sqlSelect = "SELECT nome, fone, email FROM cliente WHERE cliente.id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, id);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					to.setNome(rs.getString("nome"));
					to.setFone(rs.getString("fone"));
					to.setEmail(rs.getString("email"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
			throw new IOException(e1);
		}
		return to;
	}
	
	public ArrayList<ClienteTO> listarClientes() throws IOException {
		ClienteTO to;
		ArrayList<ClienteTO> lista = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, fone, email FROM cliente order by nome";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				while(rs.next()) {
					to = new ClienteTO();
					to.setId(rs.getInt("id"));
					to.setNome(rs.getString("nome"));
					to.setFone(rs.getString("fone"));
					to.setEmail(rs.getString("email"));
					lista.add(to);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
			throw new IOException(e1);
		}
		return lista;
	}
	
	public ArrayList<ClienteTO> listarClientes(String chave) throws IOException {
		ClienteTO to;
		ArrayList<ClienteTO> lista = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, fone, email FROM cliente where upper(nome) like ? order by nome";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				stm.setString(1, "%" + chave.toUpperCase() + "%");
			try (ResultSet rs = stm.executeQuery();) {
				while(rs.next()) {
					to = new ClienteTO();
					to.setId(rs.getInt("id"));
					to.setNome(rs.getString("nome"));
					to.setFone(rs.getString("fone"));
					to.setEmail(rs.getString("email"));
					lista.add(to);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
			throw new IOException(e1);
		}
		return lista;
	}

}
