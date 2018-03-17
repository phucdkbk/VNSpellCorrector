/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutai.product.text.core.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author quangvh
 */
public class ResourceBundleUtils {

    public static String getValueByKey(String key, String config) {
        ResourceBundle bundle = ResourceBundle.getBundle(config, Locale.US);
        return bundle.getString(key);
    }

}
