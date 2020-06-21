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

import controller.MarcaController;
import controller.ModeloController;
import model.Marca;
import model.Modelo;

public class ModeloInsertView implements ActionListener{
	
	private enum Actions {
	    MARCA,
	    CADASTRAR,
	    FECHAR
	  }
	
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JLabel lbltitle = new JLabel("Cadastro Modelo");
	private JLabel lblid = new JLabel("ID");
	private JLabel lblnome = new JLabel("Nome");
	private JTextField txtnome = new JTextField(30);
	private JTextField txtid = new JTextField(10);
	private JLabel lblmarca = new JLabel("Marca");
	private JButton btncadastrar = new JButton("Cadastrar");
	private JButton btnclose = new JButton("Fechar");
	private String SelectedModelo;
	private JLabel lblstatus = new JLabel("");
	
	public ModeloInsertView() {
		btncadastrar.setActionCommand(Actions.CADASTRAR.name());		
		btncadastrar.addActionListener(this);
		btnclose.setActionCommand(Actions.FECHAR.name());		
		btnclose.addActionListener(this);

		panel.add(lbltitle);
		panel.setBorder(BorderFactory.createEmptyBorder(0,100,100,100));
		panel.setLayout(new GridLayout(0,1));
		panel.add(lblid);
		panel.add(txtid);
		panel.add(lblnome);
		panel.add(txtnome);
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
		     
		    	if (event.getActionCommand() == Actions.MARCA.name()) {
		    		
		    		SelectedModelo = selectedItem;
		    		JLabel lblselectedmarca = new JLabel(SelectedModelo);
		    		panel.remove(ComboMarcas);;
		    		panel.add(lblselectedmarca);
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
				
				Modelo modelo = new Modelo();
				ModeloController controller = new ModeloController();
				

				modelo.setId(txtid.getText());
				modelo.setNome(txtnome.getText());
				
				controller.create(modelo,SelectedModelo);
				lblstatus.setText("Cadastrado com sucesso");
				panel.add(lblstatus);
				panel.add(btnclose);
				frame.pack();
				
				}	catch(Exception e) {
					lblstatus.setText("Erro ao Cadastrar");
					panel.add(lblstatus);
					panel.add(btnclose);
					frame.pack();
				}
				
			
		}	else if (event.getActionCommand() == Actions.FECHAR.name()) {
			new MainView ();
			frame.dispose();
		}
	
	}
	
	
	
	

}
