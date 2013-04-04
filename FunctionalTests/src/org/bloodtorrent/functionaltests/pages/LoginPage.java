package org.bloodtorrent.functionaltests.pages;

import net.sf.sahi.client.Browser;

public class LoginPage extends BasePage {
	public LoginPage(Browser browser) {
		super(browser);
	}

	public void enterLoginCredentials(String email, String password) {
		System.out.println("email, password" + email + ", " + password);
		browser.textbox("email").setValue(email);
		browser.password("password").setValue(password);
		
		System.out.println("after email, password : " + browser.textbox("email").getText() + ", " + browser.password("password").getText());
	}

	public void login() {
	
		browser.button("signin").click();
	}
}
