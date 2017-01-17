package com.mce;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

import com.cognizant.entity.Customer;
import com.cognizant.exceptions.CustomerNotSavedException;
import com.mce.core.GUIRESTClient;

public class CreateCustomerForm extends Form<String>  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TextField<String> firstNameField;
	private TextField<String>  lastNameField;
	private TextField<String>  emailField;
	private TextField<String>  phoneNumberField;
	private Label createStatus;
	
	private String firstName;
	private String lastName ;
	private String email;
	private Long phoneNumber;
	

	public CreateCustomerForm(String id) {
		super(id);
		
		firstNameField = new TextField<String>("firstName", Model.of(""));
		lastNameField = new TextField<String> ("lastName", Model.of(""));
		emailField = new TextField<String> ("email", Model.of(""));
		phoneNumberField = new TextField<String>("phoneNumber", Model.of(""));
		createStatus = new Label("createStatus", Model.of(""));

		add(firstNameField);
		add(lastNameField);
		add(emailField);
		add(phoneNumberField);
		add(createStatus);
		}
	
	
	public final void onSubmit() {
		firstName = firstNameField.getDefaultModelObjectAsString();
		lastName = lastNameField.getDefaultModelObjectAsString();
		email = emailField.getDefaultModelObjectAsString();
		phoneNumber = Long.valueOf(phoneNumberField.getDefaultModelObjectAsString());
		if(firstName.equals("test") && lastName.equals("test") && email.equals("test") ){
			createStatus.setDefaultModelObject(firstName.concat(", ")
					.concat(lastName).concat(", ")
					.concat(email).concat(", ")
					.concat(phoneNumber.toString()));
		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmail(email);
		customer.setPhoneNumber(phoneNumber);
		try {
			new GUIRESTClient().createCustomer(customer);
		} catch (CustomerNotSavedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else
			createStatus.setDefaultModelObject("firstName, lastName and email are not correct sorry..");
	}
}
