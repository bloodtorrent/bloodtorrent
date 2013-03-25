import static com.thoughtworks.twist.core.execution.TwistVerification.verifyEquals;
import net.sf.sahi.client.Browser;

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
		verifyEquals("Welcome to Blood Torrent", browser.byId("title").text());
	}

	public void openLink(String string1) throws Exception {
		browser.button(string1).click();
	}

}