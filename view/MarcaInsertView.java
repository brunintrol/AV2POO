package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.MarcaController;
import model.Marca;

public class MarcaInsertView implements ActionListener{
	
	private enum Actions {
	    CADASTRAR,
	    FECHAR
	  }
	
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JLabel lbltitle = new JLabel("Cadastro Marca");
	private JLabel lblid = new JLabel("ID");
	private JLabel lblmarca = new JLabel("Nome Marca");
	private JTextField txtid = new JTextField(10);
	private JTextField txtmarca = new JTextField(30);
	private JButton btncadastrar = new JButton("Cadastrar");
	private JButton btnclose = new JButton("Fechar");
	private JLabel lblstatus = new JLabel("");

	
	public MarcaInsertView() {
		btncadastrar.setActionCommand(Actions.CADASTRAR.name());		
		btncadastrar.addActionListener(this);
		btnclose.setActionCommand(Actions.FECHAR.name());		
		btnclose.addActionListener(this);

		panel.add(lbltitle);
		panel.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
		panel.setLayout(new GridLayout(0,1));
		panel.add(lblid);
		panel.add(txtid);
		panel.add(lblmarca);
		panel.add(txtmarca);
		panel.add(btncadastrar);

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
				
				Marca marca = new Marca();
				MarcaController controller = new MarcaController();
				

				marca.setId(txtid.getText());
				marca.setNome(txtmarca.getText());
				
				controller.create(marca);
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
