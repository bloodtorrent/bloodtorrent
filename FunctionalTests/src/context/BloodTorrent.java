package context;

import net.sf.sahi.client.Browser;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtworks.twist.core.execution.TwistScenarioDataStore;

public class BloodTorrent {

	private Browser browser;
	private String url = "http://localhost:8080";

	@Autowired
	private TwistScenarioDataStore scenarioStore;

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
