package org.bloodtorrent.functionaltests.scenarios;

import net.sf.sahi.client.Browser;

public class OnBrowser {

	private Browser browser;

	public OnBrowser(Browser browser) {
		this.browser = browser;
	}

	public void navigateTo(String url) throws Exception {
		browser.navigateTo(url);
	}


}