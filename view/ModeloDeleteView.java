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

import controller.ModeloController;
import model.Modelo;




public class ModeloDeleteView implements ActionListener{
	
	private enum Actions {
	    MODELO,
	    DELETAR,
	    FECHAR
	  }
	
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JLabel lbltitle = new JLabel("Deletar");
	private JButton btnatualizar = new JButton("Deletar");
	private JButton btnclose = new JButton("Fechar");
	private JLabel lblstatus = new JLabel("");
	private String selected;
	
	public ModeloDeleteView() {
		btnatualizar.setActionCommand(Actions.DELETAR.name());		
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
		
		JComboBox<String> ComboModelos = new JComboBox<String>(modelos);
		ComboModelos.setActionCommand(Actions.MODELO.name());
		ComboModelos.setSelectedIndex(0);
		ComboModelos.addActionListener(this);
		ComboModelos.addActionListener(new ActionListener() {
			 
		    @Override
		    public void actionPerformed(ActionEvent event) {
				@SuppressWarnings("unchecked")
				JComboBox<String> combo = (JComboBox<String>) event.getSource();
		        String selectedItem = (String) combo.getSelectedItem();
		    	if (event.getActionCommand() == Actions.MODELO.name()) {
		    		selected = selectedItem;
			        JLabel lblselected = new JLabel(selectedItem);
		    		panel.remove(ComboModelos);
		    		panel.add(lblselected);
		    		panel.add(btnatualizar);
		    	}

				frame.pack();
	    		
		    }
		});
		
		panel.add(ComboModelos);
		frame.add(panel,BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		frame.setTitle("Atualização");
		
	}

	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub

		if (event.getActionCommand() == Actions.DELETAR.name()) {
			try {
				
				Modelo modelo = new Modelo();
				ModeloController controller = new ModeloController();
				
				modelo.setNome(selected);
				
				controller.delete(modelo);
				lblstatus.setText("Deletado com sucesso");
				panel.add(lblstatus);
				panel.add(btnclose);
				frame.pack();
				
				}	catch(Exception e) {
					panel.remove(lblstatus);
					lblstatus.setText("Erro ao Deletar");
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