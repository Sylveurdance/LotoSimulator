package model;

public class User {

	private String login;
	private Integer money;
	private Integer nb_tirages;

	public User(String login, Integer money, Integer nb_tirages) {
		this.login = login;
		this.money = money;
		this.nb_tirages = nb_tirages;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Integer getNb_tirages() {
		return nb_tirages;
	}

	public void setNb_tirages(Integer nb_tirages) {
		this.nb_tirages = nb_tirages;
	}
	
	
	
}
