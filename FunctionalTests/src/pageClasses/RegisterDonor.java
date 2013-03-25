package pageClasses;

import net.sf.sahi.client.Browser;

public class RegisterDonor extends BasePage {
	
	public RegisterDonor(Browser browser){
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

	public void register() {
		browser.submit("Register").click();
		
	}

	public void cancel() {
		 browser.button("Cancel").click();
		
	}

}