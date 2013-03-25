import static com.thoughtworks.twist.core.execution.TwistVerification.verifyEquals;
import net.sf.sahi.client.Browser;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtworks.twist.core.execution.TwistScenarioDataStore;

public class OnMainPage {

	private Browser browser;

	@Autowired
	private TwistScenarioDataStore scenarioStore;
	private String btnRegisterDonor="Register donor";
	private String btnRequestBlood="Request blood";

	public OnMainPage(Browser browser) {
		this.browser = browser;
	}

	public void verifyMainTitle() throws Exception {
		verifyEquals("Welcome to Blood Torrent", browser.byId("title").text());
	}

	public void selectRegisterDonor() throws Exception {
		browser.button(btnRegisterDonor).click();
	}

	public void requestForBlood() throws Exception {
		browser.button(btnRequestBlood).click();
	}

}