package org.bloodtorrent.functionaltests.scenarios;
import static com.thoughtworks.twist.core.execution.TwistVerification.verifyEquals;
import static com.thoughtworks.twist.core.execution.TwistVerification.verifyTrue;
import net.sf.sahi.client.Browser;

import org.bloodtorrent.functionaltests.pages.MainPage;
import org.springframework.beans.factory.annotation.Autowired;


import com.thoughtworks.twist.core.execution.TwistScenarioDataStore;

public class OnMainPage {

	@Autowired
	private TwistScenarioDataStore scenarioStore;
	private String adminEmail = "Administrator@bloodtorrent.org";
	private String adminPassWord = "p@ssw0rd";

	private MainPage mainPage;

	public OnMainPage(Browser browser) {
		mainPage = new MainPage(browser);
	}

	public void verifyMainTitle() throws Exception {
		verifyEquals("Blood Torrent", mainPage.getTitle());
	}

	public void selectRegisterDonor() throws Exception {
		mainPage.registerDonor();
	}

	public void requestForBlood() throws Exception {
		mainPage.requestForBlood();
	}

	public void verifyNonLoginStatus() throws Exception {
		verifyTrue(mainPage.loginButton().exists());
	}

	public void fillOutTheValidInformationForLoginAsAdmin() throws Exception {
		mainPage.enterLoginCredentials(adminEmail, adminPassWord);
	}

	public void login() throws Exception {
		mainPage.login();
	}

	public void verifySuccessStoriesSectionIsVisible() throws Exception {
		verifyTrue(mainPage.getSectionOfSuccessStory().exists());
	}

	public void verifyReadMoreLinkIsPresentForSuccessStories() throws Exception {
		verifyTrue(mainPage.getReadMoreLink().exists());
	}

	public void displayDetailedSuccessStory() throws Exception {
		mainPage.getDetailSuccessStory();
	}
}