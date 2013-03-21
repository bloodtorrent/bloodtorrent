import net.sf.sahi.client.Browser;
import static com.thoughtworks.twist.core.execution.TwistVerification.verifyEquals;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtworks.twist.core.execution.TwistScenarioDataStore;

public class OnMainPage {

	private Browser browser;

	@Autowired
	private TwistScenarioDataStore scenarioStore;

	public OnMainPage(Browser browser) {
		this.browser = browser;
	}

	public void verifyMainTitle() throws Exception {
		System.out.println(browser.heading1("Welcome to Blood Torrent"));
		verifyEquals("Welcome to Blood Torrent", browser.heading1("Welcome to Blood Torrent"));
	}

}