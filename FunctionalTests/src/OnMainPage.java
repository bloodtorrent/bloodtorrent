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

	public void openLink(String linkName) throws Exception {
		browser.link(linkName).click();
	}

}