package context;

// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtworks.twist.core.execution.TwistScenarioDataStore;

public class RegisterDonor {

	private Browser browser;
	private String btnRegisterDonor = "Register donor";
	
	@Autowired
	private TwistScenarioDataStore scenarioStore;

	public RegisterDonor(Browser browser) {
		this.browser = browser;
	}

	public void setUp() throws Exception {
		browser.button(btnRegisterDonor).click();
		
	}

	public void tearDown() throws Exception {
		//This method is executed after the scenario execution finishes.
	}

}
