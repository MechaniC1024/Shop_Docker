package com.PrestaShop.Report;

import java.io.InputStream;

import io.qameta.allure.Allure;

public class Report {
	
	public static void addAttachmentToReport(String name, String content) {

		Allure.addAttachment(name, content);
	}
	
	public static void addAttachmentToReport(String name, StringBuilder content) {

		Allure.addAttachment(name, content.toString());
	}
	
	public static void addAttachmentToReport(String name, InputStream content) {

		Allure.addAttachment(name, content);
	}

}
