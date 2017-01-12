package com.mkyong.pages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

import com.cognizant.entity.Customer;
import com.cognizant.exceptions.CustomerNotFoundException;

public class Home extends WebPage {
	private static final long serialVersionUID = 1L;

    public Home(final PageParameters parameters) throws CustomerNotFoundException {
    	Customer customer = findById(3L);
        add(new Label("message", "Hello World, Wicket"));
        add(new Label("firstName", customer.getFirstName()));
        

    }
    
private final String baseUrl = "http://localhost:8080/server/api/customer";
	
	private HttpURLConnection makeRestCall(String path, String method, Long id) throws IOException  {
		URL url = new URL(String.format("%s/%s/%d", baseUrl, path, id));
		System.out.println("Server Uri:  " + url.toString() + " was Triggred");
		HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
		httpConnection.setRequestMethod(method);
		httpConnection.setRequestProperty("Accept", "application/xml");
		httpConnection.setRequestProperty("Content-Type", "application/xml");
		return httpConnection;
	}
	
	public Customer findById(Long customerId) throws CustomerNotFoundException{
		Customer customer = new Customer();
		BufferedReader bufferedReader = null;
		try{
			HttpURLConnection httpConnection = makeRestCall("find", "GET", customerId);
			if(httpConnection.getResponseCode() == 404){
				throw new CustomerNotFoundException();
			}
			bufferedReader = new BufferedReader(new InputStreamReader(
					(httpConnection.getInputStream())));
			
			String xmlString = bufferedReader.readLine();  
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xmlString);
			customer = (Customer) unmarshaller.unmarshal(reader);
			
			if (customer == null){
				throw new CustomerNotFoundException();
			}
			
		} catch (RuntimeException | IOException | JAXBException e){
			
		}
		return customer;
	}

}
