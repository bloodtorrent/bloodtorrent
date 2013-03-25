// JUnit Assert framework can be used for verification

import static com.thoughtworks.twist.core.execution.TwistVerification.verifyEquals;
import static com.thoughtworks.twist.core.execution.TwistVerification.verifyTrue;
import net.sf.sahi.client.Browser;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtworks.twist.core.execution.TwistScenarioDataStore;

public class OnAdminPage {

	private Browser browser;

	@Autowired
	private TwistScenarioDataStore scenarioStore;

	public OnAdminPage(Browser browser) {
		this.browser = browser;
	}

	public void verifyAdminPage() throws Exception {
		verifyEquals("Welcome to Blood Torrent", browser.byId("title").text());
	}

	public void verifyLoginStatusOf(String string1) throws Exception {
		verifyTrue(browser.link("Sign off").exists());
	}

	public void logout() throws Exception {
		browser.link("Sign off").click();
	
	}

}