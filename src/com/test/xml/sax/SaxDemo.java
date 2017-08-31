package com.test.xml.sax;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;

import com.test.xml.XmlDocument;

/**
*@author huangq
*@date 2017年8月30日
*/
public class SaxDemo implements XmlDocument {

	@Override
	public void createXml(String fileName) {
		SAXTransformerFactory tff=(SAXTransformerFactory) SAXTransformerFactory.newInstance();
		try {
			TransformerHandler handler=tff.newTransformerHandler();
			Transformer tr=handler.getTransformer();
			tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			tr.setOutputProperty(OutputKeys.INDENT, "yes");
			File f=new File(fileName);
			if(!f.exists()){
				f.createNewFile();
			}
			
			Result result=new StreamResult(new FileOutputStream(f));
			handler.setResult(result);
			handler.startDocument();
			
			AttributesImpl attr=new AttributesImpl();
			handler.startElement("", "", "bookstore", attr);
			attr.clear();
			
			attr.addAttribute("", "", "id", "", "1");
			handler.startElement("", "", "book", attr);
			
			attr.clear();
			handler.startElement("", "", "name", attr);
			handler.characters("小王子".toCharArray(), 0, "小王子".length());
			
			handler.endElement("", "", "name");
			handler.endElement("", "", "book");
			handler.endElement("", "", "bookstore");
			handler.endDocument();
		} catch (TransformerConfigurationException e) {
			
			e.printStackTrace();
		}catch (IOException e) {
			
			e.printStackTrace();
		}catch (SAXException e) {
			
			e.printStackTrace();
		}
	}
	@Override
	public void parserXml(String fileName) {
		
		SAXParserFactory saxfac=SAXParserFactory.newInstance();
		
		try {
			SAXParser saxparser=saxfac.newSAXParser();
			InputStream is=new FileInputStream(fileName);
			saxparser.parse(is, new MySAXHandler());
		} catch (ParserConfigurationException e) {
			
			e.printStackTrace();
		} catch (SAXException e) {
			
			e.printStackTrace();
		}catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	class MySAXHandler extends DefaultHandler{
		int userIndex=0;
		boolean hasAttribute=false;
		Attributes attributes=null;
		@Override
		public void startDocument() throws SAXException {
			System.out.println("SAX解析开始");
		}
		@Override
		public void endDocument() throws SAXException {
			System.out.println("SAX解析结束");
		}
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			if(qName.equals("users")){
				return;
			}
			if(attributes.getLength()>0){
				this.attributes=attributes;
				this.hasAttribute=true;
				//System.out.println(attributes.getLength()+","+attributes.getQName(0)+":"+attributes.getValue(0));
			}
			
			if(qName.equals("user")){
				
				userIndex++;
				System.out.println("+++++++++++开始遍历第"+userIndex+"个人++++++++++");
				//已知属性的名称，获取属性值
//				String value =attributes.getValue("id");
//				System.out.println("user的属性值是："+value);
				//不知道user元素下的属性名及个数，获取属性名及属性值
				int num =attributes.getLength();
				for(int i=0;i<num;i++){
					System.out.print("user元素的第"+(i+1)+"个属性："+attributes.getQName(i));
					System.out.println("---属性值是:"+attributes.getValue(i));
				}
				return;
			}else if (!qName.equals("user") &&!qName.equals("users")) {
				System.out.print("节点名是:"+qName+"---节点值:");
			}{
				
			}
		}
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
		
			if(qName.equals("user")){
				System.out.println("++++++++++++结束遍历第"+userIndex+"个人++++++++++++++");
			}
	/*		if (hasAttribute&&(attributes!=null)) {
				for(int i=0;i<attributes.getLength();i++){
					System.out.println(attributes.getQName(0)+":"+attributes.getValue(0));
				}
			}*/
		}
		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			String value=new String(ch,start,length);
			if(!value.trim().equals("")){
				System.out.println(new String(ch,start,length));
			}
		}
		
	}
	public static void main(String[] args) {
		XmlDocument xml=new SaxDemo();
//		xml.parserXml("people.xml");
		xml.createXml("books2.xml");
	}
	

}
