package org.bloodtorrent.functionaltests.scenarios;
import java.util.Random;

import net.sf.sahi.client.Browser;

import org.bloodtorrent.functionaltests.pages.RegisterDonor;
import org.springframework.beans.factory.annotation.Autowired;


import com.thoughtworks.twist.core.execution.TwistScenarioDataStore;

import static com.thoughtworks.twist.core.execution.TwistVerification.verify;
import static com.thoughtworks.twist.core.execution.TwistVerification.verifyEquals;

public class OnRegisterDonorPage {

	private Browser browser;
	private RegisterDonor registerDonor;
	
	private String email = "administrator@bloodtorrent.org";
	private String firstName = "Jane";
	private String lastName = "Jung";
	private String passWord = "12341234";
	private String confirmPassWord = "12341234";
	private String address = "Seoul";
	private String city = "Seoul";
	private String state = "Orissa";
	private String cellPhone = "0102003000";
	private String bloodGroup = "B+";
	private String distance = "10";
	
	
	@Autowired
	private TwistScenarioDataStore scenarioStore;

	public OnRegisterDonorPage(Browser browser) {
		this.browser = browser;
		registerDonor = new RegisterDonor(browser);
	}

	private void setMendatoryFieldsWithoutEmail() {
		registerDonor.setFirstName(firstName);
		registerDonor.setLastName(lastName);
		registerDonor.setPassWord(passWord);
		registerDonor.setConfirmPassWord(confirmPassWord);
		registerDonor.setAddress(address);
		registerDonor.setCity(city);
		registerDonor.setState(state);
		registerDonor.setCellPhone(cellPhone);
		registerDonor.setBloodGroup(bloodGroup);
		registerDonor.setDistance(distance);
	}
	
	public void fillOutTheValidInformationToMandatoryFields() throws Exception {
		
		Random rd= new Random();
		int rdEmailcount = rd.nextInt(1000)*2;
		email = rdEmailcount + "id@samsung.com";
		
		registerDonor.setEmail(email);
		setMendatoryFieldsWithoutEmail();
	}


	public void registerDonor() throws Exception {
		
		registerDonor.register();
	}

	public void cancelRegistration() throws Exception {
		registerDonor.cancel();
	}

	public void fillOutTheValidInformationToMendatoryFieldsWithExistingUsersEmailAddress() throws Exception {
		
		registerDonor.setEmail(email);
		setMendatoryFieldsWithoutEmail();
		
	}

	public void fillOutTheAddressInformation() throws Exception {
		fillOutTheAddressInformation("Pune", "Pune", "Maharashtra");
	
	}
	
	public void fillOutMissingAddressInformation() throws Exception {
		fillOutTheAddressInformation("", "Pune", "Maharashtra");
	}
	
	public void fillOutTheAddressInformation(String address, String city, String state) throws Exception {
		registerDonor.setAddress(address);
		registerDonor.setCity(city);
		registerDonor.setState(state);
	}

	public void openMapsToSelectAccurateLocation() throws Exception {
		registerDonor.openMap();
	}

	public void verifyTheMessageForMandatoryFieldsAboutAddressInformation() throws Exception {
		verifyEquals(registerDonor.returnAlertText(), "Please provide the address, city and state before using map.");
	}

	public void verifyLocationValue() throws Exception {
		verifyEquals("73.85674369999992", registerDonor.getLng());
		verifyEquals("18.5204303", registerDonor.getLat());
	}



}