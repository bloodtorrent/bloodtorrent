package pageClasses;

import net.sf.sahi.client.Browser;

public class Main extends BasePage {
	public Main(Browser browser) {
		super(browser);
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
