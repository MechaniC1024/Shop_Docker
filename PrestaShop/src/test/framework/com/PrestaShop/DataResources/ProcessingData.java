package com.PrestaShop.DataResources;

import static com.PrestaShop.Report.Report.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.DataProvider;

public class ProcessingData {
	
	public static final String sep = File.separator;
	private static final String path = "src" + sep + "test" + sep + "resources"  + sep + "ProjectData" + sep;

	private static final String pathToLoginData = path + "DataLoginInAdmin.properties";
	private static final String pathToURLs = path + "URLsData.properties";
	private static final String pathToCustomerData = path + "CustomerData.properties";

	private static final String urlAdmin = "UrlAdmin";
	private static final String urlSite = "UrlSite";
	private static final String customerData = "Data";
	private static final String customerAddress = "Address";

	private static final String loginAdmin = "LoginAdmin";
	private static final String passwordAdmin = "PasswordAdmin";

	private static String getProperty(InputStream fileProper, String key) {

		Properties prop = new Properties();

		try {
			prop.load(fileProper);
		} catch (IOException e) {
			addAttachmentToReport("Exception: IOException", e.getMessage());
		}

		String strProp = prop.getProperty(key);

		return strProp;
	}

	private static List<String> getListProperty(InputStream fileProper, String key) {

		Properties prop = new Properties();

		try {
			prop.load(fileProper);
		} catch (IOException e) {
			addAttachmentToReport("Exception: IOException", e.getMessage());
		}

		String strData = prop.getProperty(key);
		List<String> listData = Arrays.asList(strData.split(","));

		return listData;
	}

	public static String getUrlAdminPanel() {
		String url = "";
		try {
			url = getProperty(new FileInputStream(pathToURLs), urlAdmin);
		} catch (FileNotFoundException e) {
			addAttachmentToReport("Exception: FileNotFoundException", e.getMessage());
		}
		return url;
	}

	public static String getUrlSite() {
		String url = "";
		try {
			url = getProperty(new FileInputStream(pathToURLs), urlSite);
		} catch (FileNotFoundException e) {
			addAttachmentToReport("Exception: FileNotFoundException", e.getMessage());
		}
		return url;
	}

	public static int getRandomPayment() {

		int payment = (int) ((Math.random() * 2) + 1);

		return payment;
	}

	@DataProvider
	public Object[][] getLoginAndPassword() {

		String login = "";
		String password = "";
		try {
			login = getProperty(new FileInputStream(pathToLoginData), loginAdmin);
			password = getProperty(new FileInputStream(pathToLoginData), passwordAdmin);
		} catch (FileNotFoundException e) {
			addAttachmentToReport("Exception: FileNotFoundException", e.getMessage());
		}
		return new Object[][] { { login, password } };
	}

	@DataProvider
	public Object[][] getCustomerData() {

		List<String> listData = new ArrayList<>();

		try {
			listData = getListProperty(new FileInputStream(pathToCustomerData), customerData);
		} catch (FileNotFoundException e) {
			addAttachmentToReport("Exception: FileNotFoundException", e.getMessage());
		}

		Object arrayData[] = listData.toArray();
		return new Object[][] { arrayData };
	}

	@DataProvider
	public Object[][] getCustomerAddress() {

		List<String> listData = new ArrayList<>();

		try {
			listData = getListProperty(new FileInputStream(pathToCustomerData), customerAddress);
		} catch (FileNotFoundException e) {
			addAttachmentToReport("Exception: FileNotFoundException", e.getMessage());
		}

		Object arrayData[] = listData.toArray();
		return new Object[][] { arrayData };
	}
}