package org.bloodtorrent.functionaltests.pages;

import net.sf.sahi.client.Browser;

public class ThankYou extends BasePage {

	public ThankYou(Browser browser){
		super(browser);
	}

	public String getMessageForBloodRequest() {
		return browser.heading1("Thank you for posting your request. We will get in touch soon to validate the details.").getText();
	}

	public String getMessageForRegistering() {
		return browser.listItem(0).text();
	}
}