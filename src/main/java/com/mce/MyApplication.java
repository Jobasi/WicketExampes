package com.mce;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

import com.mce.pages.Home;

public class MyApplication extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		// TODO Auto-generated method stub
		return Home.class;
	}

}
