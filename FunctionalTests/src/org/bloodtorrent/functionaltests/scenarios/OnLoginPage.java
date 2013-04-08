package org.bloodtorrent.functionaltests.scenarios;

import static junit.framework.Assert.assertTrue;
import net.sf.sahi.client.Browser;

import org.bloodtorrent.functionaltests.pages.LoginPage;

public class OnLoginPage {

	private LoginPage loginPage;
	
	public OnLoginPage(Browser browser) {
		loginPage = new LoginPage(browser);
	}

	public void login() throws Exception {
		loginPage.login();
	}

	public void verifyDisplayingLoginPage() throws Exception {
		assertTrue(loginPage.getIdElement().exists());
		assertTrue(loginPage.getPasswordElement().exists());
		assertTrue(loginPage.getSignInButtonElement().exists());
	}

	public void fillOutTheValidAdminCredentials() throws Exception {
		loginPage.setTheValidAdminCredentials();
	}

	public void fillOutTheInvalidAdminCredentials() throws Exception {
		loginPage.setEmail("ddd");
		loginPage.setPassword("ddd");
	}
}
