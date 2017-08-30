package com.test.xml.dom;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.test.xml.XmlDocument;

/**
*@author huangq
*@date 2017年8月30日
*/
public class DomDemo implements XmlDocument {

	@Override
	public void parserXml(String fileName) {
		try {
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			//创建DocumentBuilder对象
			DocumentBuilder db=dbf.newDocumentBuilder();
			//通过DocumentBuilder对象的parser方法加载books.xml文件到当前项目下
			Document document=db.parse(fileName);
			NodeList users=document.getChildNodes();
			for(int i=0; i<users.getLength();i++){
				Node user=users.item(i);
				NodeList userInfo=user.getChildNodes();
				
				for(int j=0;j<userInfo.getLength();j++){
					Node node=userInfo.item(j);
					NodeList userMeta=node.getChildNodes();
					
					for(int k=0;k<userMeta.getLength();k++){
						if(userMeta.item(k).getNodeName()!="#text")
							System.out.println(userMeta.item(k).getNodeName()+":"+userMeta.item(k).getTextContent());
					}
					System.out.println();
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
		XmlDocument xmlTest=new DomDemo();
		xmlTest.parserXml("people.xml");
		//xmlTest.parserXml("books.xml");
		
	}

}
