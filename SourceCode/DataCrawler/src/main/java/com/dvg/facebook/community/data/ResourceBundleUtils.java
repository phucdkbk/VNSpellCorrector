/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvg.facebook.community.data;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author quangvh
 */
public class ResourceBundleUtils {

	public static String getValueByKey(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("configures", Locale.US);
		return bundle.getString(key);
	}

	public static String getValueByKey(String configFile, String key) {
		ResourceBundle bundle = ResourceBundle.getBundle(configFile, Locale.US);
		return bundle.getString(key);
	}

	public static String getDataByKey(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("data", Locale.US);
		return bundle.getString(key);
	}

	public static boolean getCrawlerPolicy(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("crawler_policy", Locale.US);
		return Boolean.parseBoolean(bundle.getString(key));
	}

}
