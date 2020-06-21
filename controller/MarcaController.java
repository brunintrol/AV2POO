package controller;

import java.util.ArrayList;
import java.util.List;

import dao.MarcaDao;
import model.Marca;

public class MarcaController {
	
	public void create (Marca marca) {
		try {
			MarcaDao dao = new MarcaDao();
			
			dao.create(marca);
		
		} catch (Exception e) {
			System.out.println("Erro no Controller");
		
		}

		finally {
			System.out.println("Fim inserção");
		
		}
	
	}

	public List<Marca> readMarcaAll() {
		List<Marca> marcas = new ArrayList<Marca>();
		
		try{
			MarcaDao dao = new MarcaDao();
			marcas = dao.readMarcaAll();		
		}
		
		finally {
			System.out.println("Fim da leitura!");
		}
		
		return marcas;
	
	}
	
	public void update (Marca marca,String marcaantiga) {
		try {
			MarcaDao dao = new MarcaDao();
			
			dao.update(marca,marcaantiga);
		
		} catch (Exception e) {
			System.out.println("Erro no Controller");
		
		}

		finally {
			System.out.println("Fim da atualização");
		
		}
	
	}
	
	
	public void delete (Marca marca) {
		try {
			MarcaDao dao = new MarcaDao();
			
			dao.delete(marca);
		
		} catch (Exception e) {
			System.out.println("Erro no Controller");
		
		}

		finally {
			System.out.println("Fim excluído");
		
		}
	
	}
	
	
}
