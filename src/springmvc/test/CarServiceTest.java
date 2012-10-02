package springmvc.test;

import static org.junit.Assert.*;

import java.util.*;

import javax.annotation.Resource;

import org.hibernate.*;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import springmvc.domain.*;
import springmvc.service.*;

@Transactional
public class CarServiceTest extends AbstractServiceTest {
	@Autowired
	private CarService carService;
	
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	public CarServiceTest() {
		prepareData();
	}
	
	public void prepareData() {
//		Session s = sessionFactory.openSession();
//		Transaction tx = s.beginTransaction();
//		
//		Car c1 = new Car();
//		c1.setColour("Black");
//		c1.setDoors(4);
//		c1.setMake("Toyota");
//		c1.setModel("Rush");
//		c1.setPrice(78000.00);
//		c1.setYear(2010);
//		
//		s.save(c1);
//		
//		tx.commit();
	}
	
	@Test
	public void getAll() {
		Map<String, Object> map = carService.getAll(1, 0);
		
		assertEquals("1 to 1 of 1", (String) map.get("item_msg"));
	}
	
	@AfterClass
	public static void dispose() {
	}
}
