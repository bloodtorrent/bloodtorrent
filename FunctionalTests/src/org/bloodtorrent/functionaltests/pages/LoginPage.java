package org.bloodtorrent.functionaltests.pages;

import net.sf.sahi.client.Browser;

public class LoginPage extends BasePage {
	public LoginPage(Browser browser) {
		super(browser);
	}

	public void enterLoginCredentials(String email, String password) {
		browser.textbox("email").setValue(email);
		browser.password("password").setValue(password);
	}

	public void login() {
	
		browser.button("signin").click();
	}
}
