// JUnit Assert framework can be used for verification

import static com.thoughtworks.twist.core.execution.TwistVerification.verifyEquals;
import static com.thoughtworks.twist.core.execution.TwistVerification.verifyTrue;
import net.sf.sahi.client.Browser;

import org.springframework.beans.factory.annotation.Autowired;

import pageClasses.Admin;

import com.thoughtworks.twist.core.execution.TwistScenarioDataStore;

public class OnAdminPage {

	private Browser browser;

	@Autowired
	private TwistScenarioDataStore scenarioStore;
	private Admin admin;
	
	public OnAdminPage(Browser browser) {
		this.browser = browser;
		admin = new Admin(browser);
	}

	public void verifyAdminPage() throws Exception {
		verifyEquals("http://localhost:8080/admin", admin.getUrl());
	}

	public void logout() throws Exception {
		admin.signOff();
	}

}