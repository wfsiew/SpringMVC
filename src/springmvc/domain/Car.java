package springmvc.domain;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class Car implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8140136410404076645L;

	private int id;
	private String make;
	private String model;
	private Integer year;
	private Integer doors;
	private String colour;
	private Double price;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the make
	 */
	@Column(name = "make")
	public String getMake() {
		return make;
	}

	/**
	 * @param make
	 *            the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * @return the doors
	 */
	@Column(name = "doors")
	public Integer getDoors() {
		return doors;
	}

	/**
	 * @param doors
	 *            the doors to set
	 */
	public void setDoors(Integer doors) {
		this.doors = doors;
	}

	/**
	 * @return the colour
	 */
	public String getColour() {
		return colour;
	}

	/**
	 * @param colour
	 *            the colour to set
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	public static Map<String, Object> getErrors(BindingResult result) {
		List<FieldError> ls = result.getFieldErrors();

		Map<String, Object> dic = new HashMap<String, Object>();
		Map<String, String> m = new HashMap<String, String>();
		ResourceBundle rb = ResourceBundle.getBundle("typemismatch");

		for (FieldError f : ls) {
			String s = f.getDefaultMessage();

			if (f.getField().equals("year") || f.getField().equals("doors")) {
				if (f.isBindingFailure())
					s = rb.getString("typeMismatch.java.lang.Integer");
			}

			else if (f.getField().equals("price")) {
				if (f.isBindingFailure())
					s = rb.getString("typeMismatch.java.lang.Double");
			}

			m.put(f.getField(), s);
		}

		dic.put("error", 1);
		dic.put("errors", m);

		return dic;
	}
}
