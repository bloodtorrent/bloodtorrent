// JUnit Assert framework can be used for verification

import static com.thoughtworks.twist.core.execution.TwistVerification.verifyEquals;
import net.sf.sahi.client.Browser;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtworks.twist.core.execution.TwistScenarioDataStore;

public class OnThankYouPage {

	private Browser browser;

	@Autowired
	private TwistScenarioDataStore scenarioStore;

	public OnThankYouPage(Browser browser) {
		this.browser = browser;
	}

	public void verifyThankYouMessage() throws Exception {
		
		verifyEquals("Thank you for posting your request. We will get in touch soon to validate the details."
				,browser.heading1("Thank you for posting your request. We will get in touch soon to validate the details.").getText()); 

		System.out.println(browser.heading1("Thank you for posting your request. We will get in touch soon to validate the details.").getText());
		browser.link("Home").click();
	
	}

}