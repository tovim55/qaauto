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


public class ModifyXMLFile {
  static String inputFile = "C:\\\\tmp\\\\eclipse-workspace-testng-maven\\\\eclipse-workspace-testng-maven\\\\tests\\\\testng.xml";
  static String outputFile = "C:\\\\tmp\\\\eclipse-workspace-testng-maven\\\\eclipse-workspace-testng-maven\\\\tests\\\\testng1.xml";

  public static void ModifyXML(String KeyToUpdate, String ValueToUpdate, String ValueUpdated) throws Exception {
//	  String KeyToUpdate = "browserType";
//	  String ValueToUpdate = "CHROME";
//	  String ValueUpdated = "IE";
	  
	  
	  
//	  "name=\"env\" value=\"DEV\""
	  String StringToUpdate = "name=\"" + KeyToUpdate + "\"" + " value=\"" + ValueToUpdate + "\"";
	  String StringUpdated = "name=\"" + KeyToUpdate + "\"" + " value=\"" + ValueUpdated + "\"";
	  
	  System.out.println(StringToUpdate);
	  System.out.println(StringUpdated);
	  
	  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	  DocumentBuilder builder = factory.newDocumentBuilder();
	   
	  //Build Document
	  Document document = builder.parse(new File(outputFile));
	   
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
	  
	  for (int temp = 0; temp < nList.getLength(); temp++)
	  {
	   Node node = nList.item(temp);
//	   System.out.println(node);   
//	   System.out.println(node.getNodeName());  
	   System.out.println(node.getTextContent());  
	   textToUpdate = node.getTextContent();
	   if (textToUpdate.contains(StringToUpdate) ) {
		   textUpdated = textToUpdate.replaceAll(StringToUpdate, StringUpdated);
		   System.out.println(textUpdated);  
		   node.setTextContent(textUpdated);
	   }
//	   if (textToUpdate.contains("name=\"env\" value=\"DEV\"") ) {
//		   textUpdated = textToUpdate.replaceAll("name=\"env\" value=\"DEV\"", "name=\"env\" value=\"TEST\"");
//		   System.out.println(textUpdated);  
//		   node.setTextContent(textUpdated);
//	   }
	  }
//	// write the content into xml file
	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	Transformer transformer = transformerFactory.newTransformer();
	DOMSource source = new DOMSource(document);
	StreamResult result = new StreamResult(new File(outputFile));
	transformer.transform(source, result);

	System.out.println("Done");

 }
    public static boolean VerifyXML(String KeyToVerify, String ValueToVerify) throws Exception {
//  	  String KeyToVerify = "browserType";
//  	  String ValueToVerify = "CHROME";
  	  	  
  	  String StringToVerify = "name=\"" + KeyToVerify + "\"" + " value=\"" + ValueToVerify + "\"";

  	  
  	  System.out.println(StringToVerify);
  	  
  	  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
  	  DocumentBuilder builder = factory.newDocumentBuilder();
  	   
  	  //Build Document
  	  Document document = builder.parse(new File(outputFile));
  	   
  	  //Normalize the XML Structure; 
  	  document.getDocumentElement().normalize();
  	   
  	  //Here comes the root node
  	  Element root = document.getDocumentElement();
  	  System.out.println(root.getNodeName());
  	  
  	//Get all tests
  	  NodeList nList = document.getElementsByTagName("parameter");
  	  System.out.println("============================");
  	  System.out.println(nList);
  	  
  	  String textToUpdate;
  	  Boolean verified = false;
  	  
  	  for (int temp = 0; temp < nList.getLength(); temp++)
  	  {
  	   Node node = nList.item(temp);
//  	   System.out.println(node);   
//  	   System.out.println(node.getNodeName());  
//  	   System.out.println(node.getTextContent());  
  	   textToUpdate = node.getTextContent();
//  	   textToUpdate = node.getFirstChild()
  	   System.out.println("??????????   " + textToUpdate);
  	   if (textToUpdate.contains(KeyToVerify) & textToUpdate.contains(ValueToVerify)) {
  		   
  		   System.out.println("Verified:   " + textToUpdate);  
  		   verified = true;
  	   }

  	  }


  	System.out.println("Done");
	return verified;

   }

}

