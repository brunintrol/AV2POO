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

import controller.ModeloController;
import model.Modelo;


public class ModeloUpdateView implements ActionListener{
	
	private enum Actions {
	    MODELO,
	    ATUALIZAR,
	    FECHAR
	  }
	
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JLabel lbltitle = new JLabel("Atualizar");
	private JLabel lblmarca = new JLabel("Novo Modelo");
	private JTextField txtnome = new JTextField(30);
	private JButton btnatualizar = new JButton("Atualizar");
	private JButton btnclose = new JButton("Fechar");
	private String modeloantigo;
	private JLabel lblstatus = new JLabel("");
	
	public ModeloUpdateView() {
		btnatualizar.setActionCommand(Actions.ATUALIZAR.name());		
		btnatualizar.addActionListener(this);
		btnclose.setActionCommand(Actions.FECHAR.name());		
		btnclose.addActionListener(this);

		panel.add(lbltitle);
		panel.setBorder(BorderFactory.createEmptyBorder(0,50,50,50));
		panel.setLayout(new GridLayout(0,1));

	
		Vector <String> modelos = new Vector<String>();
		
		ModeloController controller = new ModeloController();
		
		for (Modelo modelo:controller.readModeloAll()) {
			modelos.addElement(modelo.getNome());
		}
		
		JComboBox<String> ComboModelo = new JComboBox<String>(modelos);
		ComboModelo.setActionCommand(Actions.MODELO.name());
		ComboModelo.setSelectedIndex(0);
		ComboModelo.addActionListener(this);
		ComboModelo.addActionListener(new ActionListener() {
			 
		    @Override
		    public void actionPerformed(ActionEvent event) {
		    	@SuppressWarnings("unchecked")
				JComboBox<String> combo = (JComboBox<String>) event.getSource();
		        String selectedItem = (String) combo.getSelectedItem();
		    	if (event.getActionCommand() == Actions.MODELO.name()) {
		    		modeloantigo = selectedItem;
			        JLabel lblselected = new JLabel(selectedItem);
		    		panel.remove(ComboModelo);
		    		panel.add(lblselected);
		    		panel.add(lblmarca);
		    		panel.add(txtnome);
		    		panel.add(btnatualizar);
		    	}

				frame.pack();
	    		
		    }
		});
		
		panel.add(ComboModelo);
		frame.add(panel,BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		frame.setTitle("Atualização");
		
	}

	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub

		if (event.getActionCommand() == Actions.ATUALIZAR.name()) {
			try {
				
				Modelo modelo = new Modelo();
				ModeloController controller = new ModeloController();

				modelo.setNome(txtnome.getText());
				
				controller.update(modelo,modeloantigo);
				lblstatus.setText("Atualizado com sucesso");
				panel.add(lblstatus);
				panel.add(btnclose);
				frame.pack();
				
				}	catch(Exception e) {
					panel.remove(lblstatus);
					lblstatus.setText("Erro ao Atualizar");
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

