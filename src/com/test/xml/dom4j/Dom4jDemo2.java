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
public class Dom4jDemo2 implements XmlDocument {

	@Override
	public void parserXml(String fileName) {
		
		File inputXml=new File(fileName);
		SAXReader saxReader=new SAXReader();
		
		try {
			//通过read方法加载文件，获取document对象
			Document document=saxReader.read(inputXml);
			//通过document对象获取根节点
			Element bookStore=document.getRootElement();
			//通过根节点对象的elementIterator方法获取迭代器
			Iterator it=bookStore.elementIterator();
			while (it.hasNext()) {
				System.out.println("======开始遍历某一本书====");
				Element book = (Element) it.next();
				//获取book的属性名及属性值
				List<Attribute> bookAttrs=book.attributes();
				for(Attribute attr:bookAttrs){
					System.out.println("属性名："+attr.getName()+"--属性值："+attr.getValue());
				}
				Iterator itt=book.elementIterator();
				while (itt.hasNext()) {
					Element bookChild = (Element) itt.next();
					System.out.println("节点名："+bookChild.getName()+"--节点值："+bookChild.getStringValue());
					
				}
				System.out.println("======结束遍历某一本书======");
			}
		} catch (DocumentException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void createXml(String fileName) {
	}

	public static void main(String[] args) {
		XmlDocument xmlDom4j=new Dom4jDemo2();
		xmlDom4j.parserXml("books.xml");
	}

}
