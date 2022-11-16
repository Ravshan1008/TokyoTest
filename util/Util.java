package com.clearskye.test.util;

import java.text.DecimalFormat;

public class Util {

	public static Integer getInteger(Object obj) {
		try {
			return (int) Double.parseDouble(obj.toString());

		} catch (Exception e) {

		}

		return -1;
	}

	public static Double getDouble(Object obj) {
		try {
			return Double.parseDouble(obj.toString());

		} catch (Exception e) {

		}

		return (double) -1;
	}

	public static double roundTwoDecimals(double d) {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d));
	}
}
