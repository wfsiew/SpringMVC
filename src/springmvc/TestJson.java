package springmvc;

import java.io.*;
import java.util.*;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.*;

import com.google.gson.*;

public class TestJson {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testJackson();
		testGson();
	}
	
	private static void testJackson() {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("k1", "val1");
		m.put("k2", "val2");
		
		List<String> ls = new ArrayList<String>();
		ls.add("pl1");
		ls.add("pl2");
		
		ObjectMapper o = new ObjectMapper();
		long a = System.currentTimeMillis();
		
		try {
			String s1 = o.writeValueAsString(m);
			String s2 = o.writeValueAsString(ls);
			
			System.out.println(s1);
			System.out.println(s2);
			
			long b = System.currentTimeMillis();
			System.out.println(b - a);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testGson() {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("k1", "val1");
		m.put("k2", "val2");
		
		List<String> ls = new ArrayList<String>();
		ls.add("pl1");
		ls.add("pl2");
		
		Gson g = new Gson();
		long a = System.currentTimeMillis();
		
		try {
			String s1 = g.toJson(m);
			String s2 = g.toJson(ls);
			
			System.out.println(s1);
			System.out.println(s2);
			
			long b = System.currentTimeMillis();
			System.out.println(b - a);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
