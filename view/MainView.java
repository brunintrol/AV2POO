package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.CarroController;
import controller.MarcaController;
import controller.ModeloController;
import model.Carro;
import model.Marca;
import model.Modelo;

public class MainView implements ActionListener {
	private enum Actions {
	    CARROINSERT,
	    CARROREAD,
	    CARROUPDATE,
	    CARRODELETE,
	    MARCAINSERT,
	    MARCAREAD,
	    MARCAUPDATE,
	    MARCADELETE,
	    MODELOINSERT,
	    MODELOREAD,
	    MODELOUPDATE,
	    MODELODELETE
	  }
	
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JLabel lblcarrotitle = new JLabel("CARRO");
	private JButton btncarroinsert = new JButton("Inserir Carro");
	private JButton btncarroupdate = new JButton("Atualizar Carro");
	private JButton btncarrodelete = new JButton("Deletar Carro");
	private JLabel lblcarrosubtitle = new JLabel("LISTA DE CARROS: ");
	private JLabel lblmodelosubtitle = new JLabel("LISTA DE MODELOS: ");
	private JLabel lblmarcasubtitle = new JLabel("LISTA DE MARCAS: ");
	private JLabel lblmarcatitle = new JLabel("MARCA");
	private JButton btnmarcainsert = new JButton("Inserir Marca");
	private JButton btnmarcaupdate = new JButton("Atualizar Marca");
	private JButton btnmarcadelete = new JButton("Deletar Marca");
	private JLabel lblmodelotitle = new JLabel("MODELO");
	private JButton btnmodeloinsert = new JButton("Inserir Modelo");
	private JButton btnmodeloupdate = new JButton("Atualizar Modelo");
	private JButton btnmodelodelete = new JButton("Deletar Modelo");

	
	
public MainView() {
	
		btncarroinsert.setActionCommand(Actions.CARROINSERT.name());
		btncarroupdate.setActionCommand(Actions.CARROUPDATE.name());
		btncarrodelete.setActionCommand(Actions.CARRODELETE.name());

		btnmarcainsert.setActionCommand(Actions.MARCAINSERT.name());
		btnmarcaupdate.setActionCommand(Actions.MARCAUPDATE.name());
		btnmarcadelete.setActionCommand(Actions.MARCADELETE.name());
		
		btnmodeloinsert.setActionCommand(Actions.MODELOINSERT.name());
		btnmodeloupdate.setActionCommand(Actions.MODELOUPDATE.name());
		btnmodelodelete.setActionCommand(Actions.MODELODELETE.name());

		btncarroinsert.addActionListener(this);
		btncarroupdate.addActionListener(this);
		btncarrodelete.addActionListener(this);
		
		btnmarcainsert.addActionListener(this);
		btnmarcaupdate.addActionListener(this);
		btnmarcadelete.addActionListener(this);
		
		btnmodeloinsert.addActionListener(this);
		btnmodeloupdate.addActionListener(this);
		btnmodelodelete.addActionListener(this);
		
		panel.setBorder(BorderFactory.createEmptyBorder(0,100,100,100));
    	panel.setLayout(new GridLayout(0,1));

		panel.add(new JLabel("<html><br></html>"));
    	panel.add(lblcarrotitle);
		panel.add(btncarroinsert);
		panel.add(btncarroupdate);
		panel.add(btncarrodelete);
		panel.add(new JLabel("<html><br></html>"));
		panel.add(lblcarrosubtitle);
		
		CarroController carrocontroller = new CarroController();
		
		for (Carro carro:carrocontroller.readAll()) {
			panel.add(new JLabel("Placa: " + carro.getPlaca() + "Marca: " + carro.getMarca() + "Modelo: " + carro.getModelo()));

		}
		
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, 500, 400);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, 400));
        contentPane.add(scrollPane);
        
		panel.add(new JLabel("<html><br></html>"));
        panel.add(lblmarcatitle);
		panel.add(btnmarcainsert);
		panel.add(btnmarcaupdate);
		panel.add(btnmarcadelete);	
		panel.add(new JLabel("<html><br></html>"));
        panel.add(lblmarcasubtitle);
        
        MarcaController marcacontroller = new MarcaController();
		
        for (Marca marca:marcacontroller.readMarcaAll()) {
			panel.add(new JLabel(marca.getNome()));
		}

		panel.add(new JLabel("<html><br></html>"));
		panel.add(lblmodelotitle);
		panel.add(btnmodeloinsert);
		panel.add(btnmodeloupdate);
		panel.add(btnmodelodelete);
		panel.add(new JLabel("<html><br></html>"));
        panel.add(lblmodelosubtitle);
        
        ModeloController modelocontroller = new ModeloController();
        
        for (Modelo modelo:modelocontroller.readModeloAll()) {
        	panel.add(new JLabel(modelo.getNome()));
        }
		
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
  
	}

public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub

	 if (e.getActionCommand() == Actions.CARROINSERT.name()) {
		 new CarroInsertView();
		 frame.dispose();
	    }
	 else if (e.getActionCommand() == Actions.CARROUPDATE.name()) {
		 new CarroUpdateView();
		 frame.dispose();
	 }
	 else if (e.getActionCommand() == Actions.CARRODELETE.name()) {
		 new CarroDeleteView();
		 frame.dispose();
	 }	 
	 else if (e.getActionCommand() == Actions.MARCAINSERT.name()) {
		 new MarcaInsertView();
		 frame.dispose();
	 }	 	 
	 else if (e.getActionCommand() == Actions.MARCAUPDATE.name()) {
		 new MarcaUpdateView();
		 frame.dispose();
	 }	 
 	 
	 else if (e.getActionCommand() == Actions.MARCADELETE.name()) {
		 new MarcaDeleteView();
		 frame.dispose();
	 }	
	 else if (e.getActionCommand() == Actions.MODELOINSERT.name()) {
		 new ModeloInsertView();
		 frame.dispose();
	 }	
	 else if (e.getActionCommand() == Actions.MODELOUPDATE.name()) {
		 new ModeloUpdateView();
		 frame.dispose();
	 }	
	 else if (e.getActionCommand() == Actions.MODELODELETE.name()) {
		 new ModeloDeleteView();
		 frame.dispose();
	 }	
	 
	 
}
}
