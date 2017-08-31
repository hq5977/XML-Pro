package com.test.xml.dom;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.test.xml.XmlDocument;

/**
*@author huangq
*@date 2017年8月30日
*/
public class DomDemo3 implements XmlDocument {

	@Override
	public void createXml(String fileName) {

		Document document =null;
		
		DocumentBuilder db=getDocumentBuilder();
		document=db.newDocument();
		document.setXmlStandalone(true);
		Element bookStore=document.createElement("bookStore");
		
		//向bookStore根节点添加子节点book
		Element book=document.createElement("book");
		
		Element name=document.createElement("name");
		//name.setNodeValue("小王子");
		name.setTextContent("小王子");
		book.appendChild(name);
		
		Element author=document.createElement("author");
		author.setTextContent("Tom");
		book.appendChild(author);
		
		Element year=document.createElement("year");
		year.setTextContent("2015");
		book.appendChild(year);
		
		Element price=document.createElement("price");
		price.setTextContent("23");
		book.appendChild(price);
		
		book.setAttribute("id", "1");
		//将book节点添加到bookstore根节点中
		bookStore.appendChild(book);
		//将bookstore节点添加到dom树中
		document.appendChild(bookStore);
		//创建TransformerFactory对象		
		TransformerFactory tff=TransformerFactory.newInstance();
		try {
			//创建Transformer对象			
			Transformer tf=tff.newTransformer();
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.transform(new DOMSource(document), new StreamResult(new File(fileName)));
		} catch (TransformerConfigurationException e) {
			
			e.printStackTrace();
		} catch (TransformerException e) {
			
			e.printStackTrace();
		}
	
	
	}
	/**
	 * 
	 */
	public DocumentBuilder getDocumentBuilder() {
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder db=null;
		try {
			db=factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			
			e.printStackTrace();
		}
		
		return db;
	}
	@Override
	public void parserXml(String fileName) {
		
			
			try {
				DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
				//创建DocumentBuilder对象
				DocumentBuilder db=dbf.newDocumentBuilder();
				Document document=db.parse(fileName);
				NodeList nodeList=document.getChildNodes(); 
				for(int i=0;i<nodeList.getLength();i++){
					Node node=nodeList.item(i);
					System.out.println(node.getNodeName());
					
					NodeList userList=node.getChildNodes();
					for(int j=0;j<userList.getLength();j++){
						Node node2=userList.item(j);
						if(node2.getNodeType()==Node.ELEMENT_NODE){
							System.out.println("-------------");
							System.out.println("节点名："+node2.getNodeName());
							NamedNodeMap map=node2.getAttributes();
							for(int k=0;k<map.getLength();k++){
								Node node3=map.item(k);
								System.out.println("属性值："+node3.getNodeName()+"--属性值："+node3.getNodeValue());
							}
							
							NodeList nodeList2=node2.getChildNodes();
							for(int k=0;k<nodeList2.getLength();k++){
								Node user=nodeList2.item(k);
								if(user.getNodeType()==Node.ELEMENT_NODE){
									System.out.println(user.getNodeName()+":"+user.getFirstChild().getNodeValue());
								}
							}
							
						}
						
					}
				}
			} catch (ParserConfigurationException e) {
				
				e.printStackTrace();
			} catch (SAXException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
	public static void main(String[] args) {
		XmlDocument xmltest=new DomDemo3();
		//xmltest.parserXml("people.xml");
		xmltest.createXml("books1.xml");
	}

}
