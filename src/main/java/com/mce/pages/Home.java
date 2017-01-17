package com.mce.pages;

import java.io.IOException;


import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

import com.cognizant.exceptions.CustomerNotFoundException;
import com.mce.CreateCustomerForm;

public class Home extends WebPage {
	private static final long serialVersionUID = 1L;

	 @SuppressWarnings({ "serial", "rawtypes" })
    public Home(final PageParameters parameters) throws CustomerNotFoundException, IOException {   
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
        
        add(new CreateCustomerForm("createForm"));
    }
    
    
    


}
