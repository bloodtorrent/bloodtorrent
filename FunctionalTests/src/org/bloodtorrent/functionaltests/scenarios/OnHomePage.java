package org.bloodtorrent.functionaltests.scenarios;

import static com.thoughtworks.twist.core.execution.TwistVerification.verifyEquals;
import static com.thoughtworks.twist.core.execution.TwistVerification.verifyTrue;
import net.sf.sahi.client.Browser;
import org.bloodtorrent.functionaltests.pages.HomePage;

public class OnHomePage {

	private HomePage homePage;

	public OnHomePage(Browser browser) {
		homePage = new HomePage(browser);
	}

	public void verifyTitleOfHomePage() throws Exception {
		verifyEquals("Blood Torrent", homePage.getTitle());
	}

	public void selectRegisterDonor() throws Exception {
		homePage.registerDonor();
	}

	public void requestForBlood() throws Exception {
		homePage.requestForBlood();
	}

	public void verifyUserIsNotLoggedIn() throws Exception {
		verifyTrue(homePage.loginButton().exists());
	}

	public void verifySuccessStoriesSectionIsVisible() throws Exception {
		verifyTrue(homePage.getSectionOfSuccessStory().exists());
	}

	public void verifyReadMoreLinkIsPresentForSuccessStories() throws Exception {
		verifyTrue(homePage.getReadMoreLink().exists());
	}

	public void displayDetailedSuccessStory() throws Exception {
		homePage.getDetailSuccessStory();
	}

	public void navigateToAdminPage() throws Exception {
		homePage.gotoLoginPage();
	
	}
}