package springmvc.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import springmvc.domain.Car;
import springmvc.domain.CarValidator;

import org.springframework.beans.factory.annotation.Autowired;
import springmvc.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/main")
public class MainController {
	protected static Logger logger = Logger.getLogger("MainController");

	@Resource(name = "carService")
	private CarService carService;

	@Autowired
	private CarValidator carValidator;

	@RequestMapping(value = "/cars", method = RequestMethod.GET)
	public String getCars(Model model) {
		logger.debug("Received request to show all cars");

		// Retrieve all cars by delegating the call to CarService
		Map<String, Object> dic = carService.getAll(1, 0);
		List<Car> cars = (List<Car>) dic.get("list");

		// Attach cars to the Model
		model.addAttribute("cars", cars);
		model.addAttribute("dic", dic);

		// This will resolve to /WEB-INF/jsp/carspage.jsp
		return "carspage";
	}

	@RequestMapping(value = "/cars/add", method = RequestMethod.GET)
	public String getAdd(Model model) {
		logger.debug("Received request to show add page");

		// Create new Person and add to model
		// This is the formBackingOBject
		model.addAttribute("carAttribute", new Car());

		// This will resolve to /WEB-INF/jsp/addpage.jsp
		return "addpage";
	}

	@RequestMapping(value = "/cars/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("carAttribute") Car car,
			BindingResult result) {
		logger.debug("Received request to add new car");

		// The "carAttribute" model has been passed to the controller from the
		// JSP
		// We use the name "carAttribute" because the JSP uses that name
		carValidator.validate(car, result);

		if (result.hasErrors())
			return "addpage";

		// Call CarService to do the actual adding
		carService.add(car);

		// This will resolve to /WEB-INF/jsp/addedpage.jsp
		return "addedpage";
	}

	@RequestMapping(value = "/cars/delete/{id}", method = RequestMethod.GET)
	public String delete(
			@PathVariable Integer id, Model model) {

		logger.debug("Received request to delete existing person");

		// Call CarService to do the actual deleting
		carService.delete(id);

		// Add id reference to Model
		model.addAttribute("id", id);

		// This will resolve to /WEB-INF/jsp/deletedpage.jsp
		return "deletedpage";
	}

	@RequestMapping(value = "/cars/edit/{id}", method = RequestMethod.GET)
	public String getEdit(@PathVariable Integer id, Model model) {
		logger.debug("Received request to show edit page");

		// Retrieve existing Car and add to model
		// This is the formBackingOBject
		model.addAttribute("carAttribute", carService.get(id));

		// This will resolve to /WEB-INF/jsp/editpage.jsp
		return "editpage";
	}

	@RequestMapping(value = "/cars/edit/{id}", method = RequestMethod.POST)
	public String saveEdit(@ModelAttribute("carAttribute") Car car,
			BindingResult result,
			@PathVariable Integer id, Model model) {
		logger.debug("Received request to update car");

		// The "carAttribute" model has been passed to the controller from
		// the JSP
		// We use the name "carAttribute" because the JSP uses that name

		// We manually assign the id because we disabled it in the JSP page
		// When a field is disabled it will not be included in the
		// ModelAttribute
		carValidator.validate(car, result);

		if (result.hasErrors())
			return "editpage";

		car.setId(id);

		// Delegate to CarService for editing
		carService.edit(car);

		// Add id reference to Model
		model.addAttribute("id", id);

		// This will resolve to /WEB-INF/jsp/editedpage.jsp
		return "editedpage";
	}
}
