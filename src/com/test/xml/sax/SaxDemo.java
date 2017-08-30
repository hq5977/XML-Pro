package com.test.xml.sax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.test.xml.XmlDocument;

/**
*@author huangq
*@date 2017年8月30日
*/
public class SaxDemo implements XmlDocument {

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
		boolean hasAttribute=false;
		Attributes attributes=null;
		@Override
		public void startDocument() throws SAXException {
			//System.out.println("文档开始打印了");
		}
		@Override
		public void endDocument() throws SAXException {
			//System.out.println("文档打印结束了");
		}
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			//System.out.println("startElement,"+"uri:"+uri+",localName:"+localName+",qName:"+qName);
			
			if(qName.equals("users")){
				return;
			}

			if(attributes.getLength()>0){
				this.attributes=attributes;
				this.hasAttribute=true;
				//System.out.println(attributes.getLength()+","+attributes.getQName(0)+":"+attributes.getValue(0));
			}
			
			if(qName.equals("user")){
				
				return;
			}
		}
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
		
			if (hasAttribute&&(attributes!=null)) {
				for(int i=0;i<attributes.getLength();i++){
					System.out.println(attributes.getQName(0)+":"+attributes.getValue(0));
				}
			}
		}
		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			System.out.println(new String(ch,start,length));
		}
		
		 
	}
	public static void main(String[] args) {
		XmlDocument xml=new SaxDemo();
		xml.parserXml("people.xml");
	}
	

}
