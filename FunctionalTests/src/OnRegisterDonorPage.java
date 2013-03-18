// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtworks.twist.core.execution.TwistScenarioDataStore;

public class OnRegisterDonorPage {

	private Browser browser;

	@Autowired
	private TwistScenarioDataStore scenarioStore;

	public OnRegisterDonorPage(Browser browser) {
		this.browser = browser;
	}

}