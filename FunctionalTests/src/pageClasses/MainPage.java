package pageClasses;

import net.sf.sahi.client.Browser;

public class MainPage {
	private Browser browser;

	public MainPage(Browser browser) {
		this.browser = browser;
	}

	public String getTitle() {
		return browser.byId("title").text();
	}
	
	public void registerDonor() {
		browser.button("Register donor").click();
	}
	
	public void requestForBlood() {
		browser.button("Request blood").click();
	}

}
