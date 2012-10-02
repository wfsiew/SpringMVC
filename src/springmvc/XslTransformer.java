package springmvc;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;

import com.sun.org.apache.xml.internal.security.transforms.TransformationException;

import java.io.*;
import java.util.*;

public class XslTransformer {
	private TransformerFactory fac;

	public XslTransformer() {
		fac = TransformerFactory.newInstance();
	}

	public void process(Reader xmlfile, Reader xslfile, Writer wr)
			throws TransformerException {
		process(new StreamSource(xmlfile), new StreamSource(xslfile),
				new StreamResult(wr));
	}

	public void process(File xmlfile, File xslfile, Writer wr)
			throws TransformerException {
		process(new StreamSource(xmlfile), new StreamSource(xslfile),
				new StreamResult(wr));
	}

	public void process(File xmlfile, File xslfile, OutputStream o)
			throws TransformerException {
		process(new StreamSource(xmlfile), new StreamSource(xslfile),
				new StreamResult(o));
	}

	public void process(Source xml, Source xsl, Result result)
			throws TransformerException {
		try {
			Templates template = fac.newTemplates(xsl);
			Transformer transformer = fac.newTransformer();
			transformer.transform(xml, result);
		}
		
		catch (TransformerConfigurationException ex) {
			throw new TransformerException(ex.getMessageAndLocation());
		}
		
		catch (TransformerException ex) {
			throw new TransformerException(ex.getMessageAndLocation());
		}
	}
	
	public static void main(String[] args) {
		File xml = new File("c:\\catalog.xml");
		File xsl = new File("c:\\catalog.xsl");
		
		try {
			FileOutputStream fs = new FileOutputStream("C:\\catalog_out.html");
			XslTransformer o = new XslTransformer();
			o.process(xml, xsl, fs);
			fs.close();
		}
		
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
