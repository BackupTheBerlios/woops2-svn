package market.model;

/**
 * 
 * 
 */
public class User {

    private String login;

    private String password;

    public Wallet wallet;

    public String getLogin() {
		return login;
	}

	public void setLogin(String _login) {
		this.login = _login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String _password) {
		this.password = _password;
	}

	public Wallet getWallet() {
        return wallet;
    }
    
    public void setWallet(Wallet _wallet) {
        this.wallet = _wallet;
    }
 }
