import static com.thoughtworks.twist.core.execution.TwistVerification.verifyEquals;
import net.sf.sahi.client.Browser;

import org.springframework.beans.factory.annotation.Autowired;

import pageClasses.MainPage;

import com.thoughtworks.twist.core.execution.TwistScenarioDataStore;

public class OnMainPage {

	@Autowired
	private TwistScenarioDataStore scenarioStore;

	private MainPage mainPage;

	public OnMainPage(Browser browser) {
		mainPage = new MainPage(browser);
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
}