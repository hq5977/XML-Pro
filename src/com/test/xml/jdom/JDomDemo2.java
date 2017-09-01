package com.test.xml.jdom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.test.xml.XmlDocument;

/**
*@author huangq
*@date 2017年8月31日
*/
public class JDomDemo2 implements XmlDocument {

	@Override
	public void parserXml(String fileName) {
		//创建一个SAXBuilder对象
		SAXBuilder builder=new SAXBuilder();
		InputStream in;
		try {
			//创建输入流对象，将文件加载到输入流中
			in=new FileInputStream(fileName);
			InputStreamReader isr=new InputStreamReader(in, "UTF-8");
			//将输入流加载到saxBuilder中
			Document document=builder.build(isr);
			//获取xml文件的根节点
			Element rootEmement=document.getRootElement();
			//获取根节点下的子节点的List集合
			List<Element> bookList=rootEmement.getChildren();
			for(Element book:bookList){
				System.out.println("====开始解析第"+(bookList.indexOf(book)+1)+"本书======");
				List<Attribute> attrList=book.getAttributes();
				for(Attribute attr:attrList){
					String attrName=attr.getName();
					String attrValue=attr.getValue();
					System.out.println("属性名："+attrName+"---属性值："+attrValue);
				}
				List<Element> bookChilds=book.getChildren();
				for(Element child:bookChilds){
					System.out.println("节点名："+child.getName()+"---节点值："+child.getValue());
				}
				
				
				System.out.println("====结束解析第"+(bookList.indexOf(book)+1)+"本书=====");
			}
			
		
			
		
		} catch (JDOMException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void createXml(String fileName) {
		
		//生成一个根节点
		Element rss=new Element("rss");
		//为节点添加属性
		rss.setAttribute("version","2.0");
		//生成一个document对象
		Document document=new Document(rss);
		
		Element channel=new Element("channel");
		rss.addContent(channel);
		
		Element title=new Element("title");
		title.setText("<![CDATA[上海移动互联网产业促进中心正式揭牌 ]]>");
		channel.addContent(title);
		
		Format format=Format.getCompactFormat();
		format.setIndent("");
		format.setEncoding("GBK");
		
		//创建XMLOutputter的对象
		XMLOutputter outputter=new XMLOutputter(format);
		try {
			//利用outputter将document对象转换成xml文档
			outputter.output(document, new FileOutputStream(new File("rssnews2.xml")));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		XmlDocument xmlJdom=new JDomDemo2();
		//xmlJdom.parserXml("src/res/books.xml");
		xmlJdom.createXml("");
	}

}
