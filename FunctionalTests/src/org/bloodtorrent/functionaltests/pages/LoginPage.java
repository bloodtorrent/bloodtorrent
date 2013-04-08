package org.bloodtorrent.functionaltests.pages;

import net.sf.sahi.client.Browser;
import net.sf.sahi.client.ElementStub;

public class LoginPage extends BasePage {

	final String ADMIN_EMAIL = "Administrator@bloodtorrent.org";
	final String ADMIN_PASSWORD = "p@ssw0rd";
	
	public LoginPage(Browser browser) {
		super(browser);
	}

	public void setTheValidAdminCredentials() {
		setEmail(ADMIN_EMAIL);
		setPassword(ADMIN_PASSWORD);	
	}
	
	public void login() {
	
		browser.button("signin").click();
	}
	
	public ElementStub getIdElement() {
		return browser.textbox("email");
	}
	
	public ElementStub getPasswordElement() {
		return browser.password("password");
	}
	
	public ElementStub getSignInButtonElement() {
		return browser.button("signin");
	}
	
	public void setEmail(String email) {
		getIdElement().setValue(email);
	}
	
	public void setPassword(String password) {
		getPasswordElement().setValue(password);
		
	}
	
}
