import static com.thoughtworks.twist.core.execution.TwistVerification.verifyEquals;
import net.sf.sahi.client.Browser;

import org.springframework.beans.factory.annotation.Autowired;

import pageClasses.ThankYou;

import com.thoughtworks.twist.core.execution.TwistScenarioDataStore;

public class OnThankYouPage {

	@Autowired
	private TwistScenarioDataStore scenarioStore;

	private ThankYou thankyou;

	public OnThankYouPage(Browser browser) {
		thankyou = new ThankYou(browser);
	}

	public void verifyThankYouMessage() throws Exception {
		verifyEquals("Thank you for posting your request. We will get in touch soon to validate the details." ,thankyou.getMessageForBloodRequest());
	}

	public void verifyThankYouMessageForRegister() throws Exception {
		verifyEquals("Thank you for signing up as a donor. Please go ahead and log in", thankyou.getMessageForRegistering());
	}
}