package controller;

import model.LoginModel;
import view.LoginView;
import view.MainView;

public class LoginController {

  public void login (String login, String password) {
    if (!login.isEmpty() && !password.isEmpty()) {
      LoginModel model = new LoginModel(login, password);

      if (model.validate()) {
    	  new MainView();
      } else {
    	  this.loginError();
      }
    } else {
    	this.loginError();
    }
  }
  
  private void loginError () {
	  LoginView renderView = new LoginView(true);
	  	renderView.render();
  }
  
  
}