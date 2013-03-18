// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtworks.twist.core.execution.TwistScenarioDataStore;

public class OnGoogle {

	private Browser browser;

	@Autowired
	private TwistScenarioDataStore scenarioStore;

	public OnGoogle(Browser browser) {
		this.browser = browser;
	}

	public void enterTheSearchWord(String string1) throws Exception {
		browser.textbox("q").setValue(string1);
		browser.span("gbqfi").click();
	
	}

	public void clickFirstResult() throws Exception {
		browser.link("Blood+ Torrent Download").click();
	}

}