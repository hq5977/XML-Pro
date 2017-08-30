package com.test.xml.dom;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
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
		xmltest.parserXml("people.xml");
	}

}
