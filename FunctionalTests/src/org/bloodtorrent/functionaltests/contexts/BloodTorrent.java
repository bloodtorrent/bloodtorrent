package org.bloodtorrent.functionaltests.contexts;

import net.sf.sahi.client.Browser;

public class BloodTorrent {

	private Browser browser;
	private String url = "http://localhost:8080";

	public BloodTorrent(Browser browser) {
		this.browser = browser;
	}

	public void setUp() throws Exception {
		browser.navigateTo(url);
	}

	public void tearDown() throws Exception {
		//This method is executed after the scenario execution finishes.
	}

}
