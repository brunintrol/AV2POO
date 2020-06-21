package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import model.Marca;

public class MarcaDao {
	
	public void create (Marca marca) {
		Connection connect = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		String sql = "insert into marca values (?, ?)";
		
		try {
			
			stmt = connect.prepareStatement(sql);  //instancia uma instrucao sql
			stmt.setString(1, marca.getId()); //primeiro parametro da query
			stmt.setString(2, marca.getNome()); //segundo parametro
			
			stmt.executeUpdate();
			//System.out.println("[AlunoDAO] Aluno incluido com sucesso");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			ConnectionFactory.closeConnection(connect, stmt);
		}	
	}
	
	
	
	public List<Marca> readMarcaAll () {
		Connection connect = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "Select * from marca";
		List<Marca> marcas = new ArrayList<Marca>();
		
		try{
			stmt = connect.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Marca marca = new Marca();
				
				marca.setId(rs.getString("id"));
				marca.setNome(rs.getString("nome"));
				marcas.add(marca);

			}

		} catch(SQLException e) {
			System.out.println("Erro ao tentar ler tabela aluno");

		}

		finally {
			ConnectionFactory.closeConnection(connect, stmt);
		
		}
		
		return marcas;
	
	}
	
	public void update (Marca marca, String marcaantiga) {
		Connection connect = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		String sql = "update marca set nome = ? where nome = ?";
		
		try {
			stmt = connect.prepareStatement(sql);  //instancia uma instrucao sql
			stmt.setString(1, marca.getNome()); //primeiro parametro da query
			stmt.setString(2, marcaantiga); //segundo parametro
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			ConnectionFactory.closeConnection(connect, stmt);
		}	
	}
	
	
	public void delete (Marca marca) {
		Connection connect = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		String sql = "delete from marca where nome = ?";
		
		try {
			stmt = connect.prepareStatement(sql);  //instancia uma instrucao sql
			stmt.setString(1, marca.getNome()); //primeiro parametro
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			ConnectionFactory.closeConnection(connect, stmt);
		}	
	}
	
}
