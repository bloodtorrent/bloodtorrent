package org.bloodtorrent.functionaltests.scenarios;

// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtworks.twist.core.execution.TwistScenarioDataStore;

public class OnDetailedSuccessStoryPage {

	private Browser browser;

	@Autowired
	private TwistScenarioDataStore scenarioStore;

	public OnDetailedSuccessStoryPage(Browser browser) {
		this.browser = browser;
	}

	public void verifyDetailedSuccessStoryTitle() throws Exception {
	
	}

	public void goBackHome() throws Exception {
	
	}

}
