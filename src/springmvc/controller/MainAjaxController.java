package springmvc.controller;

import java.util.*;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import springmvc.Utils;
import springmvc.domain.Car;
import springmvc.domain.CarValidator;
import springmvc.service.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ajax")
public class MainAjaxController {
	protected static Logger logger = Logger.getLogger("MainAjaxController");

	@Resource(name = "carService")
	private CarService carService;

	@Autowired
	private CarValidator carValidator;

	@RequestMapping(value = "/cars", method = RequestMethod.GET)
	public String getCarsPage(Model model) {
		logger.debug("Received request to show all cars");

		Map<Integer, String> searchOptions = new HashMap<Integer, String>();
		int key[] = { 0, 1, 2, 3, 4, 5, 6 };
		String value[] = { "All", "Make", "Model", "Year", "Doors", "Colour",
				"Price" };
		for (int i = 0; i < key.length; i++)
			searchOptions.put(key[i], value[i]);

		// Retrieve all cars by delegating the call to CarService
		Map<String, Object> dic = carService.getAll(1, 0);
		List<Car> cars = (List<Car>) dic.get("list");

		// Attach cars to the Model
		model.addAttribute("cars", cars);
		model.addAttribute("searchoptions", searchOptions);
		model.addAttribute("dic", dic);

		// This will resolve to /WEB-INF/jsp/carsajaxpage.jsp
		return "carsajaxpage";
	}

	@RequestMapping(value = "/cars/list", method = RequestMethod.GET)
	public String getCars(
			@RequestParam(value = "find", required = false, defaultValue = "0") Integer find,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "pgnum", required = false, defaultValue = "1") Integer pgnum,
			@RequestParam(value = "pgsize", required = false, defaultValue = "0") Integer pgsize,
			Model model) {
		logger.debug("Received request to show all cars");

		Map<String, Object> dic = null;

		if (find == 0 && Utils.isEmptyString(keyword)) {
			dic = carService.getAll(pgnum, pgsize);
		}

		else {
			dic = carService.getFilterBy(find, keyword, pgnum, pgsize);
		}

		List<Car> cars = (List<Car>) dic.get("list");

		model.addAttribute("cars", cars);
		model.addAttribute("dic", dic);

		return "carlist";
	}

	@RequestMapping(value = "/cars/add", method = RequestMethod.GET)
	public String getAdd(Model model) {
		logger.debug("Received request to show add page");

		// Create new Person and add to model
		// This is the formBackingOBject
		model.addAttribute("carAttribute", new Car());
		model.addAttribute("formTitle", "Create New Car");

		// This will resolve to /WEB-INF/jsp/addform.jsp
		return "saveform";
	}

	@RequestMapping(value = "/cars/add", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> add(@ModelAttribute("carAttribute") Car car,
			BindingResult result, Model model) {
		logger.debug("Received request to add new car");

		// The "carAttribute" model has been passed to the controller from the
		// JSP
		// We use the name "carAttribute" because the JSP uses that name

		// Call CarService to do the actual adding
		carValidator.validate(car, result);
		Map<String, Object> dic = new HashMap<String, Object>();

		if (result.hasErrors()) {
			dic = Car.getErrors(result);
			return dic;
		}

		carService.add(car);

		// This will response "success"
		dic.put("success", 1);
		return dic;
	}

	@RequestMapping(value = "/cars/delete", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> delete(
			@RequestParam Integer id,
			@RequestParam(value = "find", required = false, defaultValue = "0") Integer find,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "pgnum", required = false, defaultValue = "1") Integer pgnum,
			@RequestParam(value = "pgsize", required = false, defaultValue = "0") Integer pgsize,
			Model model) {

		logger.debug("Received request to delete existing car");

		// Call CarService to do the actual deleting
		carService.delete(id);
		String itemscount = carService.getItemMessage(find, keyword, pgnum, pgsize);

		Map<String, Object> dic = new HashMap<String, Object>();
		
		dic.put("success", 1);
		dic.put("itemscount", itemscount);
		return dic;
	}

	@RequestMapping(value = "/cars/edit/{id}", method = RequestMethod.GET)
	public String getEdit(@PathVariable Integer id, Model model) {
		logger.debug("Received request to show edit page");

		// Retrieve existing Car and add to model
		// This is the formBackingOBject
		model.addAttribute("carAttribute", carService.get(id));
		model.addAttribute("formTitle", "Edit Car");

		// This will resolve to /WEB-INF/jsp/editform.jsp
		return "saveform";
	}

	@RequestMapping(value = "/cars/edit/{id}", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> saveEdit(@ModelAttribute("carAttribute") Car car,
			BindingResult result, @PathVariable Integer id, Model model) {
		logger.debug("Received request to update car");

		// The "carAttribute" model has been passed to the controller from
		// the JSP
		// We use the name "carAttribute" because the JSP uses that name

		// We manually assign the id because we disabled it in the JSP page
		// When a field is disabled it will not be included in the
		// ModelAttribute
		carValidator.validate(car, result);
		Map<String, Object> dic = new HashMap<String, Object>();

		if (result.hasErrors()) {
			dic = Car.getErrors(result);
			return dic;
		}

		car.setId(id);

		// Delegate to CarService for editing
		carService.edit(car);

		// This will response "success"
		dic.put("success", 1);
		return dic;
	}
}
