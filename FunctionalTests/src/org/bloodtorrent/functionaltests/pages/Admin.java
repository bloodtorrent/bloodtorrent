package org.bloodtorrent.functionaltests.pages;

import net.sf.sahi.client.Browser;

public class Admin extends BasePage {

	public Admin(Browser browser) {
		super(browser);
	}

	public String getUrl() {
		return browser.fetch("window.location.href");
	}

	public void signOff() {
		browser.link("Sign off").click();
	}

	public void goToCreateSuccessStroy() {
		browser.button("Create").click();
	}

	public String getTitleofSuccessStoryList() {
		return browser.heading1("title").text();
	}
	

}
