package org.bloodtorrent.functionaltests.scenarios;

import net.sf.sahi.client.Browser;

import org.bloodtorrent.functionaltests.pages.LoginPage;

public class OnLoginPage {

	private LoginPage loginPage;

	private String adminEmail = "Administrator@bloodtorrent.org";
	private String adminPassWord = "p@ssw0rd";
	
	public OnLoginPage(Browser browser) {
		loginPage = new LoginPage(browser);
	}

	public void fillOutTheValidInformationForLoginAsAdmin() throws Exception {
		loginPage.enterLoginCredentials(adminEmail, adminPassWord);
	}

	public void login() throws Exception {
		loginPage.login();
	}
}
