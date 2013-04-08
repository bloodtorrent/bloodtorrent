package org.bloodtorrent.functionaltests.contexts;

import net.sf.sahi.client.Browser;

import org.bloodtorrent.functionaltests.pages.Admin;
import org.bloodtorrent.functionaltests.pages.HomePage;
import org.bloodtorrent.functionaltests.pages.LoginPage;

public class LoggedInAsAdmin {
	
	private LoginPage loginPage;
	private HomePage homePage;
	private Admin admin;

	public LoggedInAsAdmin(Browser browser) {
		loginPage = new LoginPage(browser);
		homePage = new HomePage(browser);
		admin = new Admin(browser);
	}

	public void setUp() throws Exception {
		homePage.gotoPage("successStory/list");
		loginPage.setTheValidAdminCredentials();
		loginPage.login();
	}

	public void tearDown() throws Exception {
		admin.signOff();
	}

}
