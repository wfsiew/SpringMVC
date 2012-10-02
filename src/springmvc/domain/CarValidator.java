package springmvc.domain;

import java.util.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("carValidator")
public class CarValidator implements Validator {
	@Override
	public boolean supports(Class clazz) {
		return Car.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object model, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "make", "required.make", "Make is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "model", "required.model", "Model is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "year", "required.year", "Year is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "doors", "required.doors", "Doors is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "colour", "required.colour", "Colour is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "required.price", "Price is required.");
		
		Car car = (Car) model;
		if (car.getYear() != null && car.getYear() < 0)
			errors.rejectValue("year", "invalidyear", "Year is invalid.");
		
		if (car.getDoors() != null && car.getDoors() < 0)
			errors.rejectValue("doors", "invaliddoors", "Doors is invalid.");
		
		if (car.getPrice() != null && car.getPrice() < 0)
			errors.rejectValue("price", "invalidprice", "Price is invalid.");
	}
}
