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
		verifyEquals("Blood Torrent", browser.title());
	}

}