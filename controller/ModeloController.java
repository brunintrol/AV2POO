package controller;

import java.util.ArrayList;
import java.util.List;

import dao.ModeloDao;
import model.Modelo;

public class ModeloController {
	
	public void create (Modelo modelo, String SelectedModelo) {
		try {
			ModeloDao dao = new ModeloDao();
			
			dao.create(modelo, SelectedModelo);
		
		} catch (Exception e) {
			System.out.println("Erro no Controller");
		
		}

		finally {
			System.out.println("Fim inserção");
		
		}
	
	}
	
	public List<Modelo> readModelo(String idmarca) {
		List<Modelo> modelos = new ArrayList<Modelo>();
		
		try{
			ModeloDao dao = new ModeloDao();
			modelos = dao.readModelo(idmarca);		
		}
		
		finally {
			System.out.println("Fim da leitura!");
		}
		
		return modelos;
	
	}
	
	
	public List<Modelo> readModeloAll() {
		List<Modelo> modelos = new ArrayList<Modelo>();
		
		try{
			ModeloDao dao = new ModeloDao();
			modelos = dao.readModeloAll();		
		}
		
		finally {
			System.out.println("Fim da leitura!");
		}
		
		return modelos;
	
	}
	
	public void update (Modelo modelo,String modeloantigo) {
		try {
			ModeloDao dao = new ModeloDao();
			
			dao.update(modelo,modeloantigo);
		
		} catch (Exception e) {
			System.out.println("Erro no Controller");
		
		}

		finally {
			System.out.println("Fim da atualização");
		
		}
	
	}
	
	
	public void delete (Modelo modelo) {
		try {
			ModeloDao dao = new ModeloDao();
			
			dao.delete(modelo);
		
		} catch (Exception e) {
			System.out.println("Erro no Controller");
		
		}

		finally {
			System.out.println("Fim excluído");
		
		}
	
	}

}
