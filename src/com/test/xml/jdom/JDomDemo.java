package com.test.xml.jdom;

import java.io.IOException;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.test.xml.XmlDocument;

/**
*@author huangq
*@date 2017年8月31日
*/
public class JDomDemo implements XmlDocument {

	@Override
	public void parserXml(String fileName) {
		
		SAXBuilder builder=new SAXBuilder();
		try {
			Document document=builder.build(fileName);
			Element users=document.getRootElement();
			List<Element> userList=users.getChildren("user");
			for(int i=0;i<userList.size();i++){
				Element user=userList.get(i);
				System.out.println("------开始遍历第"+(i+1)+"个人-----");
				
				List<Attribute> attrList=user.getAttributes();
				for(Attribute attr:attrList){
					System.out.println("属性名："+attr.getName()+"--属性值："+attr.getValue());
				}
				List<Element> userInfo=user.getChildren();
				for(int j=0;j<userInfo.size();j++){
					System.out.println("节点名："+userInfo.get(j).getName()+"--节点值:"+userInfo.get(j).getValue());
				}
				
				System.out.println("------结束遍历第"+(i+1)+"个人------");
			}
			
		
		} catch (JDOMException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void createXml(String fileName) {
	}

	public static void main(String[] args) {
		XmlDocument xmlJdom=new JDomDemo();
		xmlJdom.parserXml("people.xml");
	}

}
