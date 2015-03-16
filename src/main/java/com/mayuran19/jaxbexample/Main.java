/*package com.mayuran19.jaxbexample;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import com.mayuran19.generated.example.Shiporder;

public class Main {
	public static void main(String[] args) {
		try {
			SchemaFactory sf = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf
					.newSchema(new File(
							"/home/mayuran/Dropbox/workspace/java/JaxbExample/src/main/resources/xsd/example/example.xsd"));
			File file = new File(
					"/home/mayuran/Dropbox/workspace/java/JaxbExample/src/main/resources/xml/sample.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Shiporder.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			jaxbUnmarshaller.setSchema(schema);
			Shiporder customer = (Shiporder) jaxbUnmarshaller.unmarshal(file);
			System.out.println(customer);
			System.out.println(customer.getOrderid());
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

	}
}
*/