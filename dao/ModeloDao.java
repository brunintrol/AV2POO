package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import model.Modelo;

public class ModeloDao {
	
	
	public void create (Modelo modelo, String SelectedModelo) {
		Connection connect = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		String sql = "insert into modelo values (?, ?, ?)";
		String idmodelo = "select id from marca where nome = ?";
		
		try {
			
			stmt = connect.prepareStatement(idmodelo);
			stmt.setString(1, SelectedModelo);

			rs = stmt.executeQuery();
			String id="";
			while (rs.next()) {
			id = rs.getString("id");
			}
			stmt2 = connect.prepareStatement(sql);  //instancia uma instrucao sql
			stmt2.setString(1, modelo.getId()); //primeiro parametro da query
			stmt2.setString(2, modelo.getNome()); //segundo parametro
			stmt2.setString(3, id);
			
			stmt2.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			ConnectionFactory.closeConnection(connect, stmt);
		}	
	}
	
	
	public List<Modelo> readModelo (String idmarca) {
		Connection connect = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "select modelo.nome as nome, modelo.id as id, modelo.idmarca as idmarca from modelo, marca where marca.id=modelo.idmarca and marca.nome = ?";
		List<Modelo> modelos = new ArrayList<Modelo>();
		
		try{
			stmt = connect.prepareStatement(sql);
			stmt.setString(1, idmarca);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Modelo modelo = new Modelo();
				
				modelo.setId(rs.getString("id"));
				modelo.setNome(rs.getString("nome"));
				modelos.add(modelo);

			}

		} catch(SQLException e) {
			System.out.println("Erro ao tentar ler tabela aluno");

		}

		finally {
			ConnectionFactory.closeConnection(connect, stmt);
		
		}
		
		return modelos;
	
	}
	
	public List<Modelo> readModeloAll () {
		Connection connect = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "Select * from modelo";
		List<Modelo> modelos = new ArrayList<Modelo>();
		
		try{
			stmt = connect.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Modelo modelo = new Modelo();
				
				modelo.setId(rs.getString("id"));
				modelo.setNome(rs.getString("nome"));
				modelos.add(modelo);

			}

		} catch(SQLException e) {
			System.out.println("Erro ao tentar ler tabela modelo");

		}

		finally {
			ConnectionFactory.closeConnection(connect, stmt);
		
		}
		
		return modelos;

}
	
	public void update (Modelo modelo, String modeloantigo) {
		Connection connect = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		String sql = "update modelo set nome = ? where nome = ?";
		
		try {
			stmt = connect.prepareStatement(sql);  //instancia uma instrucao sql
			stmt.setString(1, modelo.getNome()); //primeiro parametro da query
			stmt.setString(2, modeloantigo); //segundo parametro
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			ConnectionFactory.closeConnection(connect, stmt);
		}	
	}
	
	public void delete (Modelo modelo) {
		Connection connect = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		String sql = "delete from modelo where nome = ?";
		
		try {
			stmt = connect.prepareStatement(sql);  //instancia uma instrucao sql
			stmt.setString(1, modelo.getNome()); //primeiro parametro
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			ConnectionFactory.closeConnection(connect, stmt);
		}	
	}
	
	
	
}
