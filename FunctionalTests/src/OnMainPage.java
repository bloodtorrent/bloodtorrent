import static com.thoughtworks.twist.core.execution.TwistVerification.verifyEquals;
import static com.thoughtworks.twist.core.execution.TwistVerification.verifyTrue;
import net.sf.sahi.client.Browser;

import org.springframework.beans.factory.annotation.Autowired;

import pageClasses.Main;

import com.thoughtworks.twist.core.execution.TwistScenarioDataStore;

public class OnMainPage {

	@Autowired
	private TwistScenarioDataStore scenarioStore;

	private Main mainPage;

	public OnMainPage(Browser browser) {
		mainPage = new Main(browser);
	}

	public void verifyMainTitle() throws Exception {
		verifyEquals("Welcome to Blood Torrent", mainPage.getTitle());
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

	public void fillOutTheValidInformationForLogin(String email, String password)
			throws Exception {
		mainPage.enterLoginCredentials(email, password);
	}

	public void login() throws Exception {
		mainPage.login();
	}
}