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
	}
	
	public void verifyThankYouMessageForRegister() throws Exception {
		verifyEquals("Thank you for signing up as a donor. Please go ahead and log in", browser.listItem(0).text());
	}


}