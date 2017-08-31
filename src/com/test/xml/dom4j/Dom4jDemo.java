package com.test.xml.dom4j;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.test.xml.XmlDocument;

/**
*@author huangq
*@date 2017年8月31日
*/
public class Dom4jDemo implements XmlDocument {

	@Override
	public void parserXml(String fileName) {
		
		File inputXml=new File(fileName);
		SAXReader saxReader=new SAXReader();
		
		try {
			Document document=saxReader.read(inputXml);
			Element users=document.getRootElement();
			
			for(Iterator<Element> i=users.elementIterator();i.hasNext();){
				Element user=i.next();
				System.out.println("----开始遍历"+user.getName()+"----");
				List<Attribute> attrList=user.attributes();
				for(Attribute attr:attrList){
					System.out.println("属性名："+attr.getName()+"---属性值："+attr.getValue());
				}
				for(Iterator<Element> j=user.elementIterator();j.hasNext();){
					Element node=j.next();
					System.out.println("节点名："+node.getName()+"--节点值:"+node.getStringValue());
				}
				System.out.println("---结束遍历"+user.getName()+"----");
			}
		} catch (DocumentException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void createXml(String fileName) {
	}

	public static void main(String[] args) {
		XmlDocument xmlDom4j=new Dom4jDemo();
		xmlDom4j.parserXml("people.xml");
	}

}
