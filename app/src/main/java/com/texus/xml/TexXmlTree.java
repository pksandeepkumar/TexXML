/**
 * 
 */
package com.texus.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author Sandeep Kumar
 *
 */
public class TexXmlTree {

	public TexXmlElement rootElement = null;
	private TexXmlElement currentElement = null;

	public TexXmlTree() {

	}

    public TexXmlTree(String xmlStringToLoad) {
        loadXML(xmlStringToLoad);
    }
	
	public void loadXML(String XmlToLoad ) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		if (null == factory) {
			return;
		}
		SAXParser saxParser = null;
	    try  {
			saxParser = factory.newSAXParser();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

		if (null == saxParser) {
			return;
		}
		
		
		DefaultHandler handler = new DefaultHandler() {
		     public void startElement(String uri, String localName, String qName,
                                      Attributes attributes)
		        throws SAXException {
		    	 TexXmlElement newElement = new TexXmlElement(qName);
		    	 if (null != attributes) {
			    	 for (int i = 0; i < attributes.getLength(); i++)  {
			    		 String keyName = attributes.getLocalName(i);
		    			 String attrValue = attributes.getValue(i);
			    		 if (null != keyName) {
			    			 newElement.setAttribute(keyName, attrValue);
			    		 }
					 }
		    	 }
		    	 if (null != currentElement) {
		    		 newElement.parent = currentElement;
		    		 currentElement.add(newElement);
		    	 }
		    	 currentElement = newElement;
		    	 if (null == rootElement) {
		    		 rootElement = currentElement;
		    	 }
		     }
		 
		     public void endElement(String uri, String localName, String qName) throws SAXException {
		    	 	if (null != currentElement) {
		    	 		currentElement = currentElement.parent;
		    	 	}
		     }
		 
		     public void characters(char ch[], int start, int length) throws SAXException {
		    	 String nodeText = new String(ch, start, length);
		        }
            };

	}
	
	public void Clear()
	{
		deleteElement(rootElement);
	}

	
	private static void deleteElement(TexXmlElement currentElement) {
		if (null == currentElement) {
			return;
		}
		if (null != currentElement.attributes) {
			currentElement.attributes.clear();
		}
		if (null != currentElement.children) {
			currentElement.children.clear();
		}
		currentElement = null;
	}
	
}
