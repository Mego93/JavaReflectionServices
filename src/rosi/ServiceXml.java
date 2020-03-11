package rosi;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import bri.Service;

public class ServiceXml implements Service {
	private final Socket s;
	
	public ServiceXml (Socket socket) {
		s = socket;
	}
	
	static class Erreur implements ErrorHandler{
		
	    public void warning(SAXParseException e) throws SAXException {
	        System.out.println("Attention : " + e.getMessage());
	        throw e;
	    }

	    public void error(SAXParseException e) throws SAXException {
	        System.out.println("Erreur : " + e.getMessage());
	        throw e;
	    }

	    public void fatalError(SAXParseException e) throws SAXException {
	        System.out.println("Erreur fatal : " + e.getMessage());
	        throw e;
	    }
	}
	
   public void run() {
	   String serveur = "ftp://localhost:2121/";
	   String chemin = null;
	   Scanner sc = new Scanner(System.in);

      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

      try {
         factory.setValidating(true);
         
         DocumentBuilder build = factory.newDocumentBuilder();
         
         ErrorHandler Erreur = new Erreur();
         build.setErrorHandler(Erreur);
  	   	 System.out.println("Veuillez saisir un mot :");
  	   	 chemin	= sc.nextLine();
         File fileXML = new File(serveur + chemin);

         try {
            Document xml = build.parse(fileXML);
            System.out.println(xml.getNodeName());
            Element elem = xml.getDocumentElement();
            System.out.println(elem.getNodeName());
         } catch (SAXParseException e){
        	 e.printStackTrace();
         }
         
      } catch (ParserConfigurationException e) {
         e.printStackTrace();
      } catch (SAXException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }      

   }
   
	protected void finalize() throws Throwable {
		 s.close(); 
	}
}
