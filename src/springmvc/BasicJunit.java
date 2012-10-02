package springmvc;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class BasicJunit {
	private Collection collection;
	
	@BeforeClass
	public static void oneTimeSetup() {
		// one-time initialization code   
    	System.out.println("@BeforeClass - oneTimeSetUp");
	}
	
	@AfterClass
	public static void oneTimeTearDown() {
		// one-time cleanup code
    	System.out.println("@AfterClass - oneTimeTearDown");
	}
	
	@Before
	public void setUp() {
		collection = new ArrayList<String>();
		System.out.println("@Before - setUp");
	}
	
	@After
	public void tearDown() {
		collection.clear();
		System.out.println("@After - tearDown");
	}
	
	@Test
    public void testEmptyCollection() {
        assertTrue(collection.isEmpty());
        System.out.println("@Test - testEmptyCollection");
    }
	
	@Test
    public void testOneItemCollection() {
        collection.add("itemA");
        assertEquals(1, collection.size());
        System.out.println("@Test - testOneItemCollection");
    }
	
	@Test(expected = ArithmeticException.class)
	public void divideWithException() {
		int i = 7/0;
	}
	
	@Test(timeout = 1000)
	public void infiniteLoop() {
		while (true);
	}
}
