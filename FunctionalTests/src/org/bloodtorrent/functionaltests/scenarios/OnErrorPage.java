package org.bloodtorrent.functionaltests.scenarios;

import static com.thoughtworks.twist.core.execution.TwistVerification.verifyEquals;
import net.sf.sahi.client.Browser;

public class OnErrorPage {

	private Browser browser;

	public OnErrorPage(Browser browser) {
		this.browser = browser;
	}

	public void verifyErrorMessageExistingUser() throws Exception {
		verifyEquals("This email address is already taken.", browser.listItem(0).text());
	}

}