package org.bloodtorrent.functionaltests.scenarios;

import static com.thoughtworks.twist.core.execution.TwistVerification.verifyEquals;
import net.sf.sahi.client.Browser;
import org.bloodtorrent.functionaltests.pages.Admin;

public class OnAdminPage {

	private Admin admin;
	
	public OnAdminPage(Browser browser) {
		admin = new Admin(browser);
	}

	public void verifyAdminPage() throws Exception {
		verifyEquals("http://localhost:8080/admin", admin.getUrl());
	}

	public void logout() throws Exception {
		admin.signOff();
	}

}