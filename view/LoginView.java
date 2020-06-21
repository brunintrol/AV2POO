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

import controller.LoginController;

public class LoginView implements ActionListener {

  private boolean error = false;
  private String login;
  private String password;
  
  private JFrame frame = new JFrame();
  private JPanel panel = new JPanel();
  private JLabel lbltitle = new JLabel("Login");
  private JTextField txtlogin = new JTextField(10);
  private JTextField txtpassword = new JTextField(10);
  private JButton btnlogin = new JButton("Logar");
  private JLabel lblstatus = new JLabel("");
  
  public LoginView (boolean error) {
	  if (error) {
		  this.error = true;
	  }
  }


  // Handles
  public void handleSubmit() {
    final LoginController controller = new LoginController();
    controller.login(this.login, this.password);
  }

  // Render
  public void render() {
    // Quando clicar no botão enviar chamar função
	  
	  	btnlogin.addActionListener(this);
		
		panel.setBorder(BorderFactory.createEmptyBorder(0,50,50,50));
		panel.setLayout(new GridLayout(0,1));
		panel.add(lbltitle);
		panel.add(lblstatus);
		panel.add(txtlogin);
		panel.add(txtpassword);
		panel.add(btnlogin);
		frame.add(panel,BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setTitle("Login");
		
		if (this.error) {
			lblstatus.setText("Login ou senha Invalidos!!!");
		}
  }
  
  
  
  @Override
  public void actionPerformed(ActionEvent e) {
  	// TODO Auto-generated method stub
	  	this.login = txtlogin.getText();
	  	this.password = txtpassword.getText();
		this.handleSubmit();
		frame.dispose();
  }
  
  

  public static void main(final String[] args) {
    final LoginView renderView = new LoginView(false);
    renderView.render();
  }




}