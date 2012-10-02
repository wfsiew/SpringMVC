package springmvc.test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import com.sun.xml.internal.bind.v2.model.core.Ref;

import springmvc.domain.*;
import springmvc.service.*;
import springmvc.controller.*;

public class MainControllerTest extends AbstractControllerTest {
	private final List<Car> cars = new ArrayList<Car>();
	private final Map<String, Object> dic = new HashMap<String, Object>();
	private CarService carService;
	private CarValidator carValidator;
	
	public MainControllerTest() {
		carService = mock(CarService.class);
	}
	
	@Before
	public void initCars() {
		Car car = new Car();
		car.setId(99);
		car.setColour("Blue");
		car.setDoors(4);
		car.setMake("Honda");
		car.setModel("City");
		car.setPrice(45000.00);
		car.setYear(2011);
		cars.add(car);
		
		car = new Car();
		car.setId(100);
		car.setColour("Red");
		car.setDoors(2);
		car.setMake("Ferrari");
		car.setModel("Modena");
		car.setPrice(1200000.88);
		car.setYear(2011);
		cars.add(car);
		
		dic.put("item_msg", "1 to 2 of 2");
		dic.put("hasnext", 0);
		dic.put("hasprev", 0);
		dic.put("nextpage", 2);
		dic.put("prevpage", 0);
		dic.put("list", cars);
	}
	
	@After
	public void clean() {
		cars.clear();
		dic.clear();
	}
	
	@Test
	public void getCars() {
		when(carService.getAll(1, 0)).thenReturn(dic);
		
		MainController mc = new MainController();
		ReflectionTestUtils.setField(mc, "carService", carService);
		ExtendedModelMap uiModel = new ExtendedModelMap();
		String result = mc.getCars(uiModel);
		
		assertNotNull(result);
		assertEquals("carspage", result);
		
		List<Car> l = (List<Car>) uiModel.get("cars");
		assertEquals(2, l.size());
	}
	
	@Test
	public void getAdd() {
		MainController mc = new MainController();
		ExtendedModelMap uiModel = new ExtendedModelMap();
		String result = mc.getAdd(uiModel);
		
		assertNotNull(result);
		assertEquals("addpage", result);
		
		Car o = (Car) uiModel.get("carAttribute");
		assertNotNull(o);
	}
	
	@Test
	public void add() {
		final Car o = new Car();
		o.setId(100);
		o.setColour("Black");
		o.setDoors(5);
		o.setMake("Toyota");
		o.setModel("Vios");
		o.setPrice(67000.00);
		o.setYear(2010);
		
		carValidator = mock(CarValidator.class);
		cars.add(o);
		
		MainController mc = new MainController();
		ReflectionTestUtils.setField(mc, "carService", carService);
		ReflectionTestUtils.setField(mc, "carValidator", carValidator);
		
		BindingResult br = new BeanPropertyBindingResult(o, "car");
		ExtendedModelMap uiModel = new ExtendedModelMap();
		String result = mc.add(o, br);
		
		assertNotNull(br);
		assertEquals("addedpage", result);
		assertEquals(3, cars.size());
	}
	
	@Test
	public void delete() {
		MainController mc = new MainController();
		ReflectionTestUtils.setField(mc, "carService", carService);
		ExtendedModelMap uiModel = new ExtendedModelMap();
		String result = mc.delete(99, uiModel);
		
		assertNotNull(result);
		assertEquals("deletedpage", result);
		
		int id = (int) uiModel.get("id");
		assertEquals(99, id);
	}
	
	@Test
	public void getEdit() {
		when(carService.get(99)).thenReturn(cars.get(0));
		
		MainController mc = new MainController();
		ReflectionTestUtils.setField(mc, "carService", carService);
		ExtendedModelMap uiModel = new ExtendedModelMap();
		String result = mc.getEdit(99, uiModel);
		
		assertNotNull(result);
		assertEquals("editpage", result);
		
		Car o = (Car) uiModel.get("carAttribute");
		assertNotNull(o);
		assertEquals("Honda", o.getMake());
	}
	
	@Test
	public void saveEdit() {
		Car o = cars.get(1);
		
		carValidator = mock(CarValidator.class);
		
		MainController mc = new MainController();
		ReflectionTestUtils.setField(mc, "carService", carService);
		ReflectionTestUtils.setField(mc, "carValidator", carValidator);
		
		BindingResult br = new BeanPropertyBindingResult(o, "car");
		ExtendedModelMap uiModel = new ExtendedModelMap();
		String result = mc.saveEdit(o, br, 100, uiModel);
		
		assertNotNull(result);
		assertEquals("editedpage", result);
		
		int id = (int) uiModel.get("id");
		assertEquals(100, id);
	}
	
	@AfterClass
	public static void dispose() {
	}
}
