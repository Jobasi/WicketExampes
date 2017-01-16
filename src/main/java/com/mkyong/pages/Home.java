package com.mkyong.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.html.link.Link;


import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.cognizant.entity.Customer;
import com.cognizant.exceptions.CustomerNotFoundException;
import com.cognizant.helper.CustomerList;
import com.mkyong.core.GUIRESTClient;

public class Home extends WebPage {
	private static final long serialVersionUID = 1L;

    public Home(final PageParameters parameters) throws CustomerNotFoundException, IOException {
    /*	Customer badCustomer = new Customer();
    	badCustomer.setPersonId(99L);
    	badCustomer.setFirstName("Bad");
    	badCustomer.setLastName("Guy");
    	badCustomer.setPhoneNumber(2112322211L);
    	badCustomer.setEmail("some@bad.guy");
    	
    	Customer customer = new GUIRESTClient().findById(3L);
    	CustomerList customerList = new GUIRESTClient().fetchAllCustomers();
    	
    	final List<IModel<Customer>> customers = new ArrayList<IModel<Customer>>();
    	
    	customers.add(Model.of(customer));
    	customers.add(Model.of(badCustomer));
    	for(Customer singleCustomer : customerList.getCustomer() ){
    		customers.add(Model.of(singleCustomer));
    	}
    	
        add(new Label("message", "Hello World, Wicket"));
        add(new Label("firstName", customer.getFirstName()));
      
        
        add(new RefreshingView<Customer>("customers") {
        	@Override
        	protected void populateItem(Item<Customer> item) {
        	   item.add(new Label("fullName", new PropertyModel(item.getModel(), "fullName")));
        	   item.add(new Label("email", new PropertyModel(item.getModel(), "email")));
        	}
       
        	@Override
        	protected Iterator<IModel<Customer>> getItemModels() {
        	   return customers.iterator();
        	}			
           });*/
        
        add(new Link("list"){
			@Override
			public void onClick() {			   
               setResponsePage(ListAllCustomers.class);
			}			
		});
        add(new Link("home"){
			@Override
			public void onClick() {			   
               setResponsePage(Home.class);
			}			
		});
    }
    
    
    


}
