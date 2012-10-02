package springmvc;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TestSAXParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SAXParserFactory fac = SAXParserFactory.newInstance();
			SAXParser p = fac.newSAXParser();
			
			DefaultHandler handler = new DefaultHandler() {
				boolean btitle = false;
				boolean bartist = false;
				boolean bcountry = false;
				boolean bcompany = false;
				boolean bprice = false;
				boolean byear = false;
				
				public void startElement(String uri, String localname, String qname, Attributes attributes) throws SAXException {
					System.out.println("Start element : " + qname);
					
					if ("title".equalsIgnoreCase(qname))
						btitle = true;
					
					if ("artist".equalsIgnoreCase(qname))
						bartist = true;
					
					if ("country".equalsIgnoreCase(qname))
						bcountry = true;
					
					if ("company".equalsIgnoreCase(qname))
						bcompany = true;
					
					if ("price".equalsIgnoreCase(qname))
						bprice = true;
					
					if ("year".equalsIgnoreCase(qname))
						byear = true;
				}
				
				public void endElement(String uri, String localname, String qname) throws SAXException {
					System.out.println("End element : " + qname);
				}
				
				public void characters(char ch[], int start, int length) throws SAXException {
					if (btitle) {
						System.out.println("Title : " + new String(ch, start, length));
						btitle = false;
					}
					
					if (bartist) {
						System.out.println("Artist : " + new String(ch, start, length));
						bartist = false;
					}
					
					if (bcountry) {
						System.out.println("Country : " + new String(ch, start, length));
						bcountry = false;
					}
					
					if (bcompany) {
						System.out.println("Company : " + new String(ch, start, length));
						bcompany = false;
					}
					
					if (bprice) {
						System.out.println("Price : " + new String(ch, start, length));
						bprice = false;
					}
					
					if (byear) {
						System.out.println("Year : " + new String(ch, start, length));
						byear = false;
					}
				}
			};
			
			p.parse("c:\\catalog.xml", handler);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
