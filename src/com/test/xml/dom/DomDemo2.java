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
public class DomDemo2 implements XmlDocument {

	@Override
	public void parserXml(String fileName) {
		try {
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			//创建DocumentBuilder对象
			DocumentBuilder db=dbf.newDocumentBuilder();
			//通过DocumentBuilder对象的parser方法加载books.xml文件到当前项目下
			Document document=db.parse(fileName);
			NodeList users=document.getChildNodes();
			System.out.println(users.getLength());
			for(int i=0; i<users.getLength();i++){
				Node user=users.item(i);
				
				System.out.println("节点名"+(i+1)+":"+user.getNodeName());
				NodeList userInfo=user.getChildNodes();
				System.out.println(userInfo.getLength());
				for(int j=0;j<userInfo.getLength();j++){
					Node node=userInfo.item(j);
					if(node.getNodeType()==Node.ELEMENT_NODE)
					System.out.println("节点名"+(i+1)+"."+(j+1)+":"+node.getNodeName());
					if(node.getAttributes()!=null){
						NamedNodeMap map=node.getAttributes();
						
						for(int m=0;m<map.getLength();m++){
							Node node2 =map.item(m);
							if(node2!=null)
							System.out.println("属性名："+node2.getNodeName()+"---属性值："+node2.getNodeValue());
						}
					}
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
		XmlDocument xmlTest=new DomDemo2();
		xmlTest.parserXml("people.xml");
		//xmlTest.parserXml("books.xml");
		
	}

}
