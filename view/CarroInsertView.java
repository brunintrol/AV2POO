package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.CarroController;
import controller.MarcaController;
import controller.ModeloController;
import model.Carro;
import model.Marca;
import model.Modelo;

public class CarroInsertView implements ActionListener{
	
	private enum Actions {
	    MARCA,
	    MODELO,
	    CADASTRAR,
	    FECHAR
	  }
	
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JLabel lbltitle = new JLabel("Cadastro");
	private JLabel lblid = new JLabel("ID");
	private JLabel lblplaca = new JLabel("Placa");
	private JLabel lblmarca = new JLabel("Marca");
	private JLabel lblmodelo = new JLabel("Modelo");
	private JTextField txtid = new JTextField(10);
	private JTextField txtplaca = new JTextField(30);
	private JButton btncadastrar = new JButton("Cadastrar");
	private JButton btnclose = new JButton("Fechar");
	private JLabel lblrow = new JLabel("<html><br/></html>");
	private String SelectedMarca;
	private String SelectedModelo;
	
	public CarroInsertView() {
		btncadastrar.setActionCommand(Actions.CADASTRAR.name());		
		btncadastrar.addActionListener(this);
		btnclose.setActionCommand(Actions.FECHAR.name());		
		btnclose.addActionListener(this);

		panel.add(lbltitle);
		panel.setBorder(BorderFactory.createEmptyBorder(0,100,100,100));
		panel.setLayout(new GridLayout(0,1));
		panel.add(lblid);
		panel.add(txtid);
		panel.add(lblplaca);
		panel.add(txtplaca);
		panel.add(lblmarca);

		
	
		Vector <String> marcas = new Vector<String>();
		
		MarcaController controller = new MarcaController();
		
		for (Marca marca:controller.readMarcaAll()) {
			marcas.addElement(marca.getNome());
		}
		
		JComboBox<String> ComboMarcas = new JComboBox<String>(marcas);
		ComboMarcas.setActionCommand(Actions.MARCA.name());
		ComboMarcas.setSelectedIndex(0);
		ComboMarcas.addActionListener(this);
		ComboMarcas.addActionListener(new ActionListener() {
			 
		    @Override
		    public void actionPerformed(ActionEvent event) {
		    	@SuppressWarnings("unchecked")
				JComboBox<String> combo = (JComboBox<String>) event.getSource();
		        String selectedItem = (String) combo.getSelectedItem();
	        	Vector <String> modelos = new Vector<String>();
	        	ModeloController controller = new ModeloController();
		        JComboBox<String> ComboModelos = new JComboBox<String>(modelos);
		     
		    	if (event.getActionCommand() == Actions.MARCA.name()) {
		    		
		    		SelectedMarca = selectedItem;
		    	
		    		for (Modelo modelo:controller.readModelo(selectedItem)) {
		    			modelos.addElement(modelo.getNome());
		    		}
			        
		    		ComboModelos.setActionCommand(Actions.MODELO.name());
			        ComboModelos.setSelectedIndex(0);
		    		ComboModelos.addActionListener(this);
		    		panel.remove(ComboMarcas);
		    		JLabel lblselectedmarca = new JLabel(SelectedMarca);
		    		panel.add(lblselectedmarca);
					panel.add(lblmodelo);
		    		panel.add(ComboModelos);
		    	}
		    	
		    	else if(event.getActionCommand() == Actions.MODELO.name()) {
		    		SelectedModelo = selectedItem;
		    		panel.add(lblrow);
		    		panel.add(btncadastrar);
		    	}

				frame.pack();
	    		
		    }
		});
		

		panel.add(ComboMarcas);
		frame.add(panel,BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		frame.setTitle("Cadastro");
		
	}

	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub

		if (event.getActionCommand() == Actions.CADASTRAR.name()) {
			try {
				
				Carro carro = new Carro();
				CarroController controller = new CarroController();
				

				carro.setId(txtid.getText());
				carro.setPlaca(txtplaca.getText());
				carro.setModelo(SelectedModelo);
				carro.setMarca(SelectedMarca);
				
				controller.create(carro);
				panel.add(btnclose);
				frame.pack();
				
				}	catch(Exception e) {
					panel.add(btnclose);
					frame.pack();
				}
				
			
		}	else if (event.getActionCommand() == Actions.FECHAR.name()) {
			new MainView ();
			frame.dispose();
		}
	
	}
	
	
	
	

}
