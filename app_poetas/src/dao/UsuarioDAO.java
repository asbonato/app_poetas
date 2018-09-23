package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import to.UsuarioTO;
import factory.ConnectionFactory;

public class UsuarioDAO {
	public boolean validar(UsuarioTO to) throws IOException {
		String sqlSelect = "SELECT username, password FROM usuario "+
				"WHERE username = ? and password = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, to.getUsername());
			stm.setString(2, to.getPassword());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
			throw new IOException(e1);
		}
	}

}
