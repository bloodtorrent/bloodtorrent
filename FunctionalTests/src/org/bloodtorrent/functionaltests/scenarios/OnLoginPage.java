package org.bloodtorrent.functionaltests.scenarios;

// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;

import org.bloodtorrent.functionaltests.pages.LoginPage;
import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtworks.twist.core.execution.TwistScenarioDataStore;

public class OnLoginPage {

	private Browser browser;
	
	private LoginPage loginPage;

	private String adminEmail = "Administrator@bloodtorrent.org";
	private String adminPassWord = "p@ssw0rd";
	
	@Autowired
	private TwistScenarioDataStore scenarioStore;

	public OnLoginPage(Browser browser) {
		this.browser = browser;
		loginPage = new LoginPage(browser);
	}

	public void fillOutTheValidInformationForLoginAsAdmin() throws Exception {
		loginPage.enterLoginCredentials(adminEmail, adminPassWord);
	}

	public void login() throws Exception {
		loginPage.login();
	}
}
