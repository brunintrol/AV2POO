package controller;

import java.util.ArrayList;
import java.util.List;

import dao.CarroDao;
import model.Carro;


public class CarroController {
	
	//regra para incluir um aluno
	public void create (Carro carro) {
		try {
			CarroDao dao = new CarroDao();
			
			dao.create(carro);
		
		} catch (Exception e) {
			System.out.println("Erro no Controller");
		
		}

		finally {
			System.out.println("Fim inserção");
		
		}
	
	}
	
	public List<Carro> readAll () {
		List<Carro> carros = new ArrayList<Carro>();
		
		try{
			CarroDao dao = new CarroDao();
			carros = dao.readAll();		
		}
		
		finally {
			System.out.println("Fim da leitura!");
		}
		
		return carros;
	
	}

	public void update (Carro carro,String placaantiga) {
		try {
			CarroDao dao = new CarroDao();
			
			dao.update(carro,placaantiga);
		
		} catch (Exception e) {
			System.out.println("Erro no Controller");
		
		}

		finally {
			System.out.println("Fim da atualização");
		
		}
	
	}

	public void delete (Carro carro) {
		try {
			CarroDao dao = new CarroDao();
			
			dao.delete(carro);
		
		} catch (Exception e) {
			System.out.println("Erro no Controller");
		
		}

		finally {
			System.out.println("Fim excluído");
		
		}
	
	}
	
}
