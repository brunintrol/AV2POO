package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import model.Carro;

public class CarroDao {
	
	public void create (Carro carro) {
		Connection connect = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		String sql = "insert into carro values (?, ?, ?)";
		String idmodelo = "select modelo.id as id from modelo,marca where modelo.nome = ? and marca.nome = ?";
		
		try {
			
			stmt = connect.prepareStatement(idmodelo);
			stmt.setString(1, carro.getModelo());
			stmt.setString(2, carro.getMarca());

			rs = stmt.executeQuery();
			String id="";
			while (rs.next()) {
			id = rs.getString("id");
			}
			stmt2 = connect.prepareStatement(sql);  //instancia uma instrucao sql
			stmt2.setString(1, carro.getId()); //primeiro parametro da query
			stmt2.setString(2, carro.getPlaca()); //segundo parametro
			stmt2.setString(3, id);
			
			stmt2.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			ConnectionFactory.closeConnection(connect, stmt);
		}	
	}
	
	public List<Carro> readAll () {
		Connection connect = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT carro.id as id, carro.placa as placa, modelo.nome as modelo, marca.nome as marca FROM carro, modelo, marca WHERE carro.idmodelo = modelo.id and modelo.idmarca = marca.id";
		List<Carro> carros = new ArrayList<Carro>();
		
		try{
			stmt = connect.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Carro carro = new Carro();
				
				carro.setId(rs.getString("id"));
				carro.setPlaca(rs.getString("placa"));
				carro.setModelo(rs.getString("modelo"));
				carro.setMarca(rs.getString("marca"));
				carros.add(carro);

			}

		} catch(SQLException e) {
			System.out.println("Erro ao tentar ler tabela aluno");

		}

		finally {
			ConnectionFactory.closeConnection(connect, stmt);
		
		}
		
		return carros;
	
	}

	// Update
	public void update (Carro carro, String placaantiga) {
		Connection connect = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		String sql = "update carro set placa = ? where placa = ?";
		
		try {
			stmt = connect.prepareStatement(sql);  //instancia uma instrucao sql
			stmt.setString(1, carro.getPlaca()); //primeiro parametro da query
			stmt.setString(2, placaantiga); //segundo parametro
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			ConnectionFactory.closeConnection(connect, stmt);
		}	
	}

	// Delete
	public void delete (Carro carro) {
		Connection connect = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		String sql = "delete from carro where placa = ?";
		
		try {
			stmt = connect.prepareStatement(sql);  //instancia uma instrucao sql
			stmt.setString(1, carro.getPlaca()); //primeiro parametro
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			ConnectionFactory.closeConnection(connect, stmt);
		}	
	}
	
	
	
	
	

}