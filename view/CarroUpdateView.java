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
import model.Carro;

public class CarroUpdateView implements ActionListener{
	
	private enum Actions {
	    CARRO,
	    ATUALIZAR,
	    FECHAR
	  }
	
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JLabel lbltitle = new JLabel("Atualizar");
	private JLabel lblplaca = new JLabel("Nova Placa");
	private JTextField txtplaca = new JTextField(30);
	private JButton btnatualizar = new JButton("Atualizar");
	private JButton btnclose = new JButton("Fechar");
	private String placaantiga;
	private JLabel lblstatus = new JLabel("");
	
	public CarroUpdateView() {
		btnatualizar.setActionCommand(Actions.ATUALIZAR.name());		
		btnatualizar.addActionListener(this);
		btnclose.setActionCommand(Actions.FECHAR.name());		
		btnclose.addActionListener(this);

		panel.add(lbltitle);
		panel.setBorder(BorderFactory.createEmptyBorder(0,50,50,50));
		panel.setLayout(new GridLayout(0,1));

	
		Vector <String> carros = new Vector<String>();
		
		CarroController controller = new CarroController();
		
		for (Carro carro:controller.readAll()) {
			carros.addElement(carro.getPlaca());
		}
		
		JComboBox<String> ComboCarros = new JComboBox<String>(carros);
		ComboCarros.setActionCommand(Actions.CARRO.name());
		ComboCarros.setSelectedIndex(0);
		ComboCarros.addActionListener(this);
		ComboCarros.addActionListener(new ActionListener() {
			 
		    @Override
		    public void actionPerformed(ActionEvent event) {
		    	@SuppressWarnings("unchecked")
				JComboBox<String> combo = (JComboBox<String>) event.getSource();
		        String selectedItem = (String) combo.getSelectedItem();
		    	if (event.getActionCommand() == Actions.CARRO.name()) {
		    		placaantiga = selectedItem;
			        JLabel lblselected = new JLabel(selectedItem);
		    		panel.remove(ComboCarros);
		    		panel.add(lblselected);
		    		panel.add(lblplaca);
		    		panel.add(txtplaca);
		    		panel.add(btnatualizar);
		    	}

				frame.pack();
	    		
		    }
		});
		
		panel.add(ComboCarros);
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
				
				Carro carro = new Carro();
				CarroController controller = new CarroController();

				carro.setPlaca(txtplaca.getText());
				
				controller.update(carro,placaantiga);
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
