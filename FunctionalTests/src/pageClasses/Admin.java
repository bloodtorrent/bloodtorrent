package pageClasses;

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
	

}
