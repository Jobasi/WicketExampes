package com.mkyong.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.cognizant.entity.Customer;
import com.cognizant.exceptions.CustomerNotFoundException;
import com.cognizant.helper.CustomerList;

public class GUIRESTClient {
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
	
	private HttpURLConnection makeRestCall(String path, String method, String email) throws IOException  {
		URL url = new URL(String.format("%s/%s/%s", baseUrl, path, email));
		System.out.println("Server Uri:  " + url.toString() + " was Triggred");
		HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
		httpConnection.setRequestMethod(method);
		httpConnection.setRequestProperty("Accept", "application/xml");
		httpConnection.setRequestProperty("Content-Type", "application/xml");
		return httpConnection;
	}
	

	private HttpURLConnection makeRestCall(String path, String method) throws IOException, MalformedURLException  {
		URL url = new URL(String.format("%s/%s", baseUrl, path));
		System.out.println("Server Uri:  " + url.toString() + " was Triggred");

		HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
		httpConnection.setRequestMethod(method);
		httpConnection.setRequestProperty("Content-Type", "application/xml");
		httpConnection.setRequestProperty("Accept", "application/xml");
		httpConnection.setDoInput(true);
		httpConnection.setDoOutput(true);
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
			
		} catch (RuntimeException e){
			
		} catch (IOException e){
			
		}catch (JAXBException e){
			
		}
		return customer;
	}
	
	public CustomerList fetchAllCustomers() throws IOException {
		HttpURLConnection httpConnection = makeRestCall("list", "GET");
		CustomerList customer = new CustomerList();
		BufferedReader bufferedReader;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(
					(httpConnection.getInputStream())));
			String xmlString = bufferedReader.readLine(); 
			JAXBContext jaxbContext = JAXBContext.newInstance(CustomerList.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xmlString);
			customer = (CustomerList) unmarshaller.unmarshal(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customer;
	}

}
