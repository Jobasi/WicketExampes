package com.cognizant.helper;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

import com.cognizant.entity.Customer;

@XmlRootElement(name="customers")
public class CustomerList implements Serializable {
	protected List<Customer> customer;

	public List<Customer> getCustomer() {
		return customer;
	}

	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
	}
}
