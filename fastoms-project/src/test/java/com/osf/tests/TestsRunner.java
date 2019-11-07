package com.osf.tests;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class TestsRunner {
	public static void main(String[] args) {
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] { StorefrontFastOMSTest.class });
		testng.addListener(tla);
		testng.run();
	}
}