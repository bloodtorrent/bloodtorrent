package org.bloodtorrent.functionaltests.scenarios;
// JUnit Assert framework can be used for verification

import static com.thoughtworks.twist.core.execution.TwistVerification.verifyEquals;
import net.sf.sahi.client.Browser;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtworks.twist.core.execution.TwistScenarioDataStore;

public class OnErrorPage {

	private Browser browser;

	@Autowired
	private TwistScenarioDataStore scenarioStore;

	public OnErrorPage(Browser browser) {
		this.browser = browser;
	}

	public void verifyErrorMessageExistingUser() throws Exception {
		verifyEquals("This email address is already taken.", browser.listItem(0).text());
	}

}