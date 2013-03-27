package org.bloodtorrent.functionaltests.scenarios;
import net.sf.sahi.client.Browser;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtworks.twist.core.execution.TwistScenarioDataStore;

public class OnBrowser {

	private Browser browser;

	@Autowired
	private TwistScenarioDataStore scenarioStore;

	public OnBrowser(Browser browser) {
		this.browser = browser;
	}

	public void navigateTo(String url) throws Exception {
		browser.navigateTo(url);
	
	}


}