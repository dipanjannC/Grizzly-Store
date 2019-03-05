package com.grizzly.validation;

public class InputValidations {

	public static boolean textValid(String rating) {

		// If the rating is upto 1 decimal place or Integer then return true
		
		if (rating.matches("^[0-9]+([,.][0-9]?)?$")) {
			return true;
		}

		return false;
	}
}
