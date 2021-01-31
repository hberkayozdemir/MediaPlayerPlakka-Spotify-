package View.GUI.proxy;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ProxyImage implements IImage{

	   private RealImage realImage;
	   private String fileName;
	   private JFrame a;
	   
	   public ProxyImage(String fileName,JFrame a){
	      this.fileName = fileName;
	      this.a = a;
	      
	   }
	   public Icon primage() {
		   Icon imgIcon = new ImageIcon(Main.class.getResource(fileName));//    "/fakeimg.gif"
		   return imgIcon;
	   }


	   public void display() {
	      if(realImage == null){
	         realImage = new RealImage(a);
	      }
	      realImage.display();
	   }
	}