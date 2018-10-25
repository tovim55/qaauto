package com.verifone.utils;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class VerifyXMLFile {
  static String inputFile = "C:\\\\tmp\\\\eclipse-workspace-testng-maven\\\\eclipse-workspace-testng-maven\\\\tests\\\\testng.xml";

  public static void main(String[] args) throws Exception {
	  String KeyToVerify = "browserType";
	  String ValueToVerify = "CHROME";
	  	  
	  String StringToVerify = "name=\"" + KeyToVerify + "\"" + " value=\"" + ValueToVerify + "\"";

	  
	  System.out.println(StringToVerify);
	  
	  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	  DocumentBuilder builder = factory.newDocumentBuilder();
	   
	  //Build Document
	  Document document = builder.parse(new File(inputFile));
	   
	  //Normalize the XML Structure; 
	  document.getDocumentElement().normalize();
	   
	  //Here comes the root node
	  Element root = document.getDocumentElement();
	  System.out.println(root.getNodeName());
	  
	//Get all tests
	  NodeList nList = document.getElementsByTagName("parameter");
	  NodeList nList1;
	  System.out.println("============================");
	  System.out.println(nList);
	  
	  String textToUpdate;
	  String textUpdated;
	  Boolean verified = false;
	  
	  for (int temp = 0; temp < nList.getLength(); temp++)
	  {
	   Node node = nList.item(temp);
//	   System.out.println(node);   
//	   System.out.println(node.getNodeName());  
//	   System.out.println(node.getTextContent());  
	   textToUpdate = node.getTextContent();
//	   textToUpdate = node.getFirstChild()
	   System.out.println("??????????   " + textToUpdate);
	   if (textToUpdate.contains(KeyToVerify) & textToUpdate.contains(ValueToVerify)) {
		   
		   System.out.println("Verified:   " + textToUpdate);  
		   verified = true;
	   }

	  }


	System.out.println("Done");

 }

}