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

import controller.MarcaController;
import model.Marca;



public class MarcaDeleteView implements ActionListener{
	
	private enum Actions {
	    MARCA,
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
	
	public MarcaDeleteView() {
		btnatualizar.setActionCommand(Actions.DELETAR.name());		
		btnatualizar.addActionListener(this);
		btnclose.setActionCommand(Actions.FECHAR.name());		
		btnclose.addActionListener(this);

		panel.add(lbltitle);
		panel.setBorder(BorderFactory.createEmptyBorder(0,50,50,50));
		panel.setLayout(new GridLayout(0,1));

	
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
		    		selected = selectedItem;
			        JLabel lblselected = new JLabel(selectedItem);
		    		panel.remove(ComboMarcas);
		    		panel.add(lblselected);
		    		panel.add(btnatualizar);
		    	}

				frame.pack();
	    		
		    }
		});
		
		panel.add(ComboMarcas);
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
				
				Marca marca = new Marca();
				MarcaController controller = new MarcaController();
				
				marca.setNome(selected);
				
				controller.delete(marca);
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