package org.bloodtorrent.functionaltests.pages;

import net.sf.sahi.client.Browser;
import net.sf.sahi.client.BrowserCondition;
import net.sf.sahi.client.ExecutionException;

public class RegisterDonor extends BasePage {
	private static final int DEFAULT_WAIT_MILLISECONDS = 1 * 1000;

	public RegisterDonor(Browser browser) {
		super(browser);
	}

	public void setEmail(String existsEmail) {
		browser.textbox("email").setValue(existsEmail);
	}

	public void setFirstName(String firstName) {
		browser.textbox("firstName").setValue(firstName);
	}

	public void setLastName(String lastName) {
		browser.textbox("lastName").setValue(lastName);
	}

	public void setPassWord(String passWord) {
		browser.password("password").setValue(passWord);
	}

	public void setConfirmPassWord(String confirmPassWord) {
		browser.password("confirmPassword").setValue(confirmPassWord);
	}

	public void setAddress(String address) {
		browser.textarea("address").setValue(address);
	}

	public void setCity(String city) {
		browser.textbox("city").setValue(city);
	}

	public void setState(String state) {
		browser.select("state").choose(state);
	}

	public void setCellPhone(String cellPhone) {
		browser.textbox("cellPhone").setValue(cellPhone);
	}

	public void setBloodGroup(String bloodGroup) {
		browser.select("bloodGroup").choose(bloodGroup);
	}

	public void setDistance(String distance) {
		browser.select("distance").choose(distance);
	}	

	public void setBirthday(String birthday) {
		browser.textbox("birthday").setValue(birthday);		
	}

	public void register() {
		browser.byId("register").click();
	}

	public void cancel() {
		browser.byId("cancel").click();
	}

	public void openMap() {
		browser.byId("popupMap").click();
	}

	public String returnAlertText() {
		return browser.lastAlert();
	}

	public double getLng() {
		BrowserCondition condition = new BrowserCondition(browser) {
			public boolean test() throws ExecutionException {
				String lng = browser.byId("lng").getValue();
				return (lng != null && lng.trim().length() > 0);
			}
		};
		browser.waitFor(condition, DEFAULT_WAIT_MILLISECONDS);
		return Double.parseDouble(browser.byId("lng").getValue());
	}

	public double getLat() {
		BrowserCondition condition = new BrowserCondition(browser) {
			public boolean test() throws ExecutionException {
				String lat = browser.byId("lat").getValue();
				return (lat != null && lat.trim().length() > 0);
			}
		};
		browser.waitFor(condition, DEFAULT_WAIT_MILLISECONDS);
		return Double.parseDouble(browser.byId("lat").getValue());
	}
	
	public String getErrorMessage() {
		BrowserCondition condition = new BrowserCondition(browser) {
			
			String errorMessage = "";
			String changedMessage = "";
			
			public boolean test() throws ExecutionException {
				// initial : hide -> show up
				// change error : compare the messages
				errorMessage = browser.div("message error").getText();
				if (errorMessage == null) {
					return true;
				} else if(!changedMessage.equals(errorMessage)){
					
					changedMessage = errorMessage;
					return false;
				}else{
					return true;
				}
			}
		};
		browser.waitFor(condition, DEFAULT_WAIT_MILLISECONDS);
		return browser.div("message error").getText(); 
	}

}
