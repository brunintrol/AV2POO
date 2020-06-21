package model;

public class LoginModel {

  private String login;
  private String password;

	public LoginModel (String login, String password) {
    this.setLogin(login);
    this.setPassword(password);
  }

  // Sets
  public void setLogin (String login) {
    this.login = login;
  }

  public void setPassword (String password) {
    this.password = password;
  }

  // Gets
  public String getLogin () {
    return login;
  }

  public String getPassword () {
    return password;
  }

  public boolean validate () {
    try {
      if ("lasalle".compareTo(this.getLogin()) == 0 && "1234".compareTo(this.getPassword()) == 0 ) {
        // Adicionar função do DAO para salvar no banco
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
  }
}